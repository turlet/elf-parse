package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/23 18:49
 */
public class PH_TYPE {

    public static final long PHT_NULL = 0;
    public static final long PHT_LOAD = 1;
    public static final long PHT_DYNAMIC = 2;
    public static final long PHT_INTERP = 3;
    public static final long PHT_NOTE = 4;
    public static final long PHT_SHLIB = 5;
    public static final long PHT_PHDR = 6;
    public static final long PHT_TLS = 7;
    public static final long PHT_LOOS = 0x60000000;
    public static final long PHT_HIOS = 0x6fffffff;
    public static final long PHT_LOPROC = 0x70000000;
    public static final long PHT_HIPROC = 0x7fffffff;
    public static final long PHT_GNU_EH_FRAME = 0x6474e550;
    public static final long PHT_SUNW_EH_FRAME = 0x6474e550;
    public static final long PHT_SUNW_UNWIND = 0x6464e550;
    public static final long PHT_GNU_STACK = 0x6474e551;
    public static final long PHT_GNU_RELRO = 0x6474e552;
    public static final long PHT_OPENBSD_RANDOMIZE = 0x65a3dbe6;
    public static final long PHT_OPENBSD_WXNEEDED = 0x65a3dbe7;
    public static final long PHT_OPENBSD_BOOTDATA = 0x65a41be6;
    public static final long PHT_ARM_ARCHEXT = 0x70000000;
    public static final long PHT_ARM_EXIDX = 0x70000001;
    public static final long PHT_ARM_UNWIND = 0x70000001;
    public static final long PHT_MIPS_REGINFO = 0x70000000;
    public static final long PHT_MIPS_RTPROC = 0x70000001;
    public static final long PHT_MIPS_OPTIONS = 0x70000002;
    public static final long PHT_MIPS_ABIFLAGS = 0x70000003;

    private static Map<Long, String> map = new HashMap<>();

    static {
        map.put(PHT_NULL, "NULL(0)");
        map.put(PHT_LOAD, "LOAD(1)");
        map.put(PHT_DYNAMIC, "DYNAMIC(2)");
        map.put(PHT_INTERP, "INTERP(3)");
        map.put(PHT_NOTE, "NOTE(4)");
        map.put(PHT_SHLIB, "SHLIB(5)");
        map.put(PHT_PHDR, "PHDR(6)");
        map.put(PHT_TLS, "LOOS(1)");
        map.put(PHT_LOOS, "HIOS(1)");
        map.put(PHT_HIOS, "LOPROC(1)");
//        map.put(PHT_LOPROC, "HIPROC(1)");
        map.put(PHT_HIPROC, "HIPROC(1)");
//        map.put(PHT_GNU_EH_FRAME, "GNU_EH_FRAME(1)");
//        map.put(PHT_SUNW_EH_FRAME, "SUNW_EH_FRAME(1)");
        map.put(PHT_SUNW_UNWIND, "SUNW_UNWIND(1)");
        map.put(PHT_GNU_STACK, "GNU_STACK(1)");
        map.put(PHT_GNU_RELRO, "GNU_RELRO(1)");
        map.put(PHT_OPENBSD_RANDOMIZE, "OPENBSD_RANDOMIZE(1)");
        map.put(PHT_OPENBSD_WXNEEDED, "OPENBSD_WXNEEDED(1)");
        map.put(PHT_OPENBSD_BOOTDATA, "OPENBSD_BOOTDATA(1)");
        map.put(PHT_ARM_ARCHEXT, "ARCHEXT(1)");
        map.put(PHT_ARM_EXIDX, "EXIDX(1)");
//        map.put(PHT_ARM_UNWIND, "UNWIND(1)");
        //还没处理其它cpu的类型
//        map.put(PHT_MIPS_REGINFO, "MIPS_REGINFO(1)");
//        map.put(PHT_MIPS_RTPROC, "MIPS_RTPROC(1)");
        map.put(PHT_MIPS_OPTIONS, "MIPS_OPTIONS(1)");
        map.put(PHT_MIPS_ABIFLAGS, "MIPS_ABIFLAGS(1)");
    }

    public static String get(Long type){
        return map.get(type);
    }
}
