package com.hzxy.modules.sellwine.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hzxy.common.utils.EnumUtil;
import com.hzxy.modules.sellwine.enums.DelFlagEnum;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 16:06
 * @Description:
 */
@Data
public class WineUserVO {

    private Long id;

    private String userName;

    private String mobileNo;

    private String areaName;

    private String trueName;

    private Integer delFlag;


    @JsonIgnore
    public DelFlagEnum delFlag(){return EnumUtil.getByCode(delFlag,DelFlagEnum.class);}

    private String createBy;

    private String roleName;

    private String remark;

    private Date createTime;

}
