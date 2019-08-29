package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/27 15:46
 * <pre>
 *     全局符号与弱符号之间的区别主要有两点:
 * (1). 当链接编辑器组合若干可重定位的目标文件时，不允许对同名的 STB_GLOBAL 符号给出多个定义。 另一方面如果一个已定义的
 * 全局符号已经存在，出现一个同名的弱符号并 不会产生错误。链接编辑器尽关心全局符号，忽略弱符号。 类似地，如果一个公共符号
 * (符号的 st_shndx 中包含 SHN_COMMON)，那 么具有相同名称的弱符号出现也不会导致错误。链接编辑器会采纳公共定 义，而忽略弱定义。
 *
 * (2). 当链接编辑器搜索归档库(archive libraries)时，会提取那些包含未定 义全局符号的档案成员。成员的定义可以是全局符号，
 * 也可以是弱符号。 连接编辑器不会提取档案成员来满足未定义的弱符号。 未能解析的弱符号取值为 0。
 * </pre>
 */
public class DYNSYM_BIND {

    /**
     * 局部符号在包含该符号定义的目标文件以外不可见。相同名 称的局部符号可以存在于多个文件中，互不影响
     */
    public static final int STB_LOCAL = 0x0;
    /**
     * 全局符号对所有将组合的目标文件都是可见的。一个文件中 对某个全局符号的定义将满足另一个文件对相同全局符号的 未定义引用
     */
    public static final int STB_GLOBAL = 0x1;

    /**
     * 弱符号与全局符号类似，不过他们的定义优先级比较低
     */
    public static final int STB_WEAK = 0x2;

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(STB_LOCAL, "LOCAL");
        map.put(STB_GLOBAL, "GLOBAL");
        map.put(STB_WEAK, "WEAK");
    }

    public static String get(Integer st_info){
        //define ELF32_ST_BIND(i)    ((i)>>4)        //获取绑定信息
        Integer st_bind = st_info >> 4;
        return map.get(st_bind);
    }

}
