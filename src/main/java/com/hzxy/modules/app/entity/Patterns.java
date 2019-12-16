package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-22 14:07
 * @Description:
 */
@Data
@TableName("tb_patterns")
public class Patterns implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long familyId;

    private String patternName;
    /**
     * 模式组成 ， 房间id_指令id,多个以;隔开
     */
    private String patternConstitute;

    /**
     * 模式当前状态
     */
    private String currentStatus;

    private String createTime;

}
