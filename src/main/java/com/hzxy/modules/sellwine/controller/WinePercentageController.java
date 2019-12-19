package com.hzxy.modules.sellwine.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.VO.WinePercentageVO;
import com.hzxy.modules.sellwine.entity.WinePercentage;
import com.hzxy.modules.sellwine.entity.WineRole;
import com.hzxy.modules.sellwine.form.WinePercentageForm;
import com.hzxy.modules.sellwine.service.WinePercentageService;
import com.hzxy.modules.sellwine.service.WineRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 17:12
 * @Description:
 */
@RestController
@RequestMapping("/wine_percentage")
@Api("代理商提层 相关Api")
public class WinePercentageController {

    @Autowired
    private WinePercentageService winePercentageService;

    @Autowired
    private WineRoleService wineRoleService;

    @GetMapping("/getByRoleId/{roleId}")
    @ApiOperation("获取代理商提层")
    public R getByRoleId(@PathVariable Long roleId){
        WinePercentage winePercentage = winePercentageService.getOne(
                new QueryWrapper<WinePercentage>()
                .eq("role_id",roleId)
        );
        if(winePercentage!=null){
            return R.ok(winePercentage);
        }else{
            return R.error("获取代理商提层信息为空");
        }
    }

    @GetMapping("/allWinePercentage")
    @ApiOperation("获取全部提层信息")
    public R allWinePercentage(){
        List<WinePercentage> winePercentageList = winePercentageService.list();
        if(winePercentageList!=null && winePercentageList.size()>0){
            // 处理转换为 roleName
            List<WinePercentageVO> winePercentageVOS = new ArrayList<>();
            for(WinePercentage winePercentage : winePercentageList){
                WinePercentageVO winePercentageVO = new WinePercentageVO();
                BeanUtils.copyProperties(winePercentage,winePercentageVO);

                WineRole wineRole = wineRoleService.getById(winePercentage.getRoleId());
                winePercentageVO.setRoleName(wineRole.getRoleName());

                winePercentageVOS.add(winePercentageVO);
            }

            return R.ok(winePercentageVOS);
        }else{
            return R.error("暂未设置提层信息");
        }
    }

    @PostMapping("/addWinePercentage")
    @ApiOperation("添加提层信息")
    public R addWinePercentage(@RequestBody WinePercentageForm winePercentageForm){
        WinePercentage winePercentage = new WinePercentage();
        BeanUtils.copyProperties(winePercentageForm,winePercentage);

        boolean saveResult = winePercentageService.save(winePercentage);
        if(saveResult){
            return R.ok("添加提层信息成功");
        }else{
            return R.error("添加提层信息失败");
        }
    }

    @PostMapping("/updateWinePercentage")
    @ApiOperation("修改提层信息")
    public R updateWinePercentage(@RequestBody WinePercentage winePercentage){
        WinePercentage queryForDistinct = winePercentageService.getOne(
                new QueryWrapper<WinePercentage>()
                .eq("role_id",winePercentage.getRoleId())
        );
        if(queryForDistinct!=null){
            return R.error("已存在该级别代理商提层信息,不能重复录入");
        }else{
            boolean updateResult = winePercentageService.updateById(winePercentage);
            if(updateResult){
                return R.ok("修改该级别代理商提层信息成功");
            }else{
                return R.error("修改提层信息失败");
            }
        }
    }

}
