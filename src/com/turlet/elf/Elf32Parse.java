package com.turlet.elf;

import com.turlet.elf.bean.*;
import com.turlet.elf.comm.PH_TYPE;
import com.turlet.elf.util.CommandParser;
import com.turlet.elf.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Create by Silen((myemail)) on 2019/8/23 10:21
 */
public class Elf32Parse {

    private FileChannel fileChannel;

    private Magic magic;

    private ElfHeader32 header;


    private List<ElfSectionHeader32> sectionHeaders;

    private List<ElfProgramHeader32> programHeaders;

    private String interperterString;

    public Elf32Parse(FileChannel fileChannel, Magic magic) {
        this.fileChannel = fileChannel;
        this.magic = magic;
    }

    public void parseElfHeader() throws IOException {
        //Log.i(Arrays.toString(headerBuf.array()));
        fileChannel.position(0);
        ByteBuffer headerBuf = ByteBuffer.allocate(Elf32.ELF_HDR_LENGTH);
        fileChannel.read(headerBuf);

        headerBuf.position(Elf32.EI_NIDENT);

        //这里本来可以直接转换成com.turlet.elf.bean.ElfHeader32对象，但为了更好的体现elf格式的结构
        Elf32.Elf32_Ehdr ehdr = new Elf32.Elf32_Ehdr();
        headerBuf.get(ehdr.e_type);
        headerBuf.get(ehdr.e_machine);
        headerBuf.get(ehdr.e_version);
        headerBuf.get(ehdr.e_entry);
        headerBuf.get(ehdr.e_phoff);
        headerBuf.get(ehdr.e_shoff);
        headerBuf.get(ehdr.e_flags);
        headerBuf.get(ehdr.e_ehsize);
        headerBuf.get(ehdr.e_phentsize);
        headerBuf.get(ehdr.e_phnum);
        headerBuf.get(ehdr.e_shentsize);
        headerBuf.get(ehdr.e_shnum);
        headerBuf.get(ehdr.e_shstrndx);
        header = ElfHeader32.parse(magic, ehdr);

        //根据参数判断是否打印
        if(CommandParser.getInstance().isFileHeader()) {
            header.print();
            System.err.println("");
        }
    }

    /**
     * 解析节头信息
     * @throws IOException
     */
    public void parseElfSectionHeaderTable() throws IOException {
        if(!(CommandParser.getInstance().isSectionHeaders() || CommandParser.getInstance().isDynSyms()
                || CommandParser.getInstance().isRodata())) {
            return;
        }
        if(Objects.isNull(header)){
            throw new NullPointerException("必需先调用com.turlet.elf.Elf32Parse.parseElfHeader方法。");
        }

        fileChannel.position(header.e_shoff);

        sectionHeaders = new ArrayList<>(header.e_shnum);
        ElfSectionHeader32 sh;
        Elf32.Elf32_Shdr shdr;
        for (int i = 0; i < header.e_shnum; i++){
            ByteBuffer shBuf = ByteBuffer.allocate(header.e_shentsize);
            fileChannel.read(shBuf);
            shBuf.rewind();

            shdr = new Elf32.Elf32_Shdr();
            shBuf.get(shdr.sh_name);
            shBuf.get(shdr.sh_type);
            shBuf.get(shdr.sh_flags);
            shBuf.get(shdr.sh_addr);
            shBuf.get(shdr.sh_offset);
            shBuf.get(shdr.sh_size);
            shBuf.get(shdr.sh_link);
            shBuf.get(shdr.sh_info);
            shBuf.get(shdr.sh_addralign);
            shBuf.get(shdr.sh_entsize);

            sh = ElfSectionHeader32.parse(shdr);
            sectionHeaders.add(sh);
        }

        //字符串表保存着一系列以NULL结尾的的字符串
        ElfSectionHeader32 shstrtab = sectionHeaders.get(sectionHeaders.size()-1);
        fileChannel.position(shstrtab.sh_offset);
        ByteBuffer shBuf = ByteBuffer.allocate(shstrtab.sh_size);
        fileChannel.read(shBuf);
        shBuf.rewind();

        //重新计算值对应的打印值
        sectionHeaders = sectionHeaders.stream().map(header -> {
            shBuf.position(header.sh_name);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (; ; ) {
                byte s = shBuf.get();
                baos.write(s);
                if (s == 0) {
                    header.name = new String(baos.toByteArray());
                    break;
                }
            }

            return header;
        }).collect(Collectors.toList());

        if(CommandParser.getInstance().isSectionHeaders()) {
            ElfSectionHeader32.printTableTitle();
            sectionHeaders.stream().forEach(ElfSectionHeader32::print);
            ElfSectionHeader32.printInfo();
        }

        // .dynsym：该section包含了动态链接符号表；其实该section是elf32_sym结构体数组
        SectionParse32 sectionParse= new SectionParse32(fileChannel,sectionHeaders);

        if(CommandParser.getInstance().isDynSyms()) {
            sectionParse.parseDymStr();
            sectionParse.parseDynSym();
        }
        if(CommandParser.getInstance().isNotes()){
            sectionParse.parseNote();
        }
        if(CommandParser.getInstance().isRodata()){
            sectionParse.parseRodata();
        }

    }

    /**
     * 解析程序头部
     * @throws IOException
     */
    public void parseElfProgramHeaderTable() throws IOException{
        if(!CommandParser.getInstance().isProgramHeaders()) {
            return;
        }
        if(Objects.isNull(header)){
            throw new NullPointerException("必需先调用com.turlet.elf.Elf32Parse.parseElfHeader方法。");
        }

        fileChannel.position(header.e_phoff);

        programHeaders = new ArrayList<>(header.e_phnum);

        Elf32.Elf32_Phdr phdr;
        ElfProgramHeader32 ph;
        ElfProgramHeader32 interpPh = null;

        for (int i = 0; i < header.e_phnum; i++) {
            ByteBuffer phBuf = ByteBuffer.allocate(header.e_phentsize);
            fileChannel.read(phBuf);
            phBuf.rewind();

            phdr = new Elf32.Elf32_Phdr();
            phBuf.get(phdr.p_type);
            phBuf.get(phdr.p_offset);
            phBuf.get(phdr.p_vaddr);
            phBuf.get(phdr.p_paddr);
            phBuf.get(phdr.p_filesz);
            phBuf.get(phdr.p_memsz);
            phBuf.get(phdr.p_flags);
            phBuf.get(phdr.p_align);

            ph = ElfProgramHeader32.parse(phdr);
            programHeaders.add(ph);

            if(PH_TYPE.PHT_INTERP == ph.p_type){
                interpPh = ph;
            }
        }
        if(interpPh != null) {
            //获取interp的数据内容
            //这个字符串是一个 ELF 解析器的路径
            fileChannel.position(interpPh.p_offset);
            ByteBuffer interpBuf = ByteBuffer.allocate(interpPh.p_filesz);
            fileChannel.read(interpBuf);
            interperterString = new String(interpBuf.array());
        }

        if(CommandParser.getInstance().isProgramHeaders()) {
            ElfProgramHeader32.printTableTitle();
            programHeaders.stream().forEach(program -> {
                program.print();
                if (PH_TYPE.PHT_INTERP == program.p_type) {
                    Log.i("    [Requesting program interpreter: " + interperterString + "]");
                }
            });
        }

    }
}
