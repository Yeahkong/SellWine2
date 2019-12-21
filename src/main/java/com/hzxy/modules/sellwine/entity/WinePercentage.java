package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 17:08
 * @Description:
 */
@Data
@TableName("wine_percentage")
public class WinePercentage {

    private Long id;

    private Long roleId;

    private Double percentage;

}
