package com.hzxy.common.utils;

import java.math.BigDecimal;

/**
 * @Program SmartHome
 * @Package com.hzxy.common.utils
 * @ClassName HexUtil
 * @Author liuningying
 * @Date 2019-12-06 15:16
 */

public class HexUtil {
    public static void main(String[] args) {
        String hex=str2HexStr("{\"action\":\"heartbeat\",\"boardNo\":\"862991528739995\",\"version\":\"V1.0\",\"type\":\"0\"}");
        System.out.println(hex);
        System.out.println(hex.length());
    }
    /**
     * 字符串转换成为16进制(无需Unicode编码)
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString();
    }
    public static String bytes2kb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1) {
            return (returnValue + "MB");
        }
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (returnValue + "KB");
    }
}
