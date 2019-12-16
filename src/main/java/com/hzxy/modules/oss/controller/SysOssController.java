/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hzxy.modules.oss.controller;

import com.google.gson.Gson;
import com.hzxy.common.utils.*;
import com.hzxy.common.validator.ValidatorUtils;
import com.hzxy.common.validator.group.AliyunGroup;
import com.hzxy.common.validator.group.QcloudGroup;
import com.hzxy.common.validator.group.QiniuGroup;
import com.hzxy.modules.oss.cloud.CloudStorageConfig;
import com.hzxy.modules.oss.entity.SysOssEntity;
import com.hzxy.modules.oss.service.SysOssService;
import com.hzxy.modules.sys.controller.AbstractController;
import com.hzxy.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * 文件上传
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController extends AbstractController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysOssService.queryPage(params);
		SysOssEntity sysOssEntity=new SysOssEntity();
		List<SysOssEntity> list=new ArrayList<>();
        for (SysOssEntity oss:(List<SysOssEntity>)page.getList()){
        	sysOssEntity=sysOssService.getById(oss.getId());
			sysOssEntity.setUrl(Constant.getAddr()+oss.getUrl());
			list.add(sysOssEntity);
		}
        page.setList(list);
		return R.ok().put("data", page);
	}


    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("data", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@PostMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@PostMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		if (file.isEmpty()) {
			R.error("上传文件不能为空");
		}
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+1;
		String fileDir="/path/"+year+"/"+month+"/";
		String filePath="";
		String num=RandomUtil.randomNumberFixLength(6);
		if (!file.isEmpty()) {
			// 文件保存路径
			// 转存文件
			FileUtils.createDirectory(fileDir);
			//String name = file.getOriginalFilename();
			filePath = fileDir+num+".png";
			File newFile = FileUtils.getAvailableFile(filePath,0);
			file.transferTo(newFile.getAbsoluteFile());
		}
		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(filePath);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);

		return R.ok().put("data", filePath);
	}


	/**
	 * 删除
	 */
	@DeleteMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody Long[] ids){
		for(long id:ids){
		    SysOssEntity oss=sysOssService.getById(id);
		    String fileName=oss.getUrl();
            File file = new File(fileName);
            if (!file.exists()) {
                logger.debug(fileName + " 文件不存在!");
            } else {
                if (file.isFile()) {
                    FileUtils.deleteFile(fileName);
                } else {
                    FileUtils.deleteDirectory(fileName);
                }
            }
        }
        sysOssService.removeByIds(Arrays.asList(ids));
		return R.ok();
	}

}
