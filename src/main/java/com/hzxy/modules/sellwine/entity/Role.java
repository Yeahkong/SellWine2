package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 14:51
 * @Description:
 */
@Data
@TableName("wine_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;

    private Date createTime;

    private String delFlag;

}
