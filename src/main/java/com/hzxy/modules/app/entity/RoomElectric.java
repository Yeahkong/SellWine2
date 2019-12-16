package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-15 16:15
 * @Description: 家庭房屋家电组成描述表
 */
@Data
@TableName("tb_room_electric")
public class RoomElectric {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long familyId;

    private Long roomId;

    /**
     * 设备类型组成 ， 格式为1_2_3 , 对应dictionary
     */
    private String electrics;

    /**
     * 具体的设备id， 格式为1_2_3 , 对应electrics表
     */
    private String electricIds;

    /**
     * 绑定状态 ， 0成功 1 失败
     */
    private String bindStatus;
    /**
     * 同一设备类型排序
     */
    private String sort;

}
