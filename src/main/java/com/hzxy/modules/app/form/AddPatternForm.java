/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hzxy.modules.app.form;

import com.hzxy.modules.app.entity.AirConditioningEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 场景添加设备表单
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "场景添加设备表单")
public class AddPatternForm {

    @ApiModelProperty("模式名称")
    private long patternId;

    @ApiModelProperty("房间id")
    private Long roomId;

    @ApiModelProperty("设备类型")
    private Long typeId;

    @ApiModelProperty("设备id")
    private Long electricId;

    @ApiModelProperty("开关状态")
    private String status;

    @ApiModelProperty("空调信息")
    private AirConditioningEntity air;

    @ApiModelProperty("设备指令id")
    private Long instructId;


}
