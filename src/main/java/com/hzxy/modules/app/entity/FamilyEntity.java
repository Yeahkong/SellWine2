package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-25 13:59
 * @Description:
 */
@Data
@TableName("tb_family")
public class FamilyEntity implements Serializable {

    /**
     * 家庭id
     */
    @TableId
    private Long id;
    /**
     * 家庭名称
     */
    private String familyName;
    /**
     * 房间组成
     */
    private String roomConstitute;
    /**
     * 房间组成名称
     */
    private String roomName;
    /**
     * 聪鸟设备id
     */
    private String cnei;
    /**
     * 家庭地址
     */
    private String familyAddress;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;


}
