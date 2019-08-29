package com.turlet.elf.bean;

import com.turlet.elf.util.Const;
import com.turlet.elf.util.Log;
import com.turlet.elf.util.Utils;

/**
 * Create by Silen((myemail)) on 2019/8/23 11:40
 * <pre>
 *     节区(Sections)
 * 节区包含目标文件的所有信息,节区满足一下条件
 * 目标文件中的每个节区都有对应的节区头部描述它，反过来，有节区头部不意味着有节区.
 * 每个节区占用文件中一个连续字节区域(这个区域可能长度为 0)
 * 文件中的节区不能重叠，不允许一个字节存在于两个节区中的情况发生.
 * 目标文件中可能包含非活动空间(INACTIVE SPACE)。这些区域不属于任何头部和节区，其内容未指定.
 * </pre>
 */
public class ElfSectionHeader32 {

    //给出节区名称。是节区头部字符串表节区(Section Header StringTable Section)的索引。名字是一个 NULL 结尾的字符串。
    public int sh_name;
    //为节区的内容和语义进行分类。参见节区类型。
    public long sh_type;
    //节区支持 1 位形式的标志，这些标志描述了多种属性。
    public int sh_flags;
    //如果节区将出现在进程的内存映像中，此成员给出节区的第一个字节应处的位置。否则，此字段为 0。
    public long sh_addr;
    //此成员的取值给出节区的第一个字节与文件头之间的偏移.
    public long sh_offset;
    //长度为4位，先不考虑符号问题
    //此成员给出节区的长度(字节数)。
    public int sh_size;
    //此成员给出节区头部表索引链接。其具体的解释依赖于节区类型
    public long sh_link;
    //此成员给出附加信息，其解释依赖于节区类型。
    public long sh_info;
    //某些节区带有地址对齐约束。例如，如果一个节区保存一个doubleword，那么系统必须保证整个节区能够按双字对齐。sh_addr对
    // sh_addralign 取模，结果必须为 0。目前仅允许取为 0 和 2的幂次数。数值 0 和 1 表示节区没有对齐约束。
    public long sh_addralign;
    //某些节区中包含固定大小的项目，如符号表。对于这类节区，此成员给出每个表项的长度节数。
    // 如果节区中并不包含固定长度表项的表格，此成员取值为 0。
    public long sh_entsize;

    /* 以下字符为格式化输出字段*/
    public String name;
    public String type;
    public String flags;

    //记录输出当前数组位置
    private static int index = 0;

    public static ElfSectionHeader32 parse(Elf32.Elf32_Shdr shdr){

        ElfSectionHeader32 header = new ElfSectionHeader32();
        header.sh_name = Utils.byte4ToInt(shdr.sh_name);
        header.sh_type = Utils.byte4ToLong(shdr.sh_type);
        header.sh_flags = Utils.byte4ToInt(shdr.sh_flags);
        header.sh_addr = Utils.byte4ToLong(shdr.sh_addr);
        header.sh_offset = Utils.byte4ToLong(shdr.sh_offset);
        header.sh_size = Utils.byte4ToInt(shdr.sh_size);
        header.sh_link = Utils.byte4ToLong(shdr.sh_link);
        header.sh_info = Utils.byte4ToLong(shdr.sh_info);
        header.sh_addralign = Utils.byte4ToLong(shdr.sh_addralign);
        header.sh_entsize = Utils.byte4ToLong(shdr.sh_entsize);

        //重置下标
        index = 0;
        return header;
    }



    public static void printTableTitle(){
        Log.i("[Nr] Name                 Type               Addr     Off    Size   ES Flg Lk Inf Al");
    }

    public static void printInfo(){
        Log.i("Key to Flags:");
        Log.i("  W (write), A (alloc), X (execute), M (merge), S (strings), I (info),");
        Log.i("  L (link order), O (extra OS processing required), G (group), T (TLS),");
        Log.i("  C (compressed), x (unknown), o (OS specific), E (exclude),");
        Log.i("  y (noread), p (processor specific)");
    }

    public void print(){
        Log.i("["+(index > 9 ? index : " "+ index)+"] "+ Utils.formatValue(name, Const.SH_NAME_LENGTH," ",true) +"  "+
                Utils.formatValue(type, Const.SH_TYPE_LENGTH," ",true) +" "+
                Utils.formatValue(Long.toHexString(sh_addr),8,"0",false)+" "+
                Utils.formatValue(Long.toHexString(sh_offset),6,"0",false)+ " "+
                Utils.formatValue(Long.toHexString(sh_size),6,"0",false) +" "+
                Utils.formatValue(Long.toHexString(sh_entsize),2,"0",false) + " "+
                Utils.formatValue(flags,3," ",true)+ "  "+
                sh_link + "  "+ sh_info+ "  "+sh_addralign);
        index++;
    }

}
