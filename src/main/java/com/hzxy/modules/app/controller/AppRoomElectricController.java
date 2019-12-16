package com.hzxy.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.R;
import com.hzxy.common.utils.StringUtils;
import com.hzxy.modules.app.entity.*;
import com.hzxy.modules.app.form.OperationForm;
import com.hzxy.modules.app.form.RoomElectricForm;
import com.hzxy.modules.app.service.*;
import com.hzxy.modules.tio.server.TioServerStarter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-16 09:18
 * @Description:
 */
@RestController
@Api("房屋家电相关接口")
@RequestMapping("/appRoomElectric")
public class AppRoomElectricController {

    @Autowired
    private RoomElectricService roomElectricService;

    @Autowired
    private ElectricsService electricsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private AirConditioningService airConditioningService;
    @Autowired
    private OperateRecordService operateRecordService;

    @PostMapping("/getRoomElectrics")
    @ApiOperation("根据familyId和roomId获取家电")
    public R getRoomElectrics(@RequestBody Map<String,Object> params){
        List<Map<String,Object>> queryResult =  roomElectricService.getRoomElectrics(params);

        Map result = new HashMap();
        result.put("familyId",params.get("familyId"));
        result.put("roomId",params.get("roomId"));
        result.put("electrics",queryResult);

        return R.ok(result);

    }

    @PostMapping("/addElectric")
    @ApiOperation("添加家电")
    public R addElectric(@RequestBody RoomElectricForm roomElectricForm)throws Exception{
        Electrics checkIsExist = electricsService.getOne(new QueryWrapper<Electrics>()
        .eq(StringUtils.isNotEmpty(roomElectricForm.getElectricNo()),"electric_no",roomElectricForm.getElectricNo()));
        if(checkIsExist==null){
            // 添加家电
            Electrics newElectrics = new Electrics();
            newElectrics.setElectricName(roomElectricForm.getElectricName());
            newElectrics.setElectricNo(roomElectricForm.getElectricNo());
            if(roomElectricForm.getElectricTypeId()==6){
                newElectrics.setIsAc("0");
            }else{
                newElectrics.setIsAc("1");
            }
            boolean saveResult = electricsService.save(newElectrics);

            Long electricId = null;
            if(saveResult){
                Electrics queryResult = electricsService.getOne(new QueryWrapper<Electrics>()
                        .eq("electric_no",roomElectricForm.getElectricNo()));
                electricId = queryResult.getId();
            }else{
                return R.error("添加设备失败");
            }

            Map condition = new HashMap();
            condition.put("family_id",roomElectricForm.getFamilyId());
            condition.put("room_id",roomElectricForm.getRoomId());
            RoomElectric roomElectric = roomElectricService.getRoomElectric(condition);
            if(roomElectric!=null){
                String electrics = roomElectric.getElectrics();
                String electricIds = roomElectric.getElectricIds();
                String bindStatus=roomElectric.getBindStatus();
                String sort=roomElectric.getSort();
                int count=1;
                for(String el:electrics.split("_")){
                    if(roomElectricForm.getElectricTypeId().equals(Long.valueOf(el))){
                        count++;
                    }
                }
                if(StringUtils.isNotEmpty(electrics)){
                    electrics = electrics+"_"+roomElectricForm.getElectricTypeId();
                }else{
                    electrics = roomElectricForm.getElectricTypeId().toString();
                }

                if(StringUtils.isNotEmpty(electricIds)){
                    electricIds = electricIds+"_"+electricId.toString();
                }else{
                    electricIds = electricId.toString();
                }

                if(StringUtils.isNotEmpty(bindStatus)) {
                    bindStatus =bindStatus+ "_1";
                }else{
                    bindStatus="1";
                }
                if(StringUtils.isNotEmpty(sort)) {
                    sort =sort+ "_" + count;
                }else{
                    bindStatus="1";
                }
                roomElectric.setElectricIds(electricIds);
                roomElectric.setElectrics(electrics);
                roomElectric.setBindStatus(bindStatus);
                roomElectric.setSort(sort);
                boolean updateResult = roomElectricService.updateById(roomElectric);
                if(updateResult){
                    commonService.addDevice(roomElectricForm.getFamilyId(),roomElectricForm.getRoomId(),electricId,roomElectricForm.getElectricTypeId(),count);
                }else{
                    return R.error("添加设备失败");
                }
            }else{
                RoomElectric newRoomElectric = new RoomElectric();
                newRoomElectric.setFamilyId(roomElectricForm.getFamilyId());
                newRoomElectric.setRoomId(roomElectricForm.getRoomId());
                newRoomElectric.setElectrics(roomElectricForm.getElectricTypeId().toString());
                newRoomElectric.setElectricIds(electricId.toString());
                newRoomElectric.setBindStatus("1");
                newRoomElectric.setSort("1");

                boolean saveRoomElectricResult = roomElectricService.save(newRoomElectric);
                if(saveRoomElectricResult){
                    commonService.addDevice(roomElectricForm.getFamilyId(),roomElectricForm.getRoomId(),electricId,roomElectricForm.getElectricTypeId(),1);
                }else{
                    return R.error("添加设备失败");
                }
            }
        }else{
            return R.error("设备编号已经存在");
        }
        if(roomElectricForm.getElectricTypeId()==6){
            AirConditioningEntity air=new AirConditioningEntity();
            air.setElectricNo(roomElectricForm.getElectricNo());
            airConditioningService.save(air);
        }
        return R.ok("添加设备成功");

    }
    @PostMapping("/getDeviceDetail")
    @ApiOperation("获取设备详情")
    public R getDeviceDetail(@RequestBody Map<String,Object> params){
       try{
           Ttec ttec=familyService.getBoardNo(Long.valueOf(params.get("familyId").toString()));
           ChannelContext channelContext = Tio.getChannelContextByBsId(TioServerStarter.serverGroupContext,ttec.getScmId());
           if(channelContext!=null){
               Electrics electrics=electricsService.getById(params.get("id").toString());
               if(electrics.getIsAc().equals("0")){
                   String order="{\"action\":\"check\",\"num\":\""+electrics.getElectricNo()+"\",\"id\":\""+params.get("familyId").toString()+"\"}";
                   boolean flag=commonService.sendToBoard(ttec.getScmId(),order);
                   OperateRecordEntity record=new OperateRecordEntity();
                   record.setOprName("发送check指令");
                   record.setOprContent(order);
                   record.setOprTime(new Date());
                   operateRecordService.save(record);
                   AirConditioningEntity air=airConditioningService.getOne(new QueryWrapper<AirConditioningEntity>().eq("electric_no",electrics.getElectricNo()));
                   air.setStatus(electrics.getElectricStatus());
                   air.setFamilyId(Long.valueOf(params.get("familyId").toString()));
                   return R.ok(air);
               }else{
                   return R.ok(electrics);
               }
           }else{
               return R.error("控制面板不在线");
           }
       }catch (Exception e){
           return R.error("数据异常");
       }
    }

