package com.hzxy.modules.sellwine.enums;

import com.hzxy.common.enums.CodeEnum;
import lombok.Getter;

@Getter
public enum PayWayEnum implements CodeEnum {
    TENCENT(0,"微信"),
    ALIPAY(1,"支付宝"),
    CASH(2,"现金"),
    CREDITCARD(3,"刷卡");

    private Integer code;

    private String message;

    PayWayEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
