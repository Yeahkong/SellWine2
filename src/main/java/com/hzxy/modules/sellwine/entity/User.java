package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 14:46
 * @Description:
 */
@Data
@TableName("wine_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private String password;

    private String trueName;

    private String tencentOpenId;

    private String alipayOpenId;

    private Date createTime;

    private String delFlag;

    private Long createBy;

    private Long roleId;

}
