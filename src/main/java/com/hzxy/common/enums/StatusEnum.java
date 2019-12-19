package com.hzxy.common.enums;

import lombok.Getter;

@Getter
public enum StatusEnum implements CodeEnum{
    SHOW(0,"显示"),
    HIDE(1,"隐藏");


    StatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }


    private Integer code;

    private String message;
}
