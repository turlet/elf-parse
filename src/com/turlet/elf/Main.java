package com.turlet.elf;

import com.turlet.elf.bean.Elf32;
import com.turlet.elf.bean.Magic;
import com.turlet.elf.comm.EI_CLASS;
import com.turlet.elf.util.CommandParser;
import com.turlet.elf.util.Const;
import com.turlet.elf.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;

/**
 * 程序主入口
 * https://www.jianshu.com/p/d14259384be0
 * https://upload-images.jianshu.io/upload_images/11441860-f965c53cdaa8fb81.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/775/format/webp
 */
public class Main {

    public static void main(String[] args) throws Exception {
        try {
            CommandParser.getInstance().parse(args);
            if (CommandParser.getInstance().isHelp()) {
                usage();
                return;
            }
            if (CommandParser.getInstance().isVersion()) {
                System.err.println(Const.VERSION);
                return;
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            usage();
            return;
        }
        File elfFile = new File(CommandParser.getInstance().getElfPath());
        if (!elfFile.exists()) {
            System.err.println(CommandParser.getInstance().getElfPath() + " file is not exists.");
            usage();
            return;
        }

        RandomAccessFile fin = new RandomAccessFile(elfFile, "r");

        // 获取通道
        FileChannel fileChannel = fin.getChannel();
        // 创建缓冲区
        ByteBuffer magicBuf = ByteBuffer.allocate(Elf32.EI_NIDENT);
        fileChannel.read(magicBuf);
        Magic magic = new Magic();
        magic.init(magicBuf);
        magicBuf.clear();
        if (magic.ei_class == EI_CLASS.ELFCLASS32) {
            //进入32位解析器
            Elf32Parse parse = new Elf32Parse(fileChannel, magic);
            parse.parseElfHeader();
            parse.parseElfSectionHeaderTable();
            parse.parseElfProgramHeaderTable();
        } else if (magic.ei_class == EI_CLASS.ELFCLASS64) {
            //进入64位解析器
            Elf64Parse parse = new Elf64Parse(fileChannel, magic);
            parse.parseElfHeader();
            parse.parseElfSectionHeaderTable();
            parse.parseElfProgramHeaderTable();
        } else {
            //无法解析
            Log.e(EI_CLASS.get(magic.ei_class));
        }
    }

    private static void usage() {
        System.err.println("ElfParse " + Const.VERSION + "- a tool for parse elf format file\n" +
                "Copyright 2019 Silen\n");
        System.err.println("Usage: elfparse <option(s)> elf-file\n" +
                " Display information about the contents of ELF format files\n" +
                " Options are:");
        System.err.println("  -h --file-header       Display the ELF file header");
        System.err.println("  -l --program-headers   Display the program headers\n" +
                "     --segments          An alias for --program-headers");
        System.err.println("  -S --section-headers   Display the sections' header\n" +
                "     --sections          An alias for --section-headers");
        System.err.println("  -e --headers           Equivalent to: -h -l -S");
        System.err.println("  -s --syms              Display the symbol table\n" +
                "     --symbols           An alias for --syms\n" +
                "     --dyn-syms             Display the dynamic symbol table");
        System.err.println("  -n --notes             Display the core notes (if present)");
        System.err.println("  -r --rodata             Display the rodata constant");
        System.err.println("  -H --help              Display this information");
        System.err.println("  -v --version           Display the version number of elfparse");
    }
}
