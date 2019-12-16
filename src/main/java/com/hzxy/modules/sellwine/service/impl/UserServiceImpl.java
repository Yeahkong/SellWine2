package com.hzxy.modules.sellwine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzxy.modules.sellwine.dao.UserDao;
import com.hzxy.modules.sellwine.entity.User;
import com.hzxy.modules.sellwine.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-16 15:11
 * @Description:
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
