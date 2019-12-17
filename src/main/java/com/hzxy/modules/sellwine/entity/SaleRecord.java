package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("wine_sale")
public class SaleRecord implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Long equipmentId;
  private Double saleAmount;
  private Double realPay;
  private String payWay;
  private String payStatus;
  private Date createDate;
  private String remark;
  private String delFlag;





}
