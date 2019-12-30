package com.hzxy.modules.sellwine.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hzxy.common.utils.EnumUtil;
import com.hzxy.modules.sellwine.enums.PayStatusEnum;
import com.hzxy.modules.sellwine.enums.PayWayEnum;
import lombok.Data;

import java.util.Date;

@Data
public class WineOrderVO {


  private Long id;
  private String orderNo;
  private Long equipmentId;
  private String equipmentNo;
  private Integer amount;
  private String userName;
  private String mobileNo;
  private String trueName;
  private String wineName;
  private Double amountPayable;
  private Integer payStatus;
  private Integer payWay;
  private String tencentOpenId;
  private String alipayOpenId;

  @TableField(exist = false)
  private String stime;

  @TableField(exist = false)
  private String etime;

  public String getStime() {
    return stime;
  }

  public void setStime(String stime) {
    this.stime = stime;
  }

  public String getEtime() {
    return etime;
  }

  public void setEtime(String etime) {
    this.etime = etime;
  }

  @JsonIgnore
  public PayStatusEnum payStatusEnum() {
    return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
  }

  @JsonIgnore
  public PayWayEnum payWayEnum() {
    return EnumUtil.getByCode(payWay, PayWayEnum.class);
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date buyTime;

  private String remark;

}
