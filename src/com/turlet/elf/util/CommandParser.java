package com.turlet.elf.util;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Create by Silen((myemail)) on 2019/8/28 16:02
 */
public class CommandParser {

    private static CommandParser parser = new CommandParser();

    private boolean fileHeader;
    private boolean programHeaders;
    private boolean sectionHeaders;
    private boolean dynSyms;
    private boolean notes;
    private boolean rodata;
    private boolean help;
    private boolean version;

    private String elfPath;

    private CommandParser() {

    }

    public static CommandParser getInstance() {
        return parser;
    }

    public void parse(String[] args) throws ParseException {
        // System.out.println(Arrays.toString(args));
        if (Objects.isNull(args)) {
            throw new ParseException("arguments is null", -1);
        }
        String argument = args[0];
        if ("-h".equals(argument)) {
            fileHeader = true;
        } else if ("-l".equals(argument)) {
            programHeaders = true;
        } else if ("-S".equals(argument)) {
            sectionHeaders = true;
        } else if ("-e".equals(argument)) {
            fileHeader = true;
            programHeaders = true;
            sectionHeaders = true;
        } else if ("-s".equals(argument)) {
            dynSyms = true;
        } else if ("-n".equals(argument)) {
            notes = true;
        } else if ("-r".equals(argument)) {
            rodata = true;
        } else if ("-H".equals(argument)) {
            help = true;
        } else if ("-v".equals(argument)) {
            version = true;
        }

        if(help || version){
            return;
        }
        if(args.length != 2){
            throw new ParseException("Nothing to do. Miss efl file path argment",-2);
        }
        elfPath = args[1];
    }

    public boolean isFileHeader() {
        return fileHeader;
    }

    public boolean isProgramHeaders() {
        return programHeaders;
    }

    public boolean isSectionHeaders() {
        return sectionHeaders;
    }

    public boolean isDynSyms() {
        return dynSyms;
    }

    public boolean isNotes() {
        return notes;
    }

    public boolean isRodata() {
        return rodata;
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isVersion() {
        return version;
    }

    public String getElfPath() {
        return elfPath;
    }
}
