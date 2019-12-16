/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hzxy.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 * 操作表单
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "操作表单")
public class OperationForm {
    @ApiModelProperty(value = "房间id")
    @NotBlank(message="房间id不能为空")
    private long familyId;

    @ApiModelProperty(value = "动作")
    @NotBlank(message="动作不能为空")
    private String action;

    @ApiModelProperty(value = "设备编号")
    @NotBlank(message="设备编号不能为空")
    private String electricNo;

}
