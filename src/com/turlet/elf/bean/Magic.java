package com.turlet.elf.bean;

import com.turlet.elf.comm.EI_CLASS;
import com.turlet.elf.comm.EI_DATA;
import com.turlet.elf.comm.EI_OSABI;
import com.turlet.elf.comm.EI_VERSION;
import com.turlet.elf.util.Log;

import java.nio.ByteBuffer;

/**
 * Create by Silen((myemail)) on 2019/8/21 16:56
 * e_ident数组给出了ELF的一些标识信息
 */
public class Magic {

    private static final byte ELFMAG0  = 0x7f;
    private static final byte ELFMAG1  = 'E';
    private static final byte ELFMAG2  = 'L';
    private static final byte ELFMAG3  = 'F';

    private static final int ELF_EI_MAG0 = 0;
    private static final int ELF_EI_MAG1 = 1;
    private static final int ELF_EI_MAG2 = 2;
    private static final int ELF_EI_MAG3 = 3;
    private static final int ELF_EI_CLASS = 4;
    private static final int ELF_EI_DATA = 5;
    private static final int ELF_EI_VERSION = 6;
    private static final int ELF_EI_OSABI = 7;
    private static final int ELF_EI_ABIVERSION = 8;
    private static final int ELF_EI_PAD = 9;
    private static final int EI_NIDENT = 16;
    
    /**
     * Magic： 7f 45 4c 46 01 01 01 00 00 00 00 00 00 00 00 00
     */
    public byte[] magic;

    /**
     * 第五个字节标识ELF文件是32位（01）还是64位（02）的
     */
    public int ei_class;

    /**
     * 第六个字节标识该ELF文件字节序是小端（01）还是大端（02）的。
     */
    public int ei_data;

    /**
     * 第七个字节指示ELF文件的版本号，一般是01。
     */
    public int ei_version;

    public int ei_osabi;

    public int ei_abiversion;
    
    /**
     * 第八到第十五个字节补齐字节开始处,解析过程中可以忽略
     */
    public byte[] ei_pad;

    /**
     * e_ident[]的大小，但一直为0,解析过程中可以忽略
     */
    public byte ei_nident;

    public boolean init(ByteBuffer magicBuf){
//        byte[] magic_4 = new byte[4];
        //从头开始重读
//        magicBuf.rewind();
        //将写模式转换成读模式
//        magicBuf.flip();
//        magicBuf.get(magic_4);
//        if(!Arrays.equals(Const.MAGIC_4, magic_4)){
//            Log.e("不是elf文件");
//            return false;
//        }
        magic = new byte[Elf32.EI_NIDENT];
        magicBuf.rewind();
        //magicBuf.flip();
        magicBuf.get(magic);
        if (!(ELFMAG0 == magic[ELF_EI_MAG0] && ELFMAG1 == magic[ELF_EI_MAG1]
                && ELFMAG2 == magic[ELF_EI_MAG2] && ELFMAG3 == magic[ELF_EI_MAG3])) {
            Log.e("不是elf文件");
            return false;
        }
        ei_class = Byte.toUnsignedInt(magic[ELF_EI_CLASS]);
        ei_data = Byte.toUnsignedInt(magic[ELF_EI_DATA]);
        ei_version = Byte.toUnsignedInt(magic[ELF_EI_VERSION]);
        ei_osabi = Byte.toUnsignedInt(magic[ELF_EI_OSABI]);
        ei_abiversion = Byte.toUnsignedInt(magic[ELF_EI_ABIVERSION]);
        return true;
    }

    private void printMagic(){
        StringBuilder sb = new StringBuilder("  魔数(Magic): ");
        for (byte data : magic){
            sb.append(Integer.toHexString(Byte.toUnsignedInt(data))).append(" ");
        }
        Log.i(sb.toString());
    }

    public void print(){
        //Arrays.asList(magic).stream().forEach(x -> System.out.println(x));
        Log.i("ELF Header:");
        printMagic();
        Log.i("  文件类别(Class)： "+ EI_CLASS.get(ei_class));
        Log.i("  数据编码(Data)： "+ EI_DATA.get(ei_data));
        Log.i("  头部版本(Version)： "+ EI_VERSION.get(ei_version));
        Log.i("  OS/ABI： "+ EI_OSABI.get(ei_osabi));
        Log.i("  ABI版本(ABI Version)： "+ ei_abiversion);
    }
}
