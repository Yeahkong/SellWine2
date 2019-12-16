/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hzxy.modules.app.controller;


import com.hzxy.common.utils.DateUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.validator.ValidatorUtils;
import com.hzxy.modules.app.entity.UserEntity;
import com.hzxy.modules.app.form.RegisterForm;
import com.hzxy.modules.app.service.UserService;
import com.hzxy.modules.jsmscode.pojo.ValidSMSResult;
import com.hzxy.modules.jsmscode.service.SendService;
import io.swagger.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 注册
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
@Api(value="APP注册接口")
public class AppRegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private SendService sendService;

    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        ValidSMSResult validSMSResult = sendService.validSMSCode(form.getMsgId(),form.getValidCode());

        if (validSMSResult != null) {

            boolean isValid = validSMSResult.getIsValid();
            if (isValid) {
                UserEntity user = new UserEntity();
                user.setMobile(form.getMobile());
                user.setUserName(form.getMobile());
                user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
                user.setCreateTime(DateUtils.getDateNow());
                userService.save(user);

                return R.ok();
            }else{
                return R.error("验证码输入有误");
            }
        }else{
            return R.error("验证码校验出现异常");
        }



    }
}
