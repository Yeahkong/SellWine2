package com.hzxy.modules.tio.server;


import com.hzxy.modules.tio.common.MessageEn;

/**
 * @author YanBiuBiu
 * @date 2018-12-07 00:15
 */
public class MessageSe {

    public static MessageEn setMes(String str){
        MessageEn messageEn =new MessageEn();
        if (str.length()!=64){
            messageEn.setError("Packet length mismatch");
            return messageEn;
        }else if(!str.substring(0,2).equals("ST")||!str.substring(62,64).equals("ED")){
            messageEn.setError("Packet Head and Tail Mismatch");
            return messageEn;
        }else{
            messageEn.setFrameHead(str.substring(0,2));
            messageEn.setDataLen(str.substring(2, 4));
            messageEn.setFunctionCode(str.substring(4, 5));
            messageEn.setDeviceNo(str.substring(5, 20));
            messageEn.setCabinetStatus(str.substring(20, 46));
            messageEn.setBook1(str.substring(46, 47));
            messageEn.setBook2(str.substring(47, 62));
            messageEn.setFrameTail(str.substring(62, 64));
            return messageEn;
        }
    }

    public static MessageEn getUnpacking(String deviceNo, String doorNo, String type, String orderNo){
        MessageEn messageEn =new MessageEn();
        //messageEn.
        return messageEn;
    }

}
