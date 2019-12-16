package com.hzxy.modules.jsmscode.client;

import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.HttpProxy;
import cn.jiguang.common.connection.IHttpClient;
import cn.jiguang.common.connection.NativeHttpClient;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jiguang.common.utils.Preconditions;
import com.google.gson.JsonObject;
import com.hzxy.modules.jsmscode.config.JSMSConfig;
import com.hzxy.modules.jsmscode.pojo.SMSPayload;
import com.hzxy.modules.jsmscode.pojo.ValidSMSResult;
import com.hzxy.modules.jsmscode.resp.SendSMSResult;

import java.util.regex.Pattern;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 09:06
 * @Description:
 */
public class SMSClient {

    private static String SMS_CODE = "code";

    private String _baseUrl;
    private String _smsCodePath;
    private String _validPath;
    private String _voiceCodePath;
    private String _tempMsgPath;
    private IHttpClient _httpClient;

    public SMSClient(String masterSecret, String appkey) {
        this(masterSecret, appkey, null, JSMSConfig.getInstance());
    }

    public SMSClient(String masterSecret, String appkey, HttpProxy proxy, JSMSConfig conf) {
        ServiceHelper.checkBasic(appkey, masterSecret);

        _baseUrl = (String) conf.get(JSMSConfig.API_HOST_NAME);
        _smsCodePath = (String) conf.get(JSMSConfig.CODE_PATH);
        _validPath = (String) conf.get(JSMSConfig.VALID_PATH);
        _voiceCodePath = (String) conf.get(JSMSConfig.VOICE_CODE_PATH);
        _tempMsgPath = (String) conf.get(JSMSConfig.TEMP_MESSAGE_PATH);

        String authCode = ServiceHelper.getBasicAuthorization(appkey, masterSecret);
        this._httpClient = new NativeHttpClient(authCode, proxy, conf.getClientConfig());
    }

    /**
     * Send SMS verification code to mobile
     * @param payload include two parameters: mobile number and templete id. The second parameter is optional.
     * @return return SendSMSResult which includes msg_id
     * @throws APIConnectionException connection exception
     * @throws APIRequestException request exception
     */
    public SendSMSResult sendSMSCode(SMSPayload payload)
            throws APIConnectionException, APIRequestException {
        Preconditions.checkArgument(null != payload, "SMS payload should not be null");

        ResponseWrapper response = _httpClient.sendPost(_baseUrl + _smsCodePath, payload.toString());
        return SendSMSResult.fromResponse(response, SendSMSResult.class);
    }

    /**
     * Send SMS verification code to server, to verify if the code valid
     * @param msgId The message id of the verification code
     * @param code Verification code
     * @return return ValidSMSResult includes is_valid
     * @throws APIConnectionException connection exception
     * @throws APIRequestException request exception
     */
    public ValidSMSResult sendValidSMSCode(String msgId, String code)
            throws APIConnectionException, APIRequestException {
        ValidSMSResult result=new ValidSMSResult();
        try {
            Preconditions.checkArgument(null != msgId, "Message id should not be null");
            Pattern codePattern = Pattern.compile("^[0-9]{6}");
            Preconditions.checkArgument(codePattern.matcher(code).matches(), "The verification code shoude be consist of six number");
            JsonObject json = new JsonObject();
            json.addProperty(SMS_CODE, code);
            ResponseWrapper response = _httpClient.sendPost(_baseUrl + _smsCodePath + "/" + msgId + _validPath, json.toString());
            result= ValidSMSResult.fromResponse(response, ValidSMSResult.class);
            //System.out.println(result.getIsValid());
            //System.out.println("====================>>>>>  responseContent is "+response.responseContent+" ;  responseCode is "+response.responseCode);
        } catch (APIRequestException e) {
            //System.out.println("================>>>>  "+e.getErrorCode());
            result.is_valid=false;
            result.errCode = e.getErrorCode();

        }catch (Exception e){
            result.is_valid=false;
            result.errCode = -1;
            result.errMsg = e.getMessage();
        }
        return result;
    }

    /**
     * Send voice SMS verification code to mobile
     * @param payload payload includes two parameters: mobile number and ttl(time to live),
     *                the second one is optional(if miss ttl, will use default value 60 seconds).
     * @return return SendSMSResult which includes msg_id
     * @throws APIConnectionException connection exception
     * @throws APIRequestException request exception
     */
    public SendSMSResult sendVoiceSMSCode(SMSPayload payload)
            throws APIConnectionException, APIRequestException {
        Preconditions.checkArgument(null != payload, "SMS payload should not be null");

        ResponseWrapper response = _httpClient.sendPost(_baseUrl + _voiceCodePath, payload.toString());
        return SendSMSResult.fromResponse(response, SendSMSResult.class);
    }

    /**
     * Send template SMS to mobile
     * @param payload payload includes mobile, temp_id and temp_para, the temp_para is a map,
     *                which's key is what you had set in jiguang portal
     * @return return SendSMSResult which includes msg_id
     * @throws APIConnectionException  connection exception
     * @throws APIRequestException request exception
     */
    public SendSMSResult sendTemplateSMS(SMSPayload payload)
            throws APIConnectionException, APIRequestException {
        Preconditions.checkArgument(null != payload, "SMS payload should not be null");

        ResponseWrapper response = _httpClient.sendPost(_baseUrl + _tempMsgPath, payload.toString());
        return SendSMSResult.fromResponse(response, SendSMSResult.class);
    }

    public void setHttpClient(IHttpClient client) {
        this._httpClient = client;
    }

    // 如果使用 NettyHttpClient，在发送请求后需要手动调用 close 方法
    public void close() {
        if (_httpClient != null && _httpClient instanceof NettyHttpClient) {
            ((NettyHttpClient) _httpClient).close();
        }
    }

}
