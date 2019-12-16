package com.hzxy.modules.tio.controller;

import com.hzxy.common.annotation.SysLog;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sys.controller.AbstractController;
import com.hzxy.modules.tio.entity.SysTio;
import com.hzxy.modules.tio.server.TioServerStarter;
import com.hzxy.modules.tio.service.SysTioService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.Arrays;
import java.util.Map;

/**
 * @Program SmartHome
 * @Package com.hzxy.modules.tio.controller
 * @ClassName SysTioController
 * @Author liuningying
 * @Date 2019-09-05 13:20
 */
@RestController
@RequestMapping("/sys/tio")
public class SysTioController extends AbstractController {
    @Autowired
    private SysTioService sysTioService;
    @GetMapping("/list")
    @RequiresPermissions("sys:tio:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysTioService.queryPage(params);
        R r=R.ok().put("data", page);
        r.getJsonStr();
        return r;
    }
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:tio:info")
    public R info(@PathVariable("id") Long id){
        SysTio tio = sysTioService.getById(id);
        return R.ok().put("data", tio);
    }
    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:tio:save")
    public R save(@RequestBody SysTio tio){
        if(tio.getId()==null){
            sysTioService.save(tio);
        }else{
            sysTioService.updateById(tio);
        }
        return R.ok();
    }
    @SysLog("删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:tio:delete")
    public R delete(@RequestBody Long[] ids){

        sysTioService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    @DeleteMapping("/start")
    @RequiresPermissions("sys:tio:start")
    public R start(@RequestBody Long[] ids){
        R r=new R();
        for (Long id: ids){
            SysTio sysTio=sysTioService.getById(id);
            try {
                //启动服务
                TioServerStarter.serverGroupContext.setHeartbeatTimeout(sysTio.getTimeout());
                TioServerStarter.tioServer.start(sysTio.getServer(),sysTio.getPort());
                r=R.ok("服务端启动成功!服务器地址:"+sysTio.getServer()+",监听端口:"+sysTio.getPort());
            }catch (BindException e){
                logger.info(e.getMessage());
                r=R.error(-1,"该端口服务端已启用!请勿重复开启!");
            }catch (Exception e){
                e.printStackTrace();
                TioServerStarter.tioServer.stop();
                r=R.error("TCP服务端启动失败");
            }
        }
        r.getJsonStr();
        return r;
    }
    @PostMapping("/close")
    @RequiresPermissions("sys:tio:close")
    public R close() {
        TioServerStarter.tioServer.stop();
        return R.ok();
    }
}
