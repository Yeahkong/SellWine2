package com.hzxy.modules.sellwine.VO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WineExceptionVO implements Serializable {
  private Long id;
  private String equipmentNo;
  private String trueName;
  private String mobileNo;
  private String areaName;
  private String exceptionType;
  private Date alarmTime;
  private Date releaseAlarmTime;
  private String remark;

}
