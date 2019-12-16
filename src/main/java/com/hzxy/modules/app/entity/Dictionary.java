package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-11 15:15
 * @Description:  字典表
 */
@Data
@TableName("tb_dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 数据类型
     */
    private String dictType;

    /**
     * 数据编码
     */
    private String dictCode;

    /**
     * 数据中文名称
     */
    private String dictName;

    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 备注信息
     */
    private String comment;

    private Integer delFlag;



}
