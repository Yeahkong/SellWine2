package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("wine_order")
public class WineOrder implements Serializable {
  private static final long serialVersionUID = 2318838645888697815L;

  private Long id;
  private String orderNo;
  private Long equipmentId;
  private Integer amount;
  private Double amountPayable;
  private Date buyTime;
  private Integer payStatus;
  private Integer payWay;
  private String tencentOpenId;
  private String alipayOpenId;
  private String remark;


}
