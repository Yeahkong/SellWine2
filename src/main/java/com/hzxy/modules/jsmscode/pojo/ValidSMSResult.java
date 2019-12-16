package com.hzxy.modules.jsmscode.pojo;

import cn.jiguang.common.resp.BaseResult;
import com.google.gson.annotations.Expose;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 09:05
 * @Description:
 */
public class ValidSMSResult extends BaseResult {

    @Expose
    public Boolean is_valid;

    public Boolean getIsValid() {
        return is_valid;
    }

    @Expose
    public int errCode;
    public int getErrCode(){
        return errCode;
    }

    @Expose
    public String errMsg;

    public String getErrMsg() {
        return errMsg;
    }


}
