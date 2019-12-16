package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-17 14:41
 * @Description:
 */
@Data
@TableName("tb_electrics")
public class Electrics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备id
     */
    @TableId
    private Long id;
    /**
     * 电器安装端口编号
     */
    private String electricNo;
    /**
     * 电器开关状态(0关  1开)
     */
    private String electricStatus;
    /**
     * 电器详细状态,如空调24度 制冷
     */
    private String electricStatusDetail;
    /**
     * 设备名称
     */
    private String electricName;
    /**
     * 是否空调（0是 1否）
     */
    private String isAc;
    /**
     * 绑定状态（0未绑定  1已绑定）
     */
    private String bindStatus;



}
