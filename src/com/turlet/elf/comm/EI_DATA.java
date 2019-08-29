package com.turlet.elf.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Silen((myemail)) on 2019/8/21 17:25
 *
 * <pre>
 *     给出处理器特定数据的数据编码方式,具体请看以下引用图片：
 *     <img src="https://upload-images.jianshu.io/upload_images/14869920-202b000d55b421ca.png"/>
 *     记忆：----高尾端：尾端在高位，低尾端：尾端在地位---
 * </pre>
 */
public final class EI_DATA {
    /**
     * 非法数据编码
     */
    public static final int ELFDATANONE = 0x0;
    /**
     * 低位在前，低字节存储在高地址
     */
    public static final int ELFDATA2LSB = 0x1;
    /**
     * 高位在前，低字节存储在低地址
     */
    public static final int ELFDATA2MSB = 0x2;

    public static final int ELFDATANUM = 0x3;

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(ELFDATANONE, "非法数据编码-Invalid data encoding(0)");
        map.put(ELFDATA2LSB, "小端-2's complement, little endian(1)");
        map.put(ELFDATA2MSB, "大端-2's complement, big endian(2)");
        map.put(ELFDATANUM, "(3)");
    }

    public static String get(Integer data) {
        return map.get(data);
    }
}
