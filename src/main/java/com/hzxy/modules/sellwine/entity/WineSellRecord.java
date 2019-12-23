package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-21 13:19
 * @Description:
 */
@Data
@TableName("wine_sell_record")
public class WineSellRecord implements Serializable {
    private static final long serialVersionUID = 9216274992878874980L;

    private Long id;

    private Long userId;

    private Long wineId;
    // 数量 ， 瓶
    private Integer amount;
    // 应付金额
    private Double amountPayable;
    // 实付金额
    private Double realPay;

    private Integer payStatus;

    private Integer payWay;

    private Date payTime;

    private String remark;

}
