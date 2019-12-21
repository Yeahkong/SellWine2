package com.hzxy.modules.sellwine.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.VO.WinePercentageVO;
import com.hzxy.modules.sellwine.entity.WinePercentage;
import com.hzxy.modules.sellwine.entity.WineRole;
import com.hzxy.modules.sellwine.service.WinePercentageService;
import com.hzxy.modules.sellwine.service.WineRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-12-17 17:12
 * @Description:
 */
@RestController
@RequestMapping("/winePercentage")
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
            WinePercentageVO winePercentageVO = convertor(winePercentage);
            return R.ok(winePercentageVO);
        }else{
            return R.error("获取代理商提层信息为空");
        }
    }

    @GetMapping("/allWinePercentage")
    @ApiOperation("获取全部提层信息")
    public R allWinePercentage(){
        List<WinePercentage> winePercentageList = winePercentageService.list();
        if(winePercentageList!=null && winePercentageList.size()>0){

            List<WinePercentageVO> winePercentageVOS = new ArrayList<>();
            for(WinePercentage winePercentage : winePercentageList){
                WinePercentageVO winePercentageVO = convertor(winePercentage);

                winePercentageVOS.add(winePercentageVO);
            }

            return R.ok(winePercentageVOS);
        }else{
            return R.error("暂未设置提层信息");
        }
    }

    @PostMapping("/addWinePercentage")
    @ApiOperation("添加提层信息")
    public R addWinePercentage(@RequestBody WinePercentage winePercentage){

        WinePercentage queryForDistinct = winePercentageService
                .getOne(new QueryWrapper<WinePercentage>()
        .eq("role_id",winePercentage.getRoleId()));
        if(queryForDistinct==null){
            boolean saveResult = winePercentageService.save(winePercentage);
            if(saveResult){
                return R.ok("添加提层信息成功");
            }else{
                return R.error("添加提层信息失败");
            }
        }else{
            return R.error("已经存在该级别代理商的提层信息，不能重复添加");
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

    // 分页 , 单个

    @GetMapping("/page")
    @ApiOperation("获取提层 的分页数据")
    public R page(@RequestParam Map<String,Object> params){
        PageUtils pageUtils = winePercentageService.queryPage(params);

        if(pageUtils!=null && pageUtils.getList().size()>0){
            List tempList = pageUtils.getList();
            List<WinePercentage> winePercentages = (List<WinePercentage>)tempList;
            List<WinePercentageVO> winePercentageVOS = new ArrayList<>();
            for(WinePercentage winePercentage : winePercentages){
                WinePercentageVO winePercentageVO = convertor(winePercentage);
                winePercentageVOS.add(winePercentageVO);
            }

            pageUtils.setList(winePercentageVOS);

            return R.ok(pageUtils);
        }else{
            return R.ok("数据为空");
        }

    }

    @GetMapping("/single/{percentageId}")
    @ApiOperation("获取单个提层数据")
    public R single(@PathVariable Long percentageId){
        if(percentageId!=null){

            WinePercentage winePercentage = winePercentageService.getById(percentageId);
            if(winePercentage!=null){
                WinePercentageVO winePercentageVO = convertor(winePercentage);
                return R.ok(winePercentageVO);
            }else{
                return R.error("查询数据为空");
            }
        }else{
            return R.error("参数不能为空");
        }

    }

    public WinePercentageVO convertor(WinePercentage winePercentage){
        WinePercentageVO winePercentageVO = new WinePercentageVO();
        BeanUtils.copyProperties(winePercentage,winePercentageVO);

        WineRole wineRole = wineRoleService.getById(winePercentage.getRoleId());
        winePercentageVO.setRoleName(wineRole.getRoleName());

        return winePercentageVO;
    }

}
