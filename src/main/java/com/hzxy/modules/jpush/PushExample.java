package com.hzxy.modules.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.log4j.Logger;

import java.util.Map;

public class PushExample {
    public static Logger logger = Logger.getLogger(PushExample.class);
    private static final String appKey = "10ea18119fe9810c0ce31612";
    private static final String masterSecret = "579fb140f0581b2277bfe5c6";
    private static PushPayload payload = null;
    private static JPushClient jpushClient = null;

    public static void main(String[] args) {
       /* Map map =new HashMap();
        map.put("electricNo", "A76EBB");
        map.put("temperature", "20");
        map.put("model", "1");
        map.put("windSpeed", "0");
        map.put("windDirection", "2");
        map.put( "sleep", "1");
        map.put("status","1");
        testSendPush(map,"getAc","1");*/
        /*testAlert("智能家居","1");*/
    }
        /**
         * 推送自定义消息
         *
         * @param map
         * @param operation
         * @param alias
         */
    public static void testSendPush(Map map, String operation, String alias) {
        jpushClient = new JPushClient(masterSecret, appKey, 3);
        payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder()
                .setMsgContent(operation)
                .addExtras(map)
                .build())
                .build();
        send(jpushClient, payload);
    }

    /**
     * 推送通知
     *
     * @param content
     * @param alias
     */
    public static void testAlert(String content, String alias) {
        jpushClient = new JPushClient(masterSecret, appKey, 3);
        payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                .setAlert(content)
                .build())
                .build();
        send(jpushClient, payload);
    }

    /**
     * 发送到极光
     *
     * @param jpushClient
     * @param payload
     */
    public static void send(JPushClient jpushClient, PushPayload payload) {
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.info("Connection error. Should retry later. ");
        } catch (APIRequestException e) {
            logger.info("Error response from JPush server. Should review and fix it. ");
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        }
    }
}

