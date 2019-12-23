package com.hzxy.modules.sellwine.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hzxy.common.utils.EnumUtil;
import com.hzxy.modules.sellwine.enums.PayStatusEnum;
import com.hzxy.modules.sellwine.enums.PayWayEnum;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-21 13:26
 * @Description:
 */
@Data
public class WineSellRecordVO {

    private Long id;

    private Long userId;

    private String trueName;

    private  String mobileNo;

    private Long wineId;

    private String wineName;

    // 数量 ， 瓶
    private Integer amount;
    // 应付金额
    private Double amountPayable;
    // 实付金额
    private Double realPay;

    private Integer payStatus;

    private Integer payWay;

    @JsonIgnore
    public PayStatusEnum payStatusEnum(){return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
    @JsonIgnore
    public PayWayEnum payWayEnum(){return EnumUtil.getByCode(payWay,PayWayEnum.class);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    private String remark;

}
