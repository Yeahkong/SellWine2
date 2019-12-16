package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 13:15
 * @Description:
 */

@Data
@TableName("wine_wine")
public class Wine implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer capacity;

    private Double unitPrice;

    private String remark;

}
