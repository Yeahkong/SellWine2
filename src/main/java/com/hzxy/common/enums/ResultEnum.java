package com.hzxy.common.enums;

import lombok.Getter;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 13:45
 * @Description:
 */
@Getter
public enum ResultEnum {

    SUCCESS(1,"成功");

    private Integer code;

    private String message;

    ResultEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}
