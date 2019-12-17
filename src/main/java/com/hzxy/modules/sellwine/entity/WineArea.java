package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 14:45
 * @Description:
 */
@Data
@TableName("wine_area")
public class WineArea implements Serializable {
    private static final long serialVersionUID = -6093954963060760919L;

    private Long id;

    private Long parentId;

    private String currentName;

    private Integer currentLevel;



}
