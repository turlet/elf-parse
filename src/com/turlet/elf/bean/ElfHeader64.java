package com.turlet.elf.bean;

import com.turlet.elf.comm.E_MACHINE;
import com.turlet.elf.comm.E_TYPE;
import com.turlet.elf.comm.E_VERSION;
import com.turlet.elf.util.Log;
import com.turlet.elf.util.Utils;

/**
 * Create by Silen((myemail)) on 2019/8/22 11:34
 */
public class ElfHeader64 {

    //目标文件标识
    public Magic magic;

    //目标文件类型
    public int e_type;

    //给出文件的目标体系结构类型
    public int e_machine;

    //目标文件版本
    public long e_version;

    //程序入口的虚拟地址，如果目标文件没有程序入口，可以为0
    public long e_entry;

    public long e_phoff;
    public long e_shoff;
    public long e_flags;
    public int e_ehsize;
    public int e_phentsize;
    public int e_phnum;
    public int e_shentsize;
    public int e_shnum;
    public int e_shstrndx;

    public static ElfHeader64 parse(Magic magic, Elf64.Elf64_Ehdr ehdr){

        ElfHeader64 header = new ElfHeader64();
        header.magic = magic;
        header.e_type = Utils.byte2ToInt(ehdr.e_type);
        header.e_machine = Utils.byte2ToInt(ehdr.e_machine);
        header.e_version = Utils.byte4ToLong(ehdr.e_version);
        header.e_entry = Utils.byte8ToLong(ehdr.e_entry);
        header.e_phoff = Utils.byte8ToLong(ehdr.e_phoff);
        header.e_shoff = Utils.byte8ToLong(ehdr.e_shoff);
        header.e_flags = Utils.byte4ToLong(ehdr.e_flags);
        header.e_ehsize = Utils.byte2ToInt(ehdr.e_ehsize);
        header.e_phentsize = Utils.byte2ToInt(ehdr.e_phentsize);
        header.e_phnum = Utils.byte2ToInt(ehdr.e_phnum);
        header.e_shentsize = Utils.byte2ToInt(ehdr.e_shentsize);
        header.e_shnum = Utils.byte2ToInt(ehdr.e_shnum);
        header.e_shstrndx = Utils.byte2ToInt(ehdr.e_shstrndx);



        return header;
    }

    public void print(){
        magic.print();
        Log.i("  文件类型(Type)： "+ E_TYPE.get(e_type));
        Log.i("  CPU结构(Machine)： " + E_MACHINE.get(e_machine));
        Log.i("  文件版本(Version)： " + E_VERSION.get(e_version));
        Log.i("  入口点地址(Entry point address)： 0x" + Long.toHexString(e_entry));
        Log.i("  程序头起点(Start of program headers)： " + e_phoff + " (bytes into file)");
        Log.i("  节头起点(Start of section headers)： " + e_shoff + " (bytes into file)");
        Log.i("  标志(Flags)： 0x" + Long.toHexString(e_flags));
        Log.i("  ELF头大小(Size of this headers)： " + e_ehsize + " (bytes)");
        Log.i("  程序头大小(Size of program headers)： " + e_phentsize + " (bytes)");
        Log.i("  程序头数量(Number of program headers)： " + e_phnum);
        Log.i("  节头大小(Size of section headers)： " + e_shentsize + " (bytes)");
        Log.i("  节头数量(Number of section headers)： " + e_shnum);
        Log.i("  字符串表索引头(Section header string table index)： " + e_shstrndx);
    }
}
