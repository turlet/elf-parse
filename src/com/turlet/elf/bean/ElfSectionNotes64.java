package com.turlet.elf.bean;

import com.turlet.elf.util.Log;
import com.turlet.elf.util.Utils;

/**
 * Create by Silen((myemail)) on 2019/8/27 19:28
 * ELF notes允许附加任意信息供系统使用。它們大部分情況是被內核文件使用(e_type of ET_CORE),
 *     但是很多projects定义它們自己的扩展。例如，GNU tool chain使用ELF notes將信息从链接器
 *     传递到C库
 *     Note section contents.  Each entry in the note section begins with a header of a fixed form.
 *     http://man7.org/linux/man-pages/man5/elf.5.html
 */
public class ElfSectionNotes64 {

    //name field的長度。在內存中內容會緊跟它之後。name爲NUll的時候代表終止。
    //                例如，如果name是“GNU”，n_namesz就是4.
    public int n_namesz;
    //描述符號字段的長度。在內存中內容會緊跟它之後。
    public int n_descsz;
    // 由name字段的值決定，有可能是下面這些中的一個：
    public int n_type;


    public String names;
    public String descs;

    public static ElfSectionNotes64 parse(Elf64.Elf64_Nhdr nhdr) {
        ElfSectionNotes64 notes = new ElfSectionNotes64();
        notes.n_namesz = Utils.byte4ToInt(nhdr.n_namesz);
        notes.n_descsz = Utils.byte4ToInt(nhdr.n_descsz);
        notes.n_type = Utils.byte4ToInt(nhdr.n_type);
        return notes;
    }

    public static void printTableTitle(long offset, int len){
        Log.i(String.format("Displaying notes found at file offset 0x%s with length 0x%s:",Utils.formatValue(Long.toHexString(offset),8,"0",false),
                Utils.formatValue(Integer.toHexString(len),8,"0",false)));
        Log.i("  Owner                 Data size       Description");
    }

    public void print(){
        Log.i("  "+Utils.formatValue(names,5," ",true) +
                "                 0x"+Utils.formatValue(Integer.toHexString(n_descsz),8,"0",false)+"       "+ getDescription() );
        Log.i("    Version: "+descs);
    }

    //http://man7.org/linux/man-pages/man5/elf.5.html
    private String getDescription(){
        if(names.contains("GNU") && descs.contains("gold")){
            //The desc contains the GNU Gold linker version used.
            return "NT_GNU_GOLD_VERSION (gold version)";
        }else{
            // NT_GNU_ABI_TAG
            //NT_GNU_HWCAP
            //NT_GNU_BUILD_ID
            return "NT_GNU_ABI_TAG";
        }
    }
    @Override
    public String toString() {
        return "ElfSectionNotes32{" +
                "n_namesz=" + n_namesz +
                ", n_descsz=" + n_descsz +
                ", n_type=" + n_type +
                ", names='" + names + '\'' +
                ", descs='" + descs + '\'' +
                '}';
    }
}
