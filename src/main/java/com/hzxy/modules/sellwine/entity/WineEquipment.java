package com.hzxy.modules.sellwine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-19 13:56
 * @Description:
 */
@Data
@TableName("wine_equipment")
public class WineEquipment implements Serializable {
    private static final long serialVersionUID = 2318838645888697815L;

    private Long id;

    private String equipmentNo;

    private Integer onLineStatus;

    private Long userId;

    private Integer status;

    private Date decantTime; // 换瓶时间

    private Long wineId; // 当前装酒id

    private Integer delFlag;

    private String remark;


}