    @PostMapping("/OpenOrCloseDevice")
    @ApiOperation("开关设备")
    public R OpenOrCloseDevice(@RequestBody OperationForm form) throws Exception{
        try {
            Ttec ttec=familyService.getBoardNo(form.getFamilyId());
            String order="{\"action\":\""+form.getAction()+"\",\"num\":\""+form.getElectricNo()+"\"}";
            boolean flag=commonService.sendToBoard(ttec.getScmId(),order);
            if(flag){
                return R.ok();
            }else{
                return R.error("控制面板不在线");
            }
        }catch (Exception e){
            return R.error("数据异常");
        }
    }

    @PostMapping("/operateAir")
    @ApiOperation("操作空调")
    public R operateAir(@RequestBody AirConditioningEntity air) throws Exception{
        try {
            Ttec ttec=familyService.getBoardNo(air.getFamilyId());
            String order="{\"action\":\"air\",\"num\":\""+air.getElectricNo()+"\",\"status\":\""+air.getStatus()+"\",\"temp\":\""+air.getTemperature()+"\",\"model\":\""+air.getModel()+"\",\"speed\":\""+air.getWindSpeed()+"\",\"wind\":\""+air.getWindDirection()+"\",\"sleep\":\""+air.getSleep()+"\"}";
            boolean flag=commonService.sendToBoard(ttec.getScmId(),order);
            if(flag){
                OperateRecordEntity record=new OperateRecordEntity();
                record.setOprName("操作空调");
                record.setOprContent(order);
                record.setOprTime(new Date());
                operateRecordService.save(record);
                commonService.updateAir(air);
                return R.ok();
            }else{
                return R.error("控制面板不在线");
            }
        }catch (Exception e){
            return R.error("数据异常");
        }
    }

}
