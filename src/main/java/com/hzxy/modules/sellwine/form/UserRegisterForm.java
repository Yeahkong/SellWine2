package com.hzxy.modules.sellwine.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 15:13
 * @Description:
 */
@Data
@ApiModel("用户注册表单")
public class UserRegisterForm {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message="用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message="真实姓名不能为空")
    private String trueName;

    @ApiModelProperty(value = "角色id")
    @NotBlank(message="角色id不能为空")
    private Long roleId;


}
