package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("wine_buy")
public class BuyRecord implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  private Long roleId;

  private Long wineId;

  private Integer buyAmount;

  private Double discount;

  private Double realPay;

  private String payWay;

  private String payStatus;

  private Date createDate;

  private String createBy;

  private String remark;

  private String delFlag;




}
