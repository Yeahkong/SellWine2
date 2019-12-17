package com.hzxy.modules.sellwine.form;

import lombok.Data;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 14:36
 * @Description:
 */
@Data
public class WineUserForAdminForm {

    private String userName;

    private String password;

    private String mobileNo;

    private String trueName;

    private Long areaId;

    private Long roleId;

}
