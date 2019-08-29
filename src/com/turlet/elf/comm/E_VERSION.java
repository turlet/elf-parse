package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/21 18:46
 * 目标文件版本
 */
public final class E_VERSION {

    public static final long EV_NOME = 0x0L;
    /**
     * 当前版本
     */
    public static final long EV_CURRENT = 0x1L;

    private static Map<Long, String> map = new HashMap<>();

    static {
        map.put(EV_NOME, "非法版本(0)");
        map.put(EV_CURRENT, "当前版本(1)");
    }

    public static String get(Long version){
        return map.get(version);
    }
}
