package com.hzxy.modules.sellwine.VO;

import lombok.Data;

import java.util.Date;

@Data
public class WineOperateRecordVO {
  private Long id;
  private String equipmentNo;
  private String trueName;
  private String mobileNo;
  private String areaName;
  private String operateName;
  private String operateContent;
  private Date operateTime;
  private String remark;
}
