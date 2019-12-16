package com.hzxy.modules.jsmscode.controller;

import com.hzxy.common.utils.MobileUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.form.RegisterForm;
import com.hzxy.modules.app.service.UserService;
import com.hzxy.modules.jsmscode.resp.SendSMSResult;
import com.hzxy.modules.jsmscode.service.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 09:28
 * @Description:
 */
@RestController
@RequestMapping("/jgmsg")
@Api(value="极光短信相关")
@Slf4j
public class SendSMSController {

    @Autowired
    private SendService sendService;

    @GetMapping("getSCode")
    @ApiOperation("获取验证码")
    public R getSCode(String phone){
        try{
            if(StringUtils.isNotEmpty(phone)){
                if(MobileUtils.siMobileOrNo(phone)){

                    SendSMSResult sendSMSResult = sendService.sendSMSWithIHttpClient(phone);

                    if (sendSMSResult != null) {
                        if (StringUtils.isNotEmpty(sendSMSResult.getMsg_id())) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("msg_id", sendSMSResult.getMsg_id());
                            return R.ok(map);
                        } else {
                            return R.error("不能获取msg_Id");
                        }
                    } else {
                        return R.error("返回值为空");
                    }
                }else{
                    return R.error("手机号码格式不正确");
                }
            }else{
                return R.error("手机号码不能为空");
            }
        }catch (Exception e){
            return R.error(e.getMessage());
        }

    }



}
