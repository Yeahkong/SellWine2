package com.hzxy.common.utils;


import com.hzxy.common.enums.CodeEnum;

/**
 * 枚举的工具类
 */
public class EnumUtil {

    // <T extends CodeEnum> 用来说明T
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each: enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }

}
