package com.hzxy.common.enums;

import lombok.Getter;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-28 17:18
 * @Description:  状态枚举
 */
@Getter
public enum StatusEnum {

    SHOW(0,"显示"),
    HIDE(1,"隐藏");


    StatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }


    private Integer code;

    private String message;

}
