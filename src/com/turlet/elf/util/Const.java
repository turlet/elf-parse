package com.turlet.elf.util;

/**
 * Create by Silen((myemail)) on 2019/8/21 15:20
 */
public interface Const {

    /** 魔数 前4位 用于判断文件是否是elf文件 */
    byte[] MAGIC_4 = {0x7F, 0x45, 0x4c, 0x46};

    //打印sh_name字段最大显示字符
    int SH_NAME_LENGTH = 20;
    int SH_TYPE_LENGTH = 18;

    String VERSION = "v1.1";
}
