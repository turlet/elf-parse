package com.turlet.elf.bean;

/**
 * Create by Silen((myemail)) on 2019/8/21 14:58
 * <pre>
     |------------------------------------------|
     | 名称          |  大小   | 说明           |
     |------------------------------------------|
     | Elf32_Addr    |   4     | 无符号程序地址 |
     |------------------------------------------|
     | Elf32_Half    |   2     | 无符号中等整数 |
     |------------------------------------------|
     | Elf32_Off     |   4     | 无符号文件偏移 |
     |------------------------------------------|
     | Elf32_SWord   |   4     | 有符号大整数   |
     |------------------------------------------|
     | Elf32_Word    |   4     | 无符号大整数   |
     |------------------------------------------|
     | unsigned char |   1     | 无符号笑整数   |
     |------------------------------------------|
 * </pre>
 */
public class Elf32 {

    /**
     * #define EI_NIDENT (16)
     * elf头文件的魔数(Magic)长度
     */
    public static final int EI_NIDENT = 0x10;

    /**
     * 头部对象字节长度，占用52位
     */
    public static final int ELF_HDR_LENGTH = 0x34;

    /**
     typedef struct
     {
         unsigned char e_ident[EI_NIDENT]; //Magic number and other info
         Elf32_Half    e_type;         //Object file type
         Elf32_Half    e_machine;      //Architecture
         Elf32_Word    e_version;      // Object file version
         Elf32_Addr    e_entry;        // Entry point virtual address
         Elf32_Off e_phoff;        // Program header table file offset
         Elf32_Off e_shoff;        // Section header table file offset
         Elf32_Word    e_flags;        // Processor-specific flags
         Elf32_Half    e_ehsize;       // ELF header size in bytes
         Elf32_Half    e_phentsize;        // Program header table entry size
         Elf32_Half    e_phnum;        // Program header table entry count
         Elf32_Half    e_shentsize;        // Section header table entry size
         Elf32_Half    e_shnum;        // Section header table entry count
         Elf32_Half    e_shstrndx;     // Section header string table index
    } Elf32_Ehdr;
     */
    public static class  Elf32_Ehdr{
        //Magic： 7f 45 4c 46 01 01 01 00 00 00 00 00 00 00 00 00
        //The initial bytes mark the file as an object file and provide machine-independent data with which to decode and interpret the file's contents
        public byte[] e_ident = new byte[EI_NIDENT];
        /**
         * This member identifies the object file type
         */
        public byte[] e_type = new byte[2];
        /**
         * This member's value specifies the required architecture for an individual file.
         */
        public byte[] e_machine = new byte[2];
        //This member identifies the object file version
        public byte[] e_version = new byte[4];
        //This member gives the virtual address to which the system first transfers control, thus starting the process. If the file has no associated entry point, this member holds zero.
        public byte[] e_entry = new byte[4];
        //This member holds the program header table's file offset in bytes. If the file has no program header table, this member holds zero.
        public byte[] e_phoff = new byte[4];
        //This member holds the section header table's file offset in bytes. If the file has no section header table, this member holds zero.
        public byte[] e_shoff = new byte[4];
        //This member holds processor-specific flags associated with the file. Flag names take the form EF_machine_flag.
        public byte[] e_flags = new byte[4];
        //This member holds the ELF header's size in bytes.
        public byte[] e_ehsize = new byte[2];
        //This member holds the size in bytes of one entry in the file's program header table; all entries are the same size.
        public byte[] e_phentsize = new byte[2];
        //This member holds the number of entries in the program header table. Thus the product of e_phentsize and e_phnum gives the table's size in bytes. If a file has no program header table, e_phnum holds the value zero.
        public byte[] e_phnum = new byte[2];
        //This member holds a section header's size in bytes. A section header is one entry in the section header table; all entries are the same size.
        public byte[] e_shentsize = new byte[2];
        //This member holds the number of entries in the section header table. Thus the product of e_shentsize and e_shnum gives the section header table's size in bytes. If a file has no section header table, e_shnum holds the value zero.
        public byte[] e_shnum = new byte[2];
        //This member holds the section header table index of the entry associated with the section name string table. If the file has no section name string table, this member holds the value SHN_UNDEF. See ``Sections'' and ``String Table'' below for more information.
        public byte[] e_shstrndx = new byte[2];
    }

    /* Section header.  */
    /**
     typedef struct
     {
         Elf32_Word    sh_name;        // Section name (string tbl index)
         Elf32_Word    sh_type;        //* Section type
         Elf32_Word    sh_flags;       //* Section flags
         Elf32_Addr    sh_addr;        //* Section virtual addr at execution
         Elf32_Off sh_offset;      //* Section file offset
         Elf32_Word    sh_size;        //* Section size in bytes
         Elf32_Word    sh_link;        //* Link to another section
         Elf32_Word    sh_info;        //* Additional section information
         Elf32_Word    sh_addralign;       //* Section alignment
         Elf32_Word    sh_entsize;     //* Entry size if section holds table
     } Elf32_Shdr;
     */
    public static class  Elf32_Shdr{
        public byte[] sh_name = new byte[4];
        public byte[] sh_type = new byte[4];
        public byte[] sh_flags = new byte[4];
        public byte[] sh_addr = new byte[4];
        public byte[] sh_offset = new byte[4];
        public byte[] sh_size = new byte[4];
        public byte[] sh_link = new byte[4];
        public byte[] sh_info = new byte[4];
        public byte[] sh_addralign = new byte[4];
        public byte[] sh_entsize = new byte[4];
    }

