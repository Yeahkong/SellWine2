package com.hzxy.modules.jsmscode.service;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.ApacheHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import com.hzxy.modules.jsmscode.client.SMSClient;
import com.hzxy.modules.jsmscode.pojo.SMSPayload;
import com.hzxy.modules.jsmscode.pojo.ValidSMSResult;
import com.hzxy.modules.jsmscode.resp.SendSMSResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 09:03
 * @Description:
 */
@Service
@Slf4j
public class SendService {

    // 慧聚快递柜 的appkey 和 masterSecret
    private static final String appkey = "2378030ba25f6868ff414c79";
    private static final String masterSecret = "af878b6d4c7b8b7307ae1f96";




    /**
     * 短信验证码
     * @param phone 接收短信验证码的手机号
     * @return 短信验证码方法调用结果是 : {"msg_id":"722079889300"}
     */
    public  SendSMSResult sendSMSWithIHttpClient(String phone) {
        SMSClient client = new SMSClient(masterSecret, appkey);
        String authCode = ServiceHelper.getBasicAuthorization(appkey, masterSecret);
        ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, ClientConfig.getInstance());
        // NettyHttpClient httpClient = new NettyHttpClient(authCode, null, ClientConfig.getInstance());
        // ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, ClientConfig.getInstance());
        // 可以切换 HttpClient，默认使用的是 NativeHttpClient
        client.setHttpClient(httpClient);
        // 如果使用 NettyHttpClient，发送完请求后要调用 close 方法
        // client.close();
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobildNumber(phone)
                .setTempId(1)
                .build();
        SendSMSResult sendSMSResult = null;
        try {
            sendSMSResult = client.sendSMSCode(payload);

        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        }
        return sendSMSResult;
    }

    /**
     * 用来验证发送过的验证码和输入的验证码是否匹配
     *
     * @param msg_id 已经发送出去的短信验证码的 id
     * @param code   输入的验证码
     * @return
     */
    public  ValidSMSResult validSMSCode(String msg_id, String code) {
        SMSClient client = new SMSClient(masterSecret, appkey);
        ValidSMSResult res = new ValidSMSResult();
        try {
            res = client.sendValidSMSCode(msg_id, code);

            int errCode = res.getErrCode();
            if (50001 == errCode) {
                res.errMsg = "auth 为空";
            } else if (50002 == errCode) {
                res.errMsg = "auth 鉴权失败";
            } else if (50003 == errCode) {
                res.errMsg = "body为空";
            } else if (50004 == errCode) {
                res.errMsg = "手机号码为空";
            } else if (50005 == errCode) {
                res.errMsg = "模板ID为空";
            } else if (50006 == errCode) {
                res.errMsg = "手机号码无效";
            } else if (50007 == errCode) {
                res.errMsg = "body无效";
            } else if (50008 == errCode) {
                res.errMsg = "未开通短信业务";
            } else if (50009 == errCode) {
                res.errMsg = "下发超频";
            } else if (50010 == errCode) {
                res.errMsg = "验证码无效";
            } else if (50011 == errCode) {
                res.errMsg = "验证码过期";
            } else if (50012 == errCode) {
                res.errMsg = "验证码已验证通过";
            } else if (50013 == errCode) {
                res.errMsg = "模板ID无效";
            } else if (50014 == errCode) {
                res.errMsg = "可发短信余量不足";
            } else if (50015 == errCode) {
                res.errMsg = "验证码为空";
            } else if (50016 == errCode) {
                res.errMsg = "API不存在";
            } else if (50017 == errCode) {
                res.errMsg = "媒体类型不支持";
            } else if (50018 == errCode) {
                res.errMsg = "请求方法不支持";
            } else if (50019 == errCode) {
                res.errMsg = "服务器端异常";
            } else if (50020 == errCode) {
                res.errMsg = "模板审核中";
            } else if (50021 == errCode) {
                res.errMsg = "模板审核未通过";
            } else if (50022 == errCode) {
                res.errMsg = "模板中参数未全部替换";
            } else if (50023 == errCode) {
                res.errMsg = "参数为空";
            } else if (50024 == errCode) {
                res.errMsg = "手机号码已退订";
            } else if (50025 == errCode) {
                res.errMsg = "该API不支持此模板类型";
            } else if (50026 == errCode) {
                res.errMsg = "msg_id无效";
            } else if (50030 == errCode) {
                res.errMsg = "recipients为空";
            } else if (50031 == errCode) {
                res.errMsg = "recipients短信接收者超过1000";
            } else if (50034 == errCode) {
                res.errMsg = "重复发送";
            } else if (50035 == errCode) {
                res.errMsg = "非法IP请求";
            } else if (50036 == errCode) {
                res.errMsg = "应用被列为黑名单";
            } else if (50037 == errCode) {
                res.errMsg = "短信中存在敏感词汇";
            } else if (50038 == errCode) {
                res.errMsg = "短信内容长度错误，文本短信长度不超过350个字，语音短信验证码长度4～8数字";
            } else if (50039 == errCode) {
                res.errMsg = "语音验证码内容错误，验证码仅支持数字";
            } else if (50040 == errCode) {
                res.errMsg = "语音验证码播报语言类型错误";
            } else if (50041 == errCode) {
                res.errMsg = "验证码有效期错误";
            } else if (50054 == errCode) {
                res.errMsg = "短信正文不能含有特殊符号，如【】";
            } else {
                res.errMsg = "出现未知错误 ， 官方都没有定义该错误 ， 请及时管理员联系!!!";
            }

        } catch (Exception e) {
            res.errMsg = "出现了异常 ， 异常信息为：" + e.getMessage();
        }
        return res;
    }

}
