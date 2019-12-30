package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("wine_opr_record")
public class WineOperateRecord implements Serializable {
  private static final long serialVersionUID = -6465345638465034403L;

  private Long id;
  private String equipmentNo;
  private String operateName;
  private String operateContent;
  private Date operateTime;
  private String remark;
}
