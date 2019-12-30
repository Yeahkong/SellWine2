package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("wine_exception")
public class WineException implements Serializable {
  private static final long serialVersionUID = -6465345638465034403L;

  private Long id;
  private String equipmentNo;
  private String exceptionType;
  private Date alarmTime;
  private Date releaseAlarmTime;
  private String remark;

}
