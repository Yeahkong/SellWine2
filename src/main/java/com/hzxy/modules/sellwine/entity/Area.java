package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("wine_area")
public class Area implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Long parentId;
  private String currentName;
  private Integer currentLevel;


}
