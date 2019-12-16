package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-22 16:20
 * @Description:
 */
@Data
@TableName("tb_electric_instruct")
public class ElectricInstruct implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 对应 electrics 表中的id
     */
    private Long electricId;
    /**
     * 动作名称
     */
    private String actionName;
    /**
     * 动作指令
     */
    private String actionInstruct;

}
