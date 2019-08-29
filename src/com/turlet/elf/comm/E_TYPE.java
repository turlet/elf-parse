package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/22 11:01
 * 目标文件标识，表示文件以哪种方式加载
 */
public final class E_TYPE{

    /*未知目标文件格式*/
    public static final int ELF_ET_NONE  = 0x0;
    /*可重定位文件*/
    public static final int ELF_ET_REL =  0x1;
    /*可执行文件*/
    public static final int ELF_ET_EXEC =  0x2;
    /*共享目标文件*/
    public static final int ELF_ET_DYN  = 0x3;
    /*Core文件(转储格式)*/
    public static final int ELF_ET_CORE  = 0x4;
    /*特定处理器文件*/
    public static final int ELF_ET_LOOS  = 0xFE00;
    public static final int ELF_ET_HIOS  = 0xFEFF;
    /*特定处理器文件*/
    public static final int ELF_ET_LOPROC =  0xFF00;
    /*特定处理器文件*/
    public static final int ELF_ET_HIPROC  = 0xFFFF;

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(ELF_ET_NONE, "未知目标文件格式-No file type(0x0)");
        map.put(ELF_ET_REL, "可重定位文件-Relocatable file(0x1)");
        map.put(ELF_ET_EXEC, "可执行文件-Executable file(0x2)");
        map.put(ELF_ET_DYN, "共享目标文件-DYN (Shared object file)(0x3)");
        map.put(ELF_ET_CORE, "Core文件(转储格式)-Core file(0x4)");
        map.put(ELF_ET_LOOS, "特定处理器文件-OS-specific range start(0xFE00)");
        map.put(ELF_ET_HIOS, "特定处理器文件-OS-specific range end(0xFEFF)");
        map.put(ELF_ET_LOPROC, "特定处理器文件-Processor-specific range start(0xFF00)");
        map.put(ELF_ET_HIPROC, "特定处理器文件-Processor-specific range end(0xFFFF)");
    }

    public static String get(Integer type){
        return map.get(type);
    }
}
