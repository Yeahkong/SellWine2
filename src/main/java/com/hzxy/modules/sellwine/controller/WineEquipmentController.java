package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.EnumUtil;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.VO.WineEquipmentVO;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.entity.WineEquipment;
import com.hzxy.modules.sellwine.entity.WineUser;
import com.hzxy.modules.sellwine.enums.DelFlagEnum;
import com.hzxy.modules.sellwine.service.WineEquipmentService;
import com.hzxy.modules.sellwine.service.WineService;
import com.hzxy.modules.sellwine.service.WineUserService;
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
 * @Date: 2019-12-19 14:03
 * @Description:
 */
@RestController
@RequestMapping("/wineEquipment")
@Api("售酒器 相关Api")
public class WineEquipmentController {

    @Autowired
    private WineEquipmentService wineEquipmentService;

    @Autowired
    private WineUserService wineUserService;

    @Autowired
    private WineService wineService;


    @GetMapping("/page")
    @ApiOperation("获取分页数据")
    public R pageData(@RequestParam Map<String,Object> params){
        PageUtils pageUtils = wineEquipmentService.queryPage(params);

        if(pageUtils!=null && pageUtils.getList().size()>0){
            List tempList = pageUtils.getList();
            List<WineEquipment> wineEquipments = (List<WineEquipment>)tempList;
            List<WineEquipmentVO> wineEquipmentVOS = new ArrayList<>();
            for(WineEquipment wineEquipment : wineEquipments){
                WineEquipmentVO wineEquipmentVO = convertor(wineEquipment);
                wineEquipmentVOS.add(wineEquipmentVO);
            }
            pageUtils.setList(wineEquipmentVOS);
            return R.ok(pageUtils);
        }else{
            return R.ok("请录入酒数据");
        }

    }

    @GetMapping("/single/{equipmentId}")
    @ApiOperation("获取单个数据")
    public R singleData(@PathVariable Long equipmentId){
        if(StringUtils.isNotEmpty(equipmentId)){
            WineEquipment wineEquipment = wineEquipmentService.getById(equipmentId);
            if(wineEquipment!=null){
                WineEquipmentVO wineEquipmentVO = convertor(wineEquipment);
                return R.ok(wineEquipmentVO);
            }else{
                return R.error("查询数据为空");
            }
        }else{
            return R.error("参数不能为空");
        }
    }

    // 修改 , 禁用/启用
    @PostMapping("/updateEquipment")
    @ApiOperation("修改售酒器信息")
    public R updateEquipment(@RequestBody WineEquipment wineEquipment){
        boolean updateResult = wineEquipmentService.updateById(wineEquipment);
        if(updateResult){
            return R.ok("修改售酒器信息成功");
        }else{
            return R.error("修改售酒器信息失败");
        }
    }

    @GetMapping("/disOrEnableEquipment")
    @ApiOperation("禁用/启用售酒器设备")
    public R disOrEnableEquipment(@RequestParam Map<String,Object> params){
        Object equipmentId = params.get("equipmentId");
        Object flag = params.get("flag");
        if(StringUtils.isNotEmpty(equipmentId) && StringUtils.isNotEmpty(flag)){
            WineEquipment wineEquipment = wineEquipmentService.getById(Long.parseLong(equipmentId.toString()));
            if(wineEquipment!=null){
                wineEquipment.setDelFlag(EnumUtil.getByCode(Integer.parseInt(flag.toString()), DelFlagEnum.class).getCode());

                boolean updateResult = wineEquipmentService.updateById(wineEquipment);
                if(updateResult){
                    return R.ok("操作成功");
                }else{
                    return R.error("操作失败");
                }
            }else{
                return R.error("查询结果为空");
            }
        }else{
            return R.error("参数不能为空");
        }
    }

    public WineEquipmentVO convertor(WineEquipment wineEquipment){
        WineEquipmentVO wineEquipmentVO = new WineEquipmentVO();
        BeanUtils.copyProperties(wineEquipment,wineEquipmentVO);

        WineUser wineUser = wineUserService.getById(wineEquipment.getUserId());
        if(wineUser!=null){
            wineEquipmentVO.setUserName(wineUser.getTrueName());
        }

        Wine wine = wineService.getById(wineEquipment.getWineId());
        if(wine!=null){
            wineEquipmentVO.setWineName(wine.getWineName());
        }
        return wineEquipmentVO;
    }
}
