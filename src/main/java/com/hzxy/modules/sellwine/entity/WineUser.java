package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 14:32
 * @Description:
 */
@Data
@TableName("wine_user")
public class WineUser implements Serializable {
    private static final long serialVersionUID = 863599218148442636L;

    private Long id;

    private String userName;

    private String password;

    private String mobileNo;

    private String trueName;

    private Long areaId;

    private String tencentOpenId;

    private String alipayOpenId;

    private Date createTime;

    private Integer delFlag;

    private Long createBy;

    private Long roleId;

    private String remark;

}
