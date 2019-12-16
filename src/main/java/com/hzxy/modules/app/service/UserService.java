/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hzxy.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.modules.app.entity.UserEntity;
import com.hzxy.modules.app.form.LoginForm;

import java.util.Map;

/**
 * 用户
 *
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	UserEntity login(LoginForm form);

	PageUtils queryPage(Map<String, Object> params);

	void deleteBatch(Long[] userIds);

	boolean updateUser(UserEntity userEntity);
}
