package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-19 15:14
 * @Description:
 */
@Data
@TableName("wine_wine")
public class Wine implements Serializable {
    private static final long serialVersionUID = -5096400138289543887L;

    private Long id;

    private String wineName;

    private double capacity;

    private double unitPrice;

    private double discountForWholeBottle;

    private String remark;

    private Integer delFlag;

}
