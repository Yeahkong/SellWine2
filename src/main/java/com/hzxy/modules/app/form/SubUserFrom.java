package com.hzxy.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Program SmartHome
 * @Package com.hzxy.modules.app.form
 * @ClassName SubUserFrom
 * @Author liuningying
 * @Date 2019-11-09 10:00
 */
@Data
@ApiModel("子用户表单")
public class SubUserFrom{
    @ApiModelProperty("家庭id")
    private Long familyId;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("昵称")
    private String nickName;

}
