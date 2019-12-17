package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 13:48
 * @Description:
 */
@Data
@TableName("wine_role")
public class WineRole implements Serializable {


    private static final long serialVersionUID = -6465345638465034403L;

    private Long id;

    private String roleName;

    private Date createTime;

    private Integer delFlag;
}
