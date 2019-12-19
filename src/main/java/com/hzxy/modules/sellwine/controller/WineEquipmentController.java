package com.hzxy.modules.sellwine.controller;

import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.modules.sellwine.VO.WineEquipmentVO;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.entity.WineEquipment;
import com.hzxy.modules.sellwine.entity.WineUser;
import com.hzxy.modules.sellwine.service.WineEquipmentService;
import com.hzxy.modules.sellwine.service.WineService;
import com.hzxy.modules.sellwine.service.WineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        List<WineEquipment> wineEquipments = (List<WineEquipment>)pageUtils.getList();
        if(wineEquipments!=null && wineEquipments.size()>0){
            List<WineEquipmentVO> wineEquipmentVOS = new ArrayList<>();
            for(WineEquipment wineEquipment : wineEquipments){
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

                wineEquipmentVOS.add(wineEquipmentVO);

            }
            pageUtils.setList(wineEquipmentVOS);
            return R.ok(pageUtils);
        }else{
            return R.error("请录入酒数据");
        }



    }

}
