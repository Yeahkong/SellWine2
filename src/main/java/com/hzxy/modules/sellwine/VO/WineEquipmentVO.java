package com.hzxy.modules.sellwine.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hzxy.common.utils.EnumUtil;
import com.hzxy.modules.sellwine.enums.DelFlagEnum;
import com.hzxy.modules.sellwine.enums.EquipmentStatusEnum;
import com.hzxy.modules.sellwine.enums.OnLineStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-19 14:12
 * @Description:
 */
@Data
public class WineEquipmentVO {

    private Long id;

    private String equipmentNo;

    private Integer onLineStatus;

    private Long userId;

    private String userName;

    private Integer status;

    private Date decantTime;

    private Long wineId;

    private String wineName;

    private Integer delFlag;

    private String remark;

    @JsonIgnore
    public OnLineStatusEnum onLineStatusEnum(){return EnumUtil.getByCode(onLineStatus, OnLineStatusEnum.class);}


    @JsonIgnore
    public DelFlagEnum delFlagEnum(){return EnumUtil.getByCode(delFlag, DelFlagEnum.class);}

    @JsonIgnore
    public EquipmentStatusEnum equipmentStatusEnum(){return EnumUtil.getByCode(status, EquipmentStatusEnum.class);}


}
