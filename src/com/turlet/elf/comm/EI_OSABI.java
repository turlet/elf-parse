package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/22 16:22
 * <pre>
 *     Byte e_ident[EI_OSABI] identifies the OS- or ABI-specific ELF extensions used by this file. Some fields in other
 *     ELF structures have flags and values that have operating system and/or ABI specific meanings; the interpretation
 *     of those fields is determined by the value of this byte. If the object file does not use any extensions, it is
 *     recommended that this byte be set to 0. If the value for this byte is 64 through 255, its meaning depends on the
 *     value of the e_machine header member. The ABI processor supplement for an architecture can define its own
 *     associated set of values for this byte in this range. If the processor supplement does not specify a set of
 *     values, one of the following values shall be used, where 0 can also be taken to mean unspecified.
 * </pre>
 */
public class EI_OSABI {

    public static final int ELFOSABI_NONE = 0;
    public static final int ELFOSABI_HPUX = 1;
    public static final int ELFOSABI_NETBSD = 2;
    public static final int ELFOSABI_LINUX = 3;
    public static final int ELFOSABI_SOLARIS = 6;
    public static final int ELFOSABI_AIX = 7;
    public static final int ELFOSABI_IRIX = 8;
    public static final int ELFOSABI_FREEBSD = 9;
    public static final int ELFOSABI_TRU64 = 10;
    public static final int ELFOSABI_MODESTO = 11;
    public static final int ELFOSABI_OPENBSD = 12;
    public static final int ELFOSABI_OPENVMS = 13;
    public static final int ELFOSABI_NSK = 14;
    public static final int ELFOSABI_AROS = 15;
    public static final int ELFOSABI_FENIXOS = 16;
    public static final int ELFOSABI_CLOUDABI = 17;
    public static final int ELFOSABI_OPENVOS = 18;
    public static final int ELFOSABI_ARM_AEABI  = 64 ;
    public static final int ELFOSABI_ARM  = 97 ;
    public static final int ELFOSABI_STANDALONE  = 255;
    //64-255	Architecture-specific value range

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(ELFOSABI_NONE, "UNIX - System V(0)");
        map.put(ELFOSABI_HPUX, "HP/UX(1)");
        map.put(ELFOSABI_NETBSD, "NetBSD(2)");
        map.put(ELFOSABI_LINUX, "Linux(3)");
        map.put(ELFOSABI_SOLARIS, "Solaris(6)");
        map.put(ELFOSABI_AIX, "AIX(7)");
        map.put(ELFOSABI_IRIX, "Irix(8)");
        map.put(ELFOSABI_FREEBSD, "FreeBSD(9)");
        map.put(ELFOSABI_TRU64, "TRU64(10)");
        map.put(ELFOSABI_MODESTO, "Modesto(11)");
        map.put(ELFOSABI_OPENBSD, "OpenBSD(12)");
        map.put(ELFOSABI_OPENVMS, "OpenVMS(13)");
        map.put(ELFOSABI_NSK, "Hewlett-Packard Non-Stop Kernel(14)");
        map.put(ELFOSABI_AROS, "Amiga Research OS(15)");
        map.put(ELFOSABI_FENIXOS, "The FenixOS highly scalable multi-core OS(16)");
        map.put(ELFOSABI_CLOUDABI, "Nuxi CloudABI(17)");
        map.put(ELFOSABI_OPENVOS, "Stratus Technologies OpenVOS(18)");
        map.put(ELFOSABI_ARM_AEABI, "ARM EABI(64)");
        map.put(ELFOSABI_ARM, "ARM(97)");
        map.put(ELFOSABI_STANDALONE, "Standalone (embedded) application(255)");
        //64-255	Architecture-specific value range
    }

    public static String get(Integer version) {
        return map.get(version);
    }
}
