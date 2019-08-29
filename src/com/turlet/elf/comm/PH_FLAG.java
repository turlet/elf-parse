package com.turlet.elf.comm;

/**
 * Create by Silen((myemail)) on 2019/8/26 10:17
 */
public class PH_FLAG {

    /* 可读的段 */
    public static final long PF_R =0x4;
    /* 可写的段 */
    public static final long PF_W =0x2;
    /*可执行的段*/
    public static final long PF_E =0x1;
    /*保留*/
    public static final long PF_MASKPROC= 0xf0000000;

    public static String get(long flag) {
        StringBuilder sb = new StringBuilder();

        if (PF_R == (flag & PF_R)) {
            sb.append("R");
        }else{
            sb.append(" ");
        }
        if (PF_W == (flag & PF_W)) {
            sb.append("W");
        }else{
            sb.append(" ");
        }
        if (PF_E == (flag & PF_E)) {
            sb.append("E");
        }
        return sb.toString();
    }
}
