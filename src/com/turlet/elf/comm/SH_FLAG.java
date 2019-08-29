package com.turlet.elf.comm;

import com.turlet.elf.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/23 16:21
 */
public class SH_FLAG {

// Legal values for sh_flags (section flags).

    public static final long SHF_WRITE = (1 << 0);   // Writable
    public static final long SHF_ALLOC = (1 << 1);  // Occupies memory during execution
    public static final long SHF_EXECINSTR = (1 << 2);   // Executable
    public static final long SHF_MERGE = (1 << 4);// Might be merged
    public static final long SHF_STRINGS = (1 << 5);// Contains nul-terminated strings
    public static final long SHF_INFO_LINK = (1 << 6);   // `sh_info' contains SHT index
    public static final long SHF_LINK_ORDER = (1 << 7);  // Preserve order after combining
    public static final long SHF_OS_NONCONFORMING = (1 << 8); // Non-standard OS specific handling required
    public static final long SHF_GROUP = (1 << 9);// Section is member of a group.
    public static final long SHF_TLS = (1 << 10);// Section hold thread-local data.
    public static final long SHF_COMPRESSED = (1 << 11);  // Section with compressed data.
    public static final long SHF_MASKOS = 0x0ff00000;// OS-specific.
    public static final long SHF_MASKPROC = 0xf0000000; // Processor-specific
    public static final long SHF_ORDERED = (1 << 30);// Special ordering requirement (Solaris).
    public static final long SHF_EXCLUDE = (1 << 31); // Section is excluded unless referenced or allocated (Solaris).

// Section compression header.  Used when SHF_COMPRESSED is set.

    public static String get(Integer flag) {
        StringBuilder sb = new StringBuilder();

        if(SHF_WRITE == (flag&SHF_WRITE)){
            sb.append("W");
        }
        if(SHF_ALLOC == (flag&SHF_ALLOC)){
            sb.append("A");
        }
        if(SHF_EXECINSTR == (flag&SHF_EXECINSTR)){
            sb.append("X");
        }
        if(SHF_MERGE == (flag&SHF_MERGE)){
            sb.append("M");
        }
        if(SHF_STRINGS == (flag&SHF_STRINGS)){
            sb.append("S");
        }
        if(SHF_INFO_LINK == (flag&SHF_INFO_LINK)){
            sb.append("I");
        }
        if(SHF_LINK_ORDER == (flag&SHF_LINK_ORDER)){
            sb.append("L");
        }
        if(SHF_OS_NONCONFORMING == (flag&SHF_OS_NONCONFORMING)){
            sb.append("N");
        }
        if(SHF_GROUP == (flag&SHF_GROUP)){
            sb.append("G");
        }
        if(SHF_TLS == (flag&SHF_TLS)){
            sb.append("T");
        }
        if(SHF_COMPRESSED == (flag&SHF_COMPRESSED)){
            sb.append("C");
        }
        if(SHF_MASKOS == (flag&SHF_MASKOS)){
            sb.append("o");
        }
        if(SHF_MASKPROC == (flag&SHF_MASKPROC)){
            sb.append("p");
        }
        if(SHF_ORDERED == (flag&SHF_ORDERED)){
            sb.append("O");
        }
        if(SHF_EXCLUDE == (flag&SHF_EXCLUDE)){
            sb.append("E");
        }
        return sb.toString();
    }

}
