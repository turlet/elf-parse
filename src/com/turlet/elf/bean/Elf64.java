package com.turlet.elf.bean;

/**
 * Create by Silen((myemail)) on 2019/8/21 14:58
 */
public class Elf64 {


    /**
     * #define EI_NIDENT (16)
     * elf头文件的魔数(Magic)长度
     */
    public static final int EI_NIDENT = 0x10;

    /**
     * 头部对象字节长度，占用52位
     */
    public static final int ELF_HDR_LENGTH = 0x40;


    /**
     * typedef struct {
     * unsigned char   e_ident[EI_NIDENT];
     * Elf64_Half      e_type;
     * Elf64_Half      e_machine;
     * Elf64_Word      e_version;
     * Elf64_Addr      e_entry;
     * Elf64_Off       e_phoff;
     * Elf64_Off       e_shoff;
     * Elf64_Word      e_flags;
     * Elf64_Half      e_ehsize;
     * Elf64_Half      e_phentsize;
     * Elf64_Half      e_phnum;
     * Elf64_Half      e_shentsize;
     * Elf64_Half      e_shnum;
     * Elf64_Half      e_shstrndx;
     * } Elf64_Ehdr;
     */
    public static class Elf64_Ehdr {
        public byte[] e_ident = new byte[EI_NIDENT];
        public byte[] e_type = new byte[2];
        public byte[] e_machine = new byte[2];
        public byte[] e_version = new byte[4];
        public byte[] e_entry = new byte[8];
        public byte[] e_phoff = new byte[8];
        public byte[] e_shoff = new byte[8];
        public byte[] e_flags = new byte[4];
        public byte[] e_ehsize = new byte[2];
        public byte[] e_phentsize = new byte[2];
        public byte[] e_phnum = new byte[2];
        public byte[] e_shentsize = new byte[2];
        public byte[] e_shnum = new byte[2];
        public byte[] e_shstrndx = new byte[2];
    }

    /**
     * typedef struct {
     *      uint32_t   sh_name;
     *      uint32_t   sh_type;
     *      uint64_t   sh_flags;
     *      Elf64_Addr sh_addr;
     *      Elf64_Off  sh_offset;
     *      uint64_t   sh_size;
     *      uint32_t   sh_link;
     *      uint32_t   sh_info;
     *      uint64_t   sh_addralign;
     *      uint64_t   sh_entsize;
     *  } Elf64_Shdr;
     */
    public static class  Elf64_Shdr{
        public byte[] sh_name = new byte[4];
        public byte[] sh_type = new byte[4];
        public byte[] sh_flags = new byte[8];
        public byte[] sh_addr = new byte[8];
        public byte[] sh_offset = new byte[8];
        public byte[] sh_size = new byte[8];
        public byte[] sh_link = new byte[4];
        public byte[] sh_info = new byte[4];
        public byte[] sh_addralign = new byte[8];
        public byte[] sh_entsize = new byte[8];
    }

    /**
     * typedef struct {
     *                uint32_t      st_name;
     *                unsigned char st_info;
     *                unsigned char st_other;
     *                uint16_t      st_shndx;
     *                Elf64_Addr    st_value;
     *                uint64_t      st_size;
     *            } Elf64_Sym;
     */
    public static class Elf64_Sym{
        public byte[] st_name = new byte[4];
        public byte[] st_info = new byte[1];
        public byte[] st_other = new byte[1];
        public byte[] st_shndx = new byte[2];
        public byte[] st_value = new byte[8];
        public byte[] st_size = new byte[8];
    }

    /**
     * typedef struct {
     *                Elf64_Word n_namesz;
     *                Elf64_Word n_descsz;
     *                Elf64_Word n_type;
     *            } Elf64_Nhdr;
     */
    public static class Elf64_Nhdr{
        public byte[] n_namesz = new byte[4];
        public byte[] n_descsz = new byte[4];
        public byte[] n_type = new byte[4];
    }

    /**
     *  typedef struct {
     *      uint32_t   p_type;
     *      uint32_t   p_flags;
     *      Elf64_Off  p_offset;
     *      Elf64_Addr p_vaddr;
     *      Elf64_Addr p_paddr;
     *      uint64_t   p_filesz;
     *      uint64_t   p_memsz;
     *      uint64_t   p_align;
     *  } Elf64_Phdr;
     */
    public static class  Elf64_Phdr{
        public byte[] p_type = new byte[4];
        public byte[] p_flags = new byte[4];
        public byte[] p_offset = new byte[8];
        public byte[] p_vaddr = new byte[8];
        public byte[] p_paddr = new byte[8];
        public byte[] p_filesz = new byte[8];
        public byte[] p_memsz = new byte[8];
        public byte[] p_align = new byte[8];
    }
}
