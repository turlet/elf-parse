package com.turlet.elf.bean;

import com.turlet.elf.comm.PH_FLAG;
import com.turlet.elf.comm.PH_TYPE;
import com.turlet.elf.util.Log;
import com.turlet.elf.util.Utils;

/**
 * Create by Silen((myemail)) on 2019/8/23 18:34
 * 程序头部
 */
public class ElfProgramHeader64 {

    //此数组元素描述的段的类型，或者如何解释此数组元素的信息
    public long p_type;
    //此成员给出从文件头到该段第一个字节的偏移
    public long p_offset;
    //此成员给出段的第一个字节将被放到内存中的虚拟地址
    public long p_vaddr;
    //此成员仅用于与物理地址相关的系统中。因为 System V 忽略所 有应用程序的物理地址信息，此字段对与可执行文件和共享目标 文件而言具体内容是未指定的。
    public long p_paddr;
    //此成员给出段在文件映像中所占的字节数。可以为 0。
    public long p_filesz;
    //此成员给出段在内存映像中占用的字节数。可以为 0。
    public long p_memsz;
    //此成员给出与段相关的标志。
    public long p_flags;
    //可加载的进程段的 p_vaddr 和 p_offset 取值必须合适，相对于对页面大小的取模而言。此成员给出段在文件中和内存中如何 对齐。
    // 数值 0 和 1 表示不需要对齐。否则 p_align 应该是个 正整数，并且是 2 的幂次数，p_vaddr 和 p_offset 对 p_align 取模后应该相等。
    public long p_align;

    /* 以下字符为格式化输出字段*/
    public String type;

    public static ElfProgramHeader64 parse(Elf64.Elf64_Phdr phdr){
        ElfProgramHeader64 ph = new ElfProgramHeader64();
        ph.p_type = Utils.byte4ToLong(phdr.p_type);
        ph.p_flags = Utils.byte4ToLong(phdr.p_flags);
        ph.p_offset = Utils.byte8ToLong(phdr.p_offset);
        ph.p_vaddr = Utils.byte8ToLong(phdr.p_vaddr);
        ph.p_paddr = Utils.byte8ToLong(phdr.p_paddr);
        ph.p_filesz = Utils.byte8ToLong(phdr.p_filesz);
        ph.p_memsz = Utils.byte8ToLong(phdr.p_memsz);
        ph.p_align = Utils.byte8ToLong(phdr.p_align);

        ph.type = PH_TYPE.get(ph.p_type);
        return ph;
    }

    public static void printTableTitle(){
        Log.i("Type               Offset             VirtAddr           PhysAddr           FileSiz            MemSiz             Flg Align");
    }

    public void print(){
        Log.i(Utils.formatValue(type, 18," ",true)+" 0x"+
                Utils.formatValue(Long.toHexString(p_offset), 16,"0",false)+" 0x"+
                Utils.formatValue(Long.toHexString(p_vaddr), 16,"0",false)+" 0x"+
                Utils.formatValue(Long.toHexString(p_paddr), 16,"0",false)+" 0x"+
                Utils.formatValue(Long.toHexString(p_filesz), 16,"0",false)+" 0x"+
                Utils.formatValue(Long.toHexString(p_memsz), 16,"0",false)+" "+
                Utils.formatValue(PH_FLAG.get(p_flags), 3," ",true)+" 0x"+
                Utils.formatValue(Long.toHexString(p_align), 6,"0",false));
    }

}
