package com.hzxy.modules.app.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-26 17:25
 * @Description:
 */
@Data
public class UserFamilyForm {

    @ApiModelProperty(value="手机号")
    private String mobile;

    @ApiModelProperty(value = "家庭详细地址")
    private String familyAddress;

    @ApiModelProperty(value = "聪鸟设备id")
    private String cnei;

    @ApiModelProperty(value = "房间组成")
    private String roomConstitute;

}
