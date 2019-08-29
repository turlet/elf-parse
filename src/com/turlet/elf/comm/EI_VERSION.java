package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/21 18:46
 * elf头部版本号码,固定值01 EV_CURRENT
 */
public final class EI_VERSION {

    public static final int EI_NOME = 0x0;
    /**
     * 文件版本,固定值01 EV_CURRENT
     */
    public static final int EI_CURRENT = 0x1;

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(EI_NOME, "非法版本(0)");
        map.put(EI_CURRENT, "当前版本(1)");
    }

    public static String get(Integer version){
        return map.get(version);
    }
}
