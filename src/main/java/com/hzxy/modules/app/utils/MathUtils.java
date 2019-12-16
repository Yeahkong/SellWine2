package com.hzxy.modules.app.utils;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-11 19:18
 * @Description:
 */
public class MathUtils {

    public static String getMaxCode(Integer code){
        Integer maxCode = code + 1;
        return String.format("%04d", maxCode);
    }


}
