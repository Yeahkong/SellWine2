package com.hzxy.modules.sellwine.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.PageUtils;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.sellwine.VO.WineSellRecordVO;
import com.hzxy.modules.sellwine.entity.Wine;
import com.hzxy.modules.sellwine.entity.WineSellRecord;
import com.hzxy.modules.sellwine.entity.WineUser;
import com.hzxy.modules.sellwine.service.WineSellRecordService;
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
 * @Date: 2019-12-21 13:24
 * @Description:
 */
@RestController
@RequestMapping("/wineSellRecord")
@Api("销售记录 相关Api")
public class WineSellRecordController {

    @Autowired
    private WineSellRecordService wineSellRecordService;

    @Autowired
    private WineService wineService;

    @Autowired
    private WineUserService wineUserService;

    // 查询条件： 用户真实姓名 、 手机号 、 付款时间 , 酒名称

    @GetMapping("/page")
    @ApiOperation(value = "获取分页数据,查询条件可以实用户真实姓名trueName,手机号码mobileNo,酒名称wineName,付款时间payTime")
    public R page(@RequestParam Map<String,Object> params){
        Object trueName = params.get("trueName");
        Object mobileNo = params.get("mobileNo");
        Object wineName = params.get("wineName");

        List<Long> userIds = new ArrayList<>();

        List<WineUser> wineUsers = wineUserService.list(new QueryWrapper<WineUser>()
                    .like(StringUtils.isNotEmpty(trueName),"true_name",trueName)
            .like(StringUtils.isNotEmpty(mobileNo),"mobile_no",mobileNo));
        if(wineUsers!=null && wineUsers.size()>0){
            for(WineUser wineUser:wineUsers){
                userIds.add(wineUser.getId());
            }
        }

        List<Long> wineIds = new ArrayList<>();
        List<Wine> wines = wineService.list(new QueryWrapper<Wine>()
        .like(StringUtils.isNotEmpty(wineName),"wine_name",wineName));

        if(wines!=null && wines.size()>0){
            for(Wine wine : wines){
                wineIds.add(wine.getId());
            }
        }

        params.put("userIds",userIds);
        params.put("wineIds",wineIds);

        params.remove("trueName");
        params.remove("mobileNo");
        params.remove("wineName");

        PageUtils pageUtils = wineSellRecordService.queryPage(params);
        if(pageUtils!=null && pageUtils.getList()!=null){
            List<WineSellRecord> wineSellRecords = (List<WineSellRecord>)pageUtils.getList();
            List<WineSellRecordVO> wineSellRecordVOS = new ArrayList<>();
            for(WineSellRecord wineSellRecord : wineSellRecords){
                WineSellRecordVO wineSellRecordVO = convertor(wineSellRecord);
                wineSellRecordVOS.add(wineSellRecordVO);
            }

            pageUtils.setList(wineSellRecordVOS);
        }
        return R.ok(pageUtils);

    }


    @GetMapping("single/{wineSellRecordId}")
    @ApiOperation("获取单条记录")
    public R single(@PathVariable Long wineSellRecordId){
        WineSellRecord wineSellRecord = wineSellRecordService.getById(wineSellRecordId);
        WineSellRecordVO wineSellRecordVO = convertor(wineSellRecord);
        return R.ok(wineSellRecordVO);
    }

    @PostMapping("/addWineSellRecord")
    @ApiOperation("添加销售记录")
    public R addWineSellRecord(@RequestBody WineSellRecord wineSellRecord){
        boolean saveResult = wineSellRecordService.save(wineSellRecord);
        if(saveResult){
            return R.ok("添加销售记录成功");
        }else{
            return R.error("添加销售记录失败");
        }
    }

    private WineSellRecordVO convertor(WineSellRecord wineSellRecord){
        WineSellRecordVO wineSellRecordVO = new WineSellRecordVO();
        BeanUtils.copyProperties(wineSellRecord,wineSellRecordVO);

        WineUser wineUser = wineUserService.getById(wineSellRecord.getUserId());
        if(wineUser!=null){
            wineSellRecordVO.setTrueName(wineUser.getTrueName());
            wineSellRecordVO.setMobileNo(wineUser.getMobileNo());
        }

        Wine wine = wineService.getById(wineSellRecord.getWineId());
        if(wine!=null){
            wineSellRecordVO.setWineName(wine.getWineName());
        }

        return wineSellRecordVO;
    }

}
