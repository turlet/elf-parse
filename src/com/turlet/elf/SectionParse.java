package com.turlet.elf;

import com.turlet.elf.bean.Elf32;
import com.turlet.elf.bean.ElfSectionDynsym32;
import com.turlet.elf.bean.ElfSectionHeader32;
import com.turlet.elf.bean.ElfSectionNotes32;
import com.turlet.elf.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by Silen((myemail)) on 2019/8/27 11:27
 */
public class SectionParse {

    private List<ElfSectionHeader32> sectionHeaders;

    private FileChannel fileChannel;

    private ByteBuffer dynstrBuf;

    private List<ElfSectionDynsym32> dynsymList;

    //.rodata保存了只读数据，可以读取但不能修改，例如printf语句中的所有静态字符串封装到该节。
    private List<String> rodataList;

    public SectionParse(FileChannel fileChannel, List<ElfSectionHeader32> sectionHeaders) {
        this.fileChannel = fileChannel;
        this.sectionHeaders = sectionHeaders;
    }

    /**
     * .dynstr
     * 字符串表
     * 字符串表节区包含以NULL结尾的字符序列.
     *
     * 一般，第一个字节(索引为 0)定义为一个空字符串。类似的，字符串表的最后一 个字节也定义为 NULL，以确保所有的字符串都
     * 以 NULL 结尾。索引为 0 的字符串在 不同的上下文中可以表示无名或者名字为 NULL 的字符串。
     *
     * 该section包含了用于动态链接的字符串，通常是符号表项名称字符串；
     * @throws IOException
     */
    public void parseDymStr() throws IOException {
        List<ElfSectionHeader32> dynstrList = sectionHeaders.stream().filter(header -> ".dynstr\u0000".equalsIgnoreCase(header.name)).collect(Collectors.toList());
        if (dynstrList.isEmpty()) {
            Log.e("找不到对应的Section的dynstr字符串表");
            return;
        }
        ElfSectionHeader32 dynstrHeader = dynstrList.get(0);

        if (dynstrHeader != null) {
            fileChannel.position(dynstrHeader.sh_offset);
            dynstrBuf = ByteBuffer.allocate(dynstrHeader.sh_size);
            fileChannel.read(dynstrBuf);
            dynstrBuf.rewind();
        }
    }


    /**
     * .dynsym
     * 符号表机制（readelf -s）
     * 符号表保存了程序实现或使用的所有全局变量和函数，如果程序引用一个自身代码未定义的符号，则称之为未定义符号，
     * 这类引用必须在静态链接期间用其他目标模块或库解决，或在加载时通过动态链接解决。
     *
     * 其中字段中有OBJECT 和 GLOBAL 的即为全局变量
     * @throws IOException
     */
    public void parseDynSym() throws IOException {
        List<ElfSectionHeader32> sectionDynSymList = sectionHeaders.stream().filter(header -> ".dynsym\u0000".equalsIgnoreCase(header.name)).collect(Collectors.toList());
        if (sectionDynSymList.isEmpty()) {
            Log.e("找不到对应的Section的dynsym符号表");
            return;
        }
        ElfSectionHeader32 dynsymHeader = sectionDynSymList.get(0);

        fileChannel.position(dynsymHeader.sh_offset);
        ByteBuffer dynsymBuf = ByteBuffer.allocate(dynsymHeader.sh_size);
        fileChannel.read(dynsymBuf);
        dynsymBuf.rewind();

        int size = dynsymHeader.sh_size /(int)dynsymHeader.sh_entsize;
        dynsymList = new ArrayList<>(size);
        ElfSectionDynsym32 dynsym;
        for (int i = 0; i < size; i++) {
            Elf32.Elf32_Sym sym = new Elf32.Elf32_Sym();
            dynsymBuf.get(sym.st_name);
            //st_value为fun指令偏移地址
            dynsymBuf.get(sym.st_value);
            //st_size为fun指令长度
            dynsymBuf.get(sym.st_size);
            dynsymBuf.get(sym.st_info);
            dynsymBuf.get(sym.st_other);
            dynsymBuf.get(sym.st_shndx);

            dynsym = ElfSectionDynsym32.parse(sym);

            //sh_name值是以.dynstr为基址的索引
            if (dynsym.st_name != 0) {
                dynstrBuf.position(dynsym.st_name);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                for (; ; ) {
                    byte s = dynstrBuf.get();
                    baos.write(s);
                    if (s == 0) {
                        dynsym.name = new String(baos.toByteArray());
                        break;
                    }
                }
            }
            dynsymList.add(dynsym);
        }

        ElfSectionDynsym32.printTableTitle(dynsymList.size());
        dynsymList.stream().forEach(ElfSectionDynsym32::print);

    }

    /**
     * .hash本节包含一张符号哈希表。是一个散列表，允许在不对全表元素进行线性搜索的情况下，快速访问所有符号表项
     * @throws IOException
     */
    public void parseHash() throws IOException {
        List<ElfSectionHeader32> hashList = sectionHeaders.stream().filter(header -> ".hash\u0000".equalsIgnoreCase(header.name)).collect(Collectors.toList());
        if (hashList.isEmpty()) {
            Log.e("找不到对应的Section的.hash节");
            return;
        }
        ElfSectionHeader32 hashHeader = hashList.get(0);

        fileChannel.position(hashHeader.sh_offset);
        ByteBuffer dynsymBuf = ByteBuffer.allocate(hashHeader.sh_size);
        fileChannel.read(dynsymBuf);
        dynsymBuf.rewind();
    }

