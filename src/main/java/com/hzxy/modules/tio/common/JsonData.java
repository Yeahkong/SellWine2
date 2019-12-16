package com.hzxy.modules.tio.common;

import lombok.Data;

/**
 * @ClassName JsonData
 * @Author liuningying
 * @Date 2019-09-05
 */
@Data
public class JsonData {
    //操作指令
    private String action;
    //板子编号
    private String boardNo;
    //版本号
    private String version;
    //联网方式(0:4G 1:网线)
    private String type;
    //房间电器匹配总ID
    private String id;
    //设备id
    private String deviceId;
    //设备编号
    private String num;
    //是否成功(0:失败  1:成功)
    private String success;
    //设备开关状态(0:关 1:开)
    private String status;
    //空调温度
    private String temp;
    //模式（0制冷 1制热 2自动 3除湿）
    private String model;
    //风速（0 低速   1中速   2高速）
    private String speed;
    //风向（0上下扫风  1左右扫风  2无扫风  3上下左右扫风同时）
    private String wind;
    //是否睡眠（0是  1否）
    private String sleep;
}
