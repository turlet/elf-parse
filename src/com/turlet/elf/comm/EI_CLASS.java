package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/21 17:08
 * 标识文件的类别，表示目标文件运行在目标机器的类别
 */
public final class EI_CLASS {
    /**
     * 非法类别
     */
    public static final int ELFCLASSNONE = 0x0;
    /**
     * 32位目标，可以在32位cpu上运行
     */
    public static final int ELFCLASS32 = 0x1;
    /**
     * 64位目标，可以在64位的cpu上运行
     */
    public static final int ELFCLASS64 = 0x2;

    public static final int ELFCLASSNUM = 0x3;

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(ELFCLASSNONE, "非法类别-Invalid class(0)");
        map.put(ELFCLASS32, "32位目标-32-bit objects(1)");
        map.put(ELFCLASS64, "64位目标-64-bit objects(2)");
        map.put(ELFCLASSNUM, "NUM(3)");
    }

    public static String get(Integer elfClass){
        return map.get(elfClass);
    }
}