    /**
     * <pre>
     * SHF_COMPRESSED
     * This flag identifies a section containing compressed data. SHF_COMPRESSED applies only to non-allocable sections,
     * and cannot be used in conjunction with SHF_ALLOC. In addition, SHF_COMPRESSED cannot be applied to sections of type SHT_NOBITS.
     * All relocations to a compressed section specifiy offsets to the uncompressed section data. It is therefore
     * necessary to decompress the section data before relocations can be applied. Each compressed section specifies
     * the algorithm independently. It is permissible for different sections in a given ELF object to employ different compression algorithms.
     *
     * Compressed sections begin with a compression header structure that identifies the compression algorithm.
     *
     *  typedef struct {
     *      Elf32_Word	ch_type;
     * 	    Elf32_Word	ch_size;
     *      Elf32_Word	ch_addralign;
     *  } Elf32_Chdr;
     * </pre>
     */
    public static class Elf32_Chdr{
        public byte[] ch_type = new byte[4];
        public byte[] ch_size = new byte[4];
        public byte[] ch_addralign = new byte[4];
    }

    /**
     * <pre>
     * 符号表(.dynsym)
     * 符号表包含用来定位、重定位程序中符号定义和引用的信息，简单的理解就是符号表记录了该文件中的所有符号，
     * 所谓的符号就是经过修饰了的函数名或者变量名，不同的编译器有不同的修饰规则。
     * 例如符号_ZL15global_static_a，就是由global_static_a变量名经过修饰而来。
     *
     * typedef struct {
     *      Elf32_Word st_name;      //符号表项名称。如果该值非0，则表示符号名的字
     *      //符串表索引(offset)，否则符号表项没有名称。
     *      Elf32_Addr st_value;       //符号的取值。依赖于具体的上下文，可能是一个绝对值、一个地址等等。
     *      Elf32_Word st_size;         //符号的尺寸大小。例如一个数据对象的大小是对象中包含的字节数。
     *      unsigned char st_info;    //符号的类型和绑定属性。
     *      unsigned char st_other;  //未定义。
     *      Elf32_Half st_shndx;        //每个符号表项都以和其他节区的关系的方式给出定义。
     * 　　　　　　　　　　　　　//此成员给出相关的节区头部表索引。
     * } Elf32_sym;
     * </pre>
     */
    public static class Elf32_Sym{
        public byte[] st_name = new byte[4];
        public byte[] st_value = new byte[4];
        public byte[] st_size = new byte[4];
        public byte[] st_info = new byte[1];
        public byte[] st_other = new byte[1];
        public byte[] st_shndx = new byte[2];
    }

    /**
     * 重定位表项
     * typedef struct {
     *     Elf32_Addr r_offset;
     *     Elf32_Word r_info;
     * } Elf32_Rel;
     */
    public static class Elf32_Rel{
        public byte[] r_offset = new byte[4];
        public byte[] r_info = new byte[4];
    }

    /**
     * 重定位信息
     * typedef struct {
     *     Elf32_Addr r_offset;
     *     Elf32_Word r_info;
     *     Elf32_Word r_addend;
     * } Elf32_Rela;
     */
    public static class Elf32_Rela{
        public byte[] r_offset = new byte[4];
        public byte[] r_info = new byte[4];
        public byte[] r_addend = new byte[4];
    }

    /**
     *  typedef struct {
     *      Elf32_Word n_namesz;
     *      Elf32_Word n_descsz;
     *      Elf32_Word n_type;
     *  } Elf32_Nhdr;
     */
    public static class Elf32_Nhdr{
        public byte[] n_namesz = new byte[4];
        public byte[] n_descsz = new byte[4];
        public byte[] n_type = new byte[4];
    }

    /**
     //Program Header 程序头部 结构定义
     typedef struct elf32_phdr {
     Elf32_Word p_type;		// segment type
     Elf32_Off p_offset;		// segment offset
     Elf32_Addr p_vaddr;		// virtual address of segment
     Elf32_Addr p_paddr;		// physical address - ignored?
     Elf32_Word p_filesz;	// number of bytes in file for seg.
     Elf32_Word p_memsz;		// number of bytes in mem. for seg.
     Elf32_Word p_flags;		// flags
     Elf32_Word p_align;		// memory alignment
     } Elf32_Phdr;
     */
    public static class  Elf32_Phdr{
        public byte[] p_type = new byte[4];
        public byte[] p_offset = new byte[4];
        public byte[] p_vaddr = new byte[4];
        public byte[] p_paddr = new byte[4];
        public byte[] p_filesz = new byte[4];
        public byte[] p_memsz = new byte[4];
        public byte[] p_flags = new byte[4];
        public byte[] p_align = new byte[4];
    }
}
