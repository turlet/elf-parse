## elf-parse
>Base on java language to parse elf file

### usage
- java -jar elfparse.jar <option(s)> elf-file
``` option
    -h --file-header       Display the ELF file header
    -l --program-headers   Display the program headers
     --segments          An alias for --program-headers
    -S --section-headers   Display the sections' header
     --sections          An alias for --section-headers
    -e --headers           Equivalent to: -h -l -S
    -s --syms              Display the symbol table
     --symbols           An alias for --syms
     --dyn-syms             Display the dynamic symbol table
    -n --notes             Display the core notes (if present)
    -r --rodata             Display the rodata constant
    -H --help              Display this information
    -v --version           Display the version number of elfparse
```