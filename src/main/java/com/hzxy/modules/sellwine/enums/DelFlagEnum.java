package com.hzxy.modules.sellwine.enums;

import com.hzxy.common.enums.CodeEnum;
import lombok.Getter;

@Getter
public enum DelFlagEnum implements CodeEnum {
    SHOW(0,"显示"),
    HIDDEN(1,"隐藏");

    private Integer code;

    private String message;

    DelFlagEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
