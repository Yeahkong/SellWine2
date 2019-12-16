package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-27 08:19
 * @Description:
 */
@Data
@TableName("tb_user_family")
public class UserFamilyEntity implements Serializable {

    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 家庭id
     */
    private Long familyId;
    /**
     * 是否主管理员
     */
    private Integer isPrimary;
    /**
     * 昵称
     */
    private String nickName;

}
