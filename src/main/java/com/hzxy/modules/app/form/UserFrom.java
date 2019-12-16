package com.hzxy.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Program SmartHome
 * @Package com.hzxy.modules.app.form
 * @ClassName UserFrom
 * @Author liuningying
 * @Date 2019-11-09 10:00
 */
@Data
@ApiModel("用户表单")
public class UserFrom {
    @ApiModelProperty("用户家庭id")
    private Long userFamilyId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("状态")
    private Integer status;

}
