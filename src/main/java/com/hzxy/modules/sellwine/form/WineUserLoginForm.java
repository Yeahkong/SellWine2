package com.hzxy.modules.sellwine.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-23 15:08
 * @Description:
 */
@Data
@ApiModel("用户登录form")
public class WineUserLoginForm {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message="用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

}
