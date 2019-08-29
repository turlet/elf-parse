package com.turlet.elf.bean;

import com.turlet.elf.comm.DYNSYM_BIND;
import com.turlet.elf.comm.DYNSYM_TYPE;
import com.turlet.elf.util.Log;
import com.turlet.elf.util.Utils;

/**
 * Create by Silen((myemail)) on 2019/8/27 13:48
 * 符号表
 * 目标文件的符号表中包含用来定位、重定位程序中符号定义和引用的信息。符号表 索引是对此数组的索引。索引0表示表中的第一表项，同时也作为未定义符号的索引。
 */
public class ElfSectionDynsym32 {


    //包含目标文件符号字符串表的索引，其中包含符号名的字符串表示，如果该值非0，则它表示了给出符号名的字符串表示索引，
    // 否则符号表项没有名称，注：外部C符号在C语言和目标文件的符号表具有相同的名称
    public int st_name;
    //此成员给出相关的符号的取值，依赖于具体的上下文，它可能是一个绝对值，一个地址等等
    public long st_value;
    //很多符号具有相关的尺寸大小，例如一个数据对象的大小是对象中包含的字节数，如果符号没有大小或者大小未知，则此成员为0
    public long st_size;
    //此成员给出符号的类型和绑定属性。下面给出若干取值和含义的绑定关系。
    /*
        #define ELF32_ST_BIND(i)    ((i)>>4)        //获取绑定信息
        #define ELF32_ST_TYPE(i)    ((i)&0xf)       //获取符号类型
        #define ELF32_ST_INFO(b, t) (((b)<<4) + ((t)&0xf))
     */
    public byte st_info;
    //该成员当前包含0，其含义没有定义
    public byte st_other;
    //每个符号表项都以和其他节区的关系的方式给出定义。此成员给出相关的节区头部表索引，某些索引具有特殊含义
    public int st_shndx;

    /* 以下字符为格式化输出字段*/
    public String name = "";

    private static int index = 0;

    public static ElfSectionDynsym32 parse(Elf32.Elf32_Sym sym) {

        ElfSectionDynsym32 header = new ElfSectionDynsym32();
        header.st_name = Utils.byte4ToInt(sym.st_name);
        header.st_value = Utils.byte4ToLong(sym.st_value);
        header.st_size = Utils.byte4ToLong(sym.st_size);
        header.st_info = sym.st_info[0];
        header.st_other = sym.st_other[0];
        header.st_shndx = Utils.byte2ToInt(sym.st_shndx);

        //重置下标
        index = 0;
        return header;
    }

    public static void printTableTitle(int size){
        Log.i(String.format("Symbol table '.dynsym' contains %d entries:",size));
        Log.i("   Num:    Value  Size Type    Bind   Vis      Ndx Name");
    }

    public void print(){
        Log.i("   "+ Utils.formatValue(String.valueOf(index), 3," ",false)+": "+
                Utils.formatValue(Long.toHexString(st_value), 8,"0",false)+"  "+
                Utils.formatValue(String.valueOf(st_size), 4," ",false) +" "+
                Utils.formatValue(DYNSYM_TYPE.get(Byte.toUnsignedInt(st_info)), 7," ",true) +" "+
                Utils.formatValue(DYNSYM_BIND.get(Byte.toUnsignedInt(st_info)), 6," ",true) +" "+
                "DEFAULT" +" "+/*st_other*/
                Utils.formatValue((st_shndx==0?"UND":String.valueOf(st_shndx)), 4," ",false) +" "+
                name);
        index++;
    }
}
