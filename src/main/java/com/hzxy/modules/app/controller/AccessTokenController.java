package com.hzxy.modules.app.controller;

import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.app.entity.AccessTokenEntity;
import com.hzxy.modules.app.service.AccessTokenService;
import com.hzxy.modules.app.service.CommonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author liuningying
 * @email 1591686150@qq.com
 * @date 2019-11-23 15:20:38
 */
@RestController
@RequestMapping("app/accesstoken")
public class AccessTokenController {
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private CommonService commonService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("app:accesstoken:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accessTokenService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("app:accesstoken:info")
    public R info(@PathVariable("id") String id){
		AccessTokenEntity accessToken = accessTokenService.getById(id);

        return R.ok().put("accessToken", accessToken);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("app:accesstoken:save")
    public R save(@RequestBody AccessTokenEntity accessToken){
		accessTokenService.save(accessToken);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("app:accesstoken:update")
    public R update(@RequestBody AccessTokenEntity accessToken){
		accessTokenService.updateById(accessToken);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("app:accesstoken:delete")
    public R delete(@RequestBody String[] ids){
		accessTokenService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
   /* @Scheduled(fixedRate = 10000)*/
    public void task() {
        commonService.getAccessToken();
    }
}