    /**
     *.data保存初始化过的数据，这是普通程序数据的一部分，可以在程序运行期间修改。
     * .rodata 保存了只读数据，可以读取但不能修改，例如printf语句中的所有静态字符串封装到该节。常量
     * @throws IOException
     */
    public void parseRodata() throws IOException {
        List<ElfSectionHeader32> rodataHeaderList = sectionHeaders.stream().filter(header -> ".rodata\u0000".equalsIgnoreCase(header.name)).collect(Collectors.toList());
        if (rodataHeaderList.isEmpty()) {
            Log.e("找不到对应的Section的rodata节");
            return;
        }
        ElfSectionHeader32 rodataHeader = rodataHeaderList.get(0);

        fileChannel.position(rodataHeader.sh_offset);
        ByteBuffer rodataBuf = ByteBuffer.allocate(rodataHeader.sh_size);
        fileChannel.read(rodataBuf);
        rodataBuf.rewind();

        rodataList = new ArrayList<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < rodataBuf.capacity(); i++) {
            byte s = rodataBuf.get();
            baos.write(s);
            if (s == 0) {
                rodataList.add(new String(baos.toByteArray()));
                baos.reset();
            }
        }
        rodataList.stream().forEach(Log::i);
    }

    /**
     * .note ”注释节(note section)”部分描述
     * @throws IOException
     */
    public void parseNote() throws IOException {
        List<ElfSectionHeader32> noteHeaderList = sectionHeaders.stream().filter(header -> header.name.startsWith(".note")).collect(Collectors.toList());
        if (noteHeaderList.isEmpty()) {
            Log.e("找不到对应的Section的note");
            return;
        }
        ElfSectionHeader32 noteHeader = noteHeaderList.get(0);

        fileChannel.position(noteHeader.sh_offset);
        ByteBuffer noteBuf = ByteBuffer.allocate(noteHeader.sh_size);
        fileChannel.read(noteBuf);
        noteBuf.rewind();

        Elf32.Elf32_Nhdr nhdr = new Elf32.Elf32_Nhdr();
        noteBuf.get(nhdr.n_namesz);
        noteBuf.get(nhdr.n_descsz);
        noteBuf.get(nhdr.n_type);

        ElfSectionNotes32 notes = ElfSectionNotes32.parse(nhdr);
        byte[] names = new byte[notes.n_namesz];
        noteBuf.get(names);
        notes.names = new String(names);
        byte[] descs = new byte[notes.n_descsz];
        noteBuf.get(descs);
        notes.descs = new String(descs);

        System.out.println(notes.toString());
        ElfSectionNotes32.printTableTitle(noteHeader.sh_offset, noteHeader.sh_size);
        notes.print();
    }

    /**
     * .comment 本节包含版本控制信息
     * @throws IOException
     */
    public void parseComment() throws IOException{
        List<ElfSectionHeader32> commentHeaderList = sectionHeaders.stream().filter(header -> ".comment\u0000".equalsIgnoreCase(header.name)).collect(Collectors.toList());
        if (commentHeaderList.isEmpty()) {
            Log.e("找不到对应的Section的comment节");
            return;
        }
        ElfSectionHeader32 commentHeader = commentHeaderList.get(0);

        fileChannel.position(commentHeader.sh_offset);
        ByteBuffer noteBuf = ByteBuffer.allocate(commentHeader.sh_size);
        fileChannel.read(noteBuf);
        noteBuf.rewind();
        Log.i("本节包含版本控制信息: "+new String(noteBuf.array()));
    }

    /**
     * .bss
     */
    public void parseBss(){
        Log.i("本节中包含目标文件中未初始化的全局变量。一般情况下，可执行程序在开\n" +
                "始运行的时候，系统会把这一段内容清零。但是，在运行期间的 bss 段是由系统初\n" +
                "始化而成的，在目标文件中.bss 节并不包含任何内容，其长度为 0，所以它的节类\n" +
                "型为 SHT_NOBITS。");
    }

    /**
     * .got 此节包含全局偏移量表。
     */
    public void parseGot(){

    }

    /**
     * .dynamic
     * 本节包含动态连接信息，并且可能有 SHF_ALLOC 和 SHF_WRITE 等属性。
     * 是否具有 SHF_WRITE 属性取决于操作系统和处理器。
     */
    public void parseDynamic(){

    }

    /**
     * .fini
     * 此节包含进程终止时要执行的程序指令。当程序正常退出时，系统会执行这
     * 一节中的代码
     */
    public void parseFini(){

    }

    /**
     * .init
     *此节包含进程初始化时要执行的程序指令。当程序开始运行时，系统会在进
     * 入主函数之前执行这一节中的代码。
     */
    public void parseInit(){

    }

    /**
     * .interp
     * 此节含有 ELF 程序解析器的路径名。如果此节被包含在某个可装载的段中，
     * 那么本节的属性中应置 SHF_ALLOC 标志位，否则不置此标志。
     */
    public void parseInterp(){

    }

    /**
     * .line
     * 本节也是一个用于调试的节，它包含那些调试符号的行号，为程序指令码与
     * 源文件的行号建立起联系。其内容格式没有统一规定。
     */
    public void parseLine(){

    }

    /**
     * .plt
     * 此节包含函数连接表
     */
    public void parsePlt(){

    }

    /**
     * .text
     * 本节包含程序指令代码。
     */
    public void parseText(){

    }

}
