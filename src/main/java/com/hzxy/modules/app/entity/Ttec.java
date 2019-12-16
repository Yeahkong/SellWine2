package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-12 09:02
 * @Description: 主控板表
 */
@Data
@TableName("tb_ttec")
public class Ttec implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 聪鸟设备id
     */
    @TableId
    private String cnei;
    /**
     * 组装调试时间
     */
    private String debugTime;
    /**
     * 程序版本
     */
    private String programVersion;
    /**
     * 网络类型(0流量卡  1网线)
     */
    private String netType;
    /**
     * 4G模块id
     */
    private String cardId;
    /**
     * 单片机id
     */
    private String scmId;
    /**
     * 流量卡类型(0移动  1联通   2电信)
     */
    private String cardType;
    /**
     * 在线状态(0在线 1离线)
     */
    private Integer onLineStatus;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 备注信息
     */
    private String comment;
    /**
     * 逻辑删除标志(0显示 1隐藏)
     */
    private Integer delFlag;


}
