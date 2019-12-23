package com.hzxy.modules.sellwine.enums;

import com.hzxy.common.enums.CodeEnum;
import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum {
    UNPAID(0,"待支付"),
    PAID(1,"已支付"),
    REFUND(2,"已退款");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
