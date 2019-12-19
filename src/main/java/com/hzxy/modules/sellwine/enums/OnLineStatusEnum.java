package com.hzxy.modules.sellwine.enums;

import com.hzxy.common.enums.CodeEnum;
import lombok.Getter;

@Getter
public enum OnLineStatusEnum implements CodeEnum {
    ONLINE(0,"在线"),
    OFFLINE(1,"离线");

    private Integer code;

    private String message;

    OnLineStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
