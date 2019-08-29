package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/23 14:28
 * section header type
 */
public class SH_TYPE {
    /* Legal values for sh_type (section type).  */

    public static final long SHT_NULL = 0;    /* Section header table entry unused */
    public static final long SHT_PROGBITS = 1;     /* Program data */
    public static final long SHT_SYMTAB = 2; /* Symbol table */
    public static final long SHT_STRTAB = 3;/* String table */
    public static final long SHT_RELA = 4;/* Relocation entries with addends */
    public static final long SHT_HASH = 5;/* Symbol hash table */
    public static final long SHT_DYNAMIC = 6;/* Dynamic linking information */
    public static final long SHT_NOTE = 7;/* Notes */
    public static final long SHT_NOBITS = 8;/* Program space with no data (bss) */
    public static final long SHT_REL = 9;/* Relocation entries, no addends */
    public static final long SHT_SHLIB = 10;   /* Reserved */
    public static final long SHT_DYNSYM = 11;  /* Dynamic linker symbol table */
    public static final long SHT_INIT_ARRAY = 14;     /* Array of constructors */
    public static final long SHT_FINI_ARRAY = 15;    /* Array of destructors */
    public static final long SHT_PREINIT_ARRAY = 16;   /* Array of pre-constructors */
    public static final long SHT_GROUP = 17;/* Section group */
    public static final long SHT_SYMTAB_SHNDX = 18;    /* Extended section indeces */
    public static final long SHT_NUM = 19;/* Number of defined types.  */
    public static final long SHT_LOOS = 0x60000000;   /* Start OS-specific.  */
    public static final long SHT_GNU_ATTRIBUTES = 0x6ffffff5;   /* Object attributes.  */
    public static final long SHT_GNU_HASH = 0x6ffffff6;  /* GNU-style hash table.  */
    public static final long SHT_GNU_LIBLIST = 0x6ffffff7; /* Prelink library list */
    public static final long SHT_CHECKSUM = 0x6ffffff8; /* Checksum for DSO content.  */
    public static final long SHT_LOSUNW = 0x6ffffffa;/* Sun-specific low bound.  */
    //public static final long SHT_SUNW_move = 0x6ffffffa;
    public static final long SHT_SUNW_COMDAT = 0x6ffffffb;
    public static final long SHT_SUNW_syminfo = 0x6ffffffc;
    public static final long SHT_GNU_verdef = 0x6ffffffd;   /* Version definition section.  */
    public static final long SHT_GNU_verneed = 0x6ffffffe;  /* Version needs section.  */
    public static final long SHT_GNU_versym = 0x6fffffff; /* Version symbol table.  */
    //public static final long SHT_HISUNW = 0x6fffffff;/* Sun-specific high bound.  */
    //public static final long SHT_HIOS = 0x6fffffff;/* End OS-specific type */
    public static final long SHT_LOPROC = 0x70000000;/* Start of processor-specific */
    public static final long SHT_HIPROC = 0x7fffffff;/* End of processor-specific */
    public static final long SHT_LOUSER = 0x80000000;/* Start of application-specific */
    public static final long SHT_HIUSER = 0x8fffffff;/* End of application-specific */

    //arm
    public static final long SHT_ARM_EXIDX = 0x70000001;
    public static final long SHT_ARM_PREEMPTMAP = 0x70000002;
    public static final long SHT_ARM_ATTRIBUTES = 0x70000003;
    public static final long SHT_ARM_DEBUGOVERLAY = 0x70000004;
    public static final long SHT_ARM_OVERLAYSECTION = 0x70000005;

    //mips
    public static final long MIPS_LIBLIST = 0x70000001;
    public static final long MIPS_CONFLICT = 0x70000002;
    public static final long MIPS_GPTAB = 0x70000003;
    public static final long MIPS_UCODE = 0x70000004;
    public static final long MIPS_DEBUG = 0x70000005;
    public static final long MIPS_REGINFO = 0x70000006;

    private static Map<Long, String> map = new HashMap<>();

    static {
        map.put(SHT_NULL, "NULL(0)");
        map.put(SHT_PROGBITS, "PROGBITS(1)");
        map.put(SHT_SYMTAB, "SYMTAB(2)");
        map.put(SHT_STRTAB, "STRTAB(3)");
        map.put(SHT_RELA, "RELA(4)");
        map.put(SHT_HASH, "HASH(5)");
        map.put(SHT_DYNAMIC, "DYNAMIC(6)");
        map.put(SHT_NOTE, "NOTE(7)");
        map.put(SHT_NOBITS, "NOBITS(8)");
        map.put(SHT_REL, "REL(9)");
        map.put(SHT_SHLIB, "SHLIB(10)");
        map.put(SHT_DYNSYM, "DYNSYM(11)");
        map.put(SHT_INIT_ARRAY, "INIT_ARRAY(14)");
        map.put(SHT_FINI_ARRAY, "FINI_ARRAY(15)");
        map.put(SHT_PREINIT_ARRAY, "PREINIT_ARRAY(16)");
        map.put(SHT_GROUP, "GROUP(17)");
        map.put(SHT_SYMTAB_SHNDX, "SYMTAB_SHNDX(18)");
        map.put(SHT_NUM, "NUM(19)");
        map.put(SHT_LOOS, "LOOS(0x60000000)");
        map.put(SHT_GNU_ATTRIBUTES, "GNU_LOOS(0x6ffffff5)");
        map.put(SHT_GNU_HASH, "GNU_HASH(0x6ffffff6)");
        map.put(SHT_GNU_LIBLIST, "LIBLIST(0x6ffffff7)");
        map.put(SHT_CHECKSUM, "CHECKSUM(0x6ffffff8)");
        map.put(SHT_LOSUNW, "LOSUNW(0x6ffffffa)");
        //map.put(SHT_SUNW_move, "SUNW_move(0x6ffffffa)");
        map.put(SHT_SUNW_COMDAT, "SUNW_COMDAT(0x6ffffffb)");
        map.put(SHT_SUNW_syminfo, "SUNW_syminfo(0x6ffffffc)");
        map.put(SHT_GNU_verdef, "GNU_verdef(0x6ffffffd)");
        map.put(SHT_GNU_verneed, "GNU_verneed(0x6ffffffe)");
        map.put(SHT_GNU_versym, "GNU_versym(0x6fffffff)");
        //map.put(SHT_HISUNW, "HISUNW(0x6fffffff)");
        map.put(SHT_LOPROC, "LOPROC(0x70000000)");
        map.put(SHT_HIPROC, "HIPROC(0x7fffffff)");
        map.put(SHT_LOUSER, "LOUSER(0x80000000)");
        map.put(SHT_HIUSER, "HIUSER(0x8fffffff)");

        map.put(SHT_ARM_EXIDX, "ARM_EXIDX(0x70000001)");
        map.put(SHT_ARM_PREEMPTMAP, "ARM_PREEMPTMAP(0x70000002)");
        map.put(SHT_ARM_ATTRIBUTES, "ARM_ATTRIBUTES(0x70000003)");
        map.put(SHT_ARM_DEBUGOVERLAY, "ARM_DEBUGOVERLAY(0x70000004)");
        map.put(SHT_ARM_OVERLAYSECTION, "ARM_OVERLAYSECTION(0x70000005)");
    }

    public static String get(Long type) {
        return map.get(type);
    }
}
