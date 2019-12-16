package com.hzxy.modules.sellwine.enums;

import lombok.Getter;

@Getter
public enum DelFlagEnums {


    SHOW(0,"显示"),
    HIDDEN(1,"隐藏");

    private Integer code;

    private String message;

    DelFlagEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
