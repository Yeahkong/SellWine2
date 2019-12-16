/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hzxy.modules.app.controller;


import com.hzxy.common.enums.StatusEnum;
import com.hzxy.common.utils.R;
import com.hzxy.common.validator.ValidatorUtils;
import com.hzxy.modules.app.VO.UserFamilyVO;
import com.hzxy.modules.app.entity.FamilyEntity;
import com.hzxy.modules.app.entity.UserEntity;
import com.hzxy.modules.app.form.LoginForm;
import com.hzxy.modules.app.service.*;
import com.hzxy.modules.app.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP登录授权
 */
@RestController
@RequestMapping("/app")
@Api("APP登录接口")
public class AppLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserFamilyVOService userFamilyVOService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private CommonService commonService;

    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        UserEntity userEntity = userService.login(form);

        if(userEntity.getStatus().equals(StatusEnum.HIDE.getCode())){
            return R.error("用户被禁用");
        }

        //生成token
        String token = jwtUtils.generateToken(userEntity.getUserId());

        // 获取家庭信息 家庭名称 家庭地址 是否主家庭成员

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        Map<String,Object> condition = new HashMap<>();
        condition.put("userId",userEntity.getUserId());
        List<UserFamilyVO> list = userFamilyVOService.getUserFamily(condition);

        map.put("userName",userEntity.getUserName());
        map.put("mobile",userEntity.getMobile());
        map.put("userId",userEntity.getUserId());
        map.put("list",commonService.convertorRooms(list));

        return R.ok(map);
    }

    @GetMapping("getRoomBy/{familyId}")
    @ApiOperation("通过家庭id获取房间")
    public R getRoomBy(@PathVariable Long familyId){
        FamilyEntity familyEntity = familyService.getById(familyId);
        List<Map<String,Object>> rooms;
        String roomConstitute = familyEntity.getRoomConstitute();
        if(roomConstitute != null && roomConstitute.length()>0){
            String[] roomIds = roomConstitute.split("_");
            String[] roomName=familyEntity.getRoomName().split("_");
            rooms = new ArrayList<>();
            /*for(String roomId : roomIds){
                Map<String,Object> room = new HashMap<>();
                Dictionary dictionary = dictionaryService.getById(roomId);
                if(dictionary!=null){
                    room.put("id",dictionary.getId());
                    room.put("name",dictionary.getDictName());
                }
                rooms.add(room);
            }*/
            for(int i=0;i<roomIds.length;i++){
                Map<String,Object> room = new HashMap<>();
                room.put("id",roomIds[i]);
                room.put("name",roomName[i]);
                rooms.add(room);
            }
            Map resultMap = new HashMap();
            resultMap.put("rooms",rooms);
            return R.ok(resultMap);
        }else{
            return R.error("没有绑定房间");
        }
    }
}
