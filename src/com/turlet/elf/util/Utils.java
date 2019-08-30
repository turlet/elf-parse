package com.turlet.elf.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create by Silen((myemail)) on 2019/8/21 16:55
 */
public class Utils {

    /**
     * 把长度为2的byte数据按小端的方式转化成int
     * @param src
     * @return
     */
    public static int byte2ToInt(byte[] src){
        if(src == null || src.length != 2){
            throw new IllegalArgumentException("bytes 参数长度必需为2");
        }
        return (src[0] & 0xFF) | ((src[1] & 0xFF) << 8);
    }

    /**
     * 把长度为4的byte数据按小端的方式转化成long
     * @param src
     * @return
     */
    public static int byte4ToInt(byte[] src){
        if(src == null || src.length != 4){
            throw new IllegalArgumentException("bytes 参数长度必需为4");
        }
        return (src[0] & 0xFF) | ((src[1] & 0xFF) << 8) | ((src[2] & 0xFF) << 16) | ((src[3] & 0xFF) << 24);
    }

    /**
     * 把长度为4的byte数据按小端的方式转化成long
     * @param src
     * @return
     */
    public static long byte4ToLong(byte[] src){
        if(src == null || src.length != 4){
            throw new IllegalArgumentException("bytes 参数长度必需为4");
        }
        return (src[0] & 0xFF) | ((src[1] & 0xFF) << 8) | ((src[2] & 0xFF) << 16) | (((long)(src[3]) & 0xFF) << 24);
    }

    /**
     * 把长度为4的byte数据按小端的方式转化成long
     * @param src
     * @return
     */
    public static long byte8ToLong(byte[] src){
        if(src == null || src.length != 8){
            throw new IllegalArgumentException("bytes 参数长度必需为8");
        }
        return (src[0] & 0xFF) | ((src[1] & 0xFF) << 8) | ((src[2] & 0xFF) << 16) | ((src[3] & 0xFF) << 24)
                | ((src[3] & 0xFF) << 32 | ((src[3] & 0xFF) << 40)| ((src[3] & 0xFF) << 48)| ((src[3] & 0xFF) << 56) );
    }


    /**
     * 为了打印对齐，格式化名字太长
     * @return
     */
    public static String formatValue(String value, int length,String key,boolean after){
        int len = (Objects.isNull(value)?0:value.length());
        List<String> blankList = Stream.generate(()->key).limit(Math.max(length - len,0)).collect(Collectors.toList());
        if(after) {
            return len > length ? value.substring(0, length) : (value + join(blankList, ""));
        }else{
            return len > length ? value.substring(0, length) : (join(blankList, "") + value);
        }
    }

    public static String join(Collection var0, String value) {
        StringBuilder builder = new StringBuilder();

        for(Iterator var3 = var0.iterator(); var3.hasNext(); builder.append((String)var3.next())) {
            if (builder.length() != 0) {
                builder.append(value);
            }
        }

        return builder.toString();
    }
}
