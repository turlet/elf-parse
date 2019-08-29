package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/27 15:46
 */
public class DYNSYM_TYPE {

    /**
     * 符号的类型没有指定
     */
    public static final int STT_NOTYPE = 0x0;
    /**
     * 符号与某个数据对象相关，比如一个变量、数组等等
     */
    public static final int STT_OBJECT = 0x1;

    /**
     * 符号与某个函数或者其他可执行代码相关
     */
    public static final int STT_FUNC = 0x2;

    /**
     * 符号与某个节区相关。这种类型的符号表项主要用于重定 位，通常具有 STB_LOCAL 绑定。
     */
    public static final int STT_SECTION = 0x3;

    /**
     * 传统上，符号的名称给出了与目标文件相关的源文件的名 称。文件符号具有 STB_LOCAL 绑定，其节区索引是SHN_ABS，
     * 并且它优先于文件的其他 STB_LOCAL 符号 (如果有的话)
     */
    public static final int STT_FILE = 0x4;

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(STT_NOTYPE, "NOTYPE");
        map.put(STT_OBJECT, "OBJECT");
        map.put(STT_FUNC, "FUNC");
        map.put(STT_SECTION, "SECTION");
        map.put(STT_FILE, "FILE");
    }

    public static String get(Integer st_info){
        //define ELF32_ST_TYPE(i)    ((i)&0xf)       //获取符号类型
        Integer st_type = st_info&0xf;
        return map.get(st_type);
    }

}
