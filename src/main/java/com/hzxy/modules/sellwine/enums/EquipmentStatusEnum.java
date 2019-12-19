package com.hzxy.modules.sellwine.enums;

import com.hzxy.common.enums.CodeEnum;
import lombok.Getter;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-19 15:19
 * @Description:
 */
@Getter
public enum EquipmentStatusEnum implements CodeEnum {

    NORMAL(0,"正常"),
    FAULT(1,"故障");

    private Integer code;

    private String message;

    EquipmentStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
