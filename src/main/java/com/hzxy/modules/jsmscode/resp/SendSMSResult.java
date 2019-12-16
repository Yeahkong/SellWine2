package com.hzxy.modules.jsmscode.resp;

import cn.jiguang.common.resp.BaseResult;
import com.google.gson.annotations.Expose;
import lombok.Getter;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 09:00
 * @Description:
 */
@Getter
public class SendSMSResult extends BaseResult {

    @Expose
    String msg_id;



}
