package com.hzxy.modules.app.VO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-26 09:36
 * @Description:
 */
@Data
@TableName("vuserfamily")
public class UserFamilyVO implements Serializable {

    private Long userFamilyId;

    private Long userId;

    private String mobile;

    private String userName;

    private Integer status;

    private Integer isPrimary;

    private Long familyId;

    private String familyName;

    private String familyAddress;

    private String roomConstitute;

    private String cnei;

    private String createTime;

    private String nickName;

}
