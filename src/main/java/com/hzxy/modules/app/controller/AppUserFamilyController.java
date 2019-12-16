package com.hzxy.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.*;
import com.hzxy.modules.app.VO.*;
import com.hzxy.modules.app.entity.*;
import com.hzxy.modules.app.entity.Dictionary;
import com.hzxy.modules.app.form.AddPatternForm;
import com.hzxy.modules.app.form.RoomFrom;
import com.hzxy.modules.app.form.UserFamilyForm;
import com.hzxy.modules.app.service.*;
import com.hzxy.modules.tio.server.TioServerStarter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

import java.util.*;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-29 15:33
 * @Description:
 */
@RestController
@Api("用户及家庭接口")
@RequestMapping("/appUserFamily")
public class AppUserFamilyController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserFamilyVOService userFamilyVOService;

    @Autowired
    private UserFamilyService userFamilyService;

    @Autowired
    private UserService userService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private TtecService ttecService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private RoomElectricService roomElectricService;

    @Autowired
    private ElectricsService electricsService;

    @Autowired
    private PatternsService patternsService;

    @Autowired
    private ElectricInstructService electricInstructService;

    @Autowired
    private OperateRecordService operateRecordService;

    @GetMapping("/page")
    @ApiOperation(value = "获取分页的用户家庭数据")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = userFamilyVOService.queryPage(params);

        List<UserFamilyVO> resource = (List<UserFamilyVO>)page.getList();

        page.setList(commonService.convertorRooms(resource));

        return  R.ok().put("data",page);
    }

    @GetMapping("/familyRoomElectrics/{familyId}")
    @ApiOperation(value = "获取家庭房间的家电组成")
    public R familyRoomElectrics(@PathVariable Long familyId){
        FamilyEntity familyEntity = familyService.getById(familyId);
        if(familyEntity!=null){
            String roomConstitute = familyEntity.getRoomConstitute();
            List<FamilyRoomElectricInfoVO> result = new ArrayList<>();
            FamilyRoomElectricInfoVO familyRoomElectricInfoVO = null;
            if(StringUtils.isNotEmpty(roomConstitute)){
                String[] roomIds = roomConstitute.split("_");
                for(String roomId : roomIds){
                    Dictionary dictionary = dictionaryService.getById(roomId);
                    if(dictionary!=null){

                        familyRoomElectricInfoVO =  new FamilyRoomElectricInfoVO();
                        familyRoomElectricInfoVO.setFamilyId(familyId);
                        familyRoomElectricInfoVO.setRoomId(dictionary.getId());
                        familyRoomElectricInfoVO.setRoomName(dictionary.getDictName());

                        Map<String,Object> condition = new HashMap<>();
                        condition.put("family_id",familyId);
                        condition.put("room_id",dictionary.getId());
                        RoomElectric roomElectric = roomElectricService.getRoomElectric(condition);
                        if(roomElectric!=null){
                            String electrics = roomElectric.getElectrics();
                            if(StringUtils.isNotEmpty(electrics)){
                                String[] electricIds = electrics.split("_");
                                StringBuffer stringBufferElectric= new StringBuffer();
                                for(String electricId : electricIds){
                                    Dictionary electricDictionary = dictionaryService.getById(electricId);
                                    if(electricDictionary!=null){
                                        stringBufferElectric.append(electricDictionary.getDictName()+",");
                                    }
                                }
                                String finalElectricZH = stringBufferElectric.toString();
                                if(finalElectricZH.endsWith(",")){
                                    finalElectricZH = finalElectricZH.substring(0,finalElectricZH.length()-1);
                                }
                                familyRoomElectricInfoVO.setElectrics(finalElectricZH);
                            }
                        }

                    }
                    if(familyRoomElectricInfoVO!=null){
                        result.add(familyRoomElectricInfoVO);
                    }
                }
                return R.ok().put("data",result);
            }else{
                return R.error("查询该家庭房间组成为空");
            }

        }else{
            return R.error("查询家庭对象为空");
        }

    }

    @PostMapping("/familySingleRoomElectrics")
    @ApiOperation(value = "获取某家庭单个房间电器集合")
    public R familySingleRoomElectrics(@RequestBody Map<String,Long> params){

        Map<String,Object> condition = new HashMap<>();
        condition.put("family_id",params.get("familyId"));
        condition.put("room_id",params.get("roomId"));
        RoomElectric roomElectric = roomElectricService.getRoomElectric(condition);

        FamilyRoomElectricInfoVO familyRoomElectricInfoVO = new FamilyRoomElectricInfoVO();
        familyRoomElectricInfoVO.setFamilyId(Long.valueOf(params.get("familyId")));
        familyRoomElectricInfoVO.setRoomId(Long.valueOf(params.get("roomId")));
        Dictionary dictionaryForRoom = dictionaryService.getById(params.get("roomId"));
        if(dictionaryForRoom!=null) {
            familyRoomElectricInfoVO.setRoomName(dictionaryForRoom.getDictName());

            if (roomElectric != null) {
                familyRoomElectricInfoVO.setElectrics(roomElectric.getElectrics());
                String electricTypeIds = roomElectric.getElectrics();
                String electricIds = roomElectric.getElectricIds();
                if (StringUtils.isNotEmpty(electricIds)) {
                    String deviceId="";
                    String typeId="";
                    String[] ids = electricIds.split("_");
                    String[] typeIds = electricTypeIds.split("_");
                    if(!params.get("patternId").toString().equals("0")){
                        Patterns patterns=patternsService.getById(params.get("patternId"));
                        if(patterns.getPatternConstitute()!=null) {
                            String[] constitute = patterns.getPatternConstitute().split(";");
                            for (String str : constitute) {
                                String[] id = str.split("_");
                                if (id[0].equals(roomElectric.getRoomId().toString())) {
                                    ElectricInstruct instruct=electricInstructService.getById(id[1]);
                                    deviceId += instruct.getElectricId()+ "_";
                                    typeId+=id[2]+"_";
                                }
                            }
                            if (!deviceId.equals("")) {
                                deviceId = deviceId.substring(0, deviceId.length() - 1);
                                String[] deviceIds = deviceId.split("_");
                                ids = StringUtils.getC(ids, deviceIds);
                                typeId = typeId.substring(0, typeId.length() - 1);
                                String[] types = typeId.split("_");
                                typeIds = StringUtils.getC(typeIds, types);
                            }
                        }

                    }
                    List<ElectricsVO> electricsList = new ArrayList<>();
                    for (int i = 0; i < ids.length; i++) {
                        Electrics electrics = electricsService.getById(ids[i]);
                        if (electrics != null) {
                            ElectricsVO electricsVO = new ElectricsVO();
                            BeanUtils.copyProperties(electrics, electricsVO);
                            electricsVO.setElectricTypeId(typeIds[i]);
                            electricsList.add(electricsVO);
                        }
                    }
                    familyRoomElectricInfoVO.setElectricsVOList(electricsList);
                }
            } else {
                familyRoomElectricInfoVO.setElectrics("");
                familyRoomElectricInfoVO.setElectricsVOList(new ArrayList<>());
            }
            Map result = new HashMap();
            result.put("data", familyRoomElectricInfoVO);
            return R.ok(result);
        }else{
            return R.error("房间信息有误");
        }
    }

    @PostMapping("/updateUserFamily")
    @ApiOperation(value = "修改用户家庭信息")
    public R updateUserFamily(@RequestBody UserFamilyVO userFamilyVO){
        // 手机号 家庭名称 家庭地址
        if(userFamilyVO.getUserFamilyId()!=null){

            UserFamilyEntity userFamilyEntity = userFamilyService.getById(userFamilyVO.getUserFamilyId());

            if(userFamilyEntity!=null){
                UserEntity userEntity = userService.getById(userFamilyEntity.getUserId());
                if(userEntity != null){
                    if(StringUtils.isNotEmpty(userFamilyVO.getMobile())){
                        if(MobileUtils.siMobileOrNo(userFamilyVO.getMobile())){
                            userEntity.setMobile(userFamilyVO.getMobile());
                            boolean saveUserEntityResult = userService.updateById(userEntity);
                            if(!saveUserEntityResult){
                                return R.error("修改用户手机号出现异常");
                            }
                        }else{
                            return R.error("手机号码格式不正确");
                        }
                    }
                }else{
                    return R.error("查询用户对象为空");
                }

                FamilyEntity familyEntity = familyService.getById(userFamilyEntity.getFamilyId());
                if(familyEntity!=null){

                    FamilyEntity familyEntityForCnei = familyService.getOne(new QueryWrapper<FamilyEntity>()
                            .lambda().eq(FamilyEntity::getCnei,userFamilyVO.getCnei()));
                    boolean isNewCnei = false;
                    if(familyEntityForCnei==null){
                        isNewCnei = true;
                    }else if (familyEntityForCnei.getId().equals(userFamilyVO.getFamilyId())){
                        isNewCnei = false;
                    }else{
                        return R.error("cnei不能重复");
                    }

                    if(isNewCnei){

                        if(StringUtils.isNotEmpty(userFamilyVO.getCnei())){
                            familyEntity.setCnei(userFamilyVO.getCnei());
                        }
                    }

                    if(StringUtils.isNotEmpty(userFamilyVO.getFamilyName())){
                        familyEntity.setFamilyName(userFamilyVO.getFamilyName());
                    }

                    if(StringUtils.isNotEmpty(userFamilyVO.getFamilyAddress())){
                        familyEntity.setFamilyAddress(userFamilyVO.getFamilyAddress());
                    }
                    if(StringUtils.isNotEmpty(userFamilyVO.getRoomConstitute())){
                        familyEntity.setRoomConstitute(userFamilyVO.getRoomConstitute());
                        String[] roomConstitute =userFamilyVO.getRoomConstitute().split("_");
                        String roomName="";
                        for(String room:roomConstitute){
                            Dictionary dictionary=dictionaryService.getById(room);
                            roomName+=dictionary.getDictName()+"_";
                        }
                        roomName = roomName.substring(0, roomName.length() - 1);
                        familyEntity.setRoomName(roomName);
                    }

                    boolean saveFamilyResult = familyService.updateById(familyEntity);
                    if(!saveFamilyResult){
                        return R.error("保存家庭信息出现异常");
                    }
                }else{
                    return R.error("查询家庭对象为空");
                }
            }else{
                return R.error("查询关系对象为空");
            }
            return R.ok("修改信息成功");
        }else{
            return R.error("标志id不能为空");
        }
    }

    @GetMapping("/info/{userFamilyId}")
    @ApiOperation(value = "单个用户家庭信息")
    public R info(@PathVariable Long userFamilyId){

        if(userFamilyId!=null){
            Map<String,Object> condition = new HashMap<>();
            condition.put("userFamilyId",userFamilyId);
            List<UserFamilyVO> userFamilyVOList = userFamilyVOService.getUserFamily(condition);
            if(userFamilyVOList!=null && userFamilyVOList.size()>0){
                return R.ok().put("data",userFamilyVOService.getUserFamily(condition).get(0));
            }else{
                return R.error("查询用户家庭信息为空");
            }
        }else{
            return R.error("标志id不能为空");
        }

    }

    @PostMapping("/saveUserFamily")
    @ApiOperation(value = "保存用户及家庭")
    public R saveUserFamily(@RequestBody UserFamilyForm userFamilyForm){
        if(userFamilyForm!=null){
            if(StringUtils.isNotEmpty(userFamilyForm.getMobile())
                    && StringUtils.isNotEmpty(userFamilyForm.getFamilyAddress())
            && StringUtils.isNotEmpty(userFamilyForm.getCnei())
            && StringUtils.isNotEmpty(userFamilyForm.getRoomConstitute())){
                if(MobileUtils.siMobileOrNo(userFamilyForm.getMobile())){
                    return  commonService.saveUserFamily(userFamilyForm);
                }else{
                    return R.error("手机号码格式不正确");
                }
            }else{
                return R.error("手机号、家庭地址、cnei、房间组成为空,请检查");
            }
        }else{
            return R.error("参数不能为空");
        }

    }

    @DeleteMapping("/deleteUserFamilyRelation")
    @ApiOperation(value = "删除用户家庭间关系")
    public R deleteUserFamilyRelation(@RequestBody Long[] userFamilyIds){
        try{
            userFamilyService.deleteBatch(userFamilyIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error("删除用户家庭间关系出现异常");
        }

    }

    @GetMapping("/getInitData")
    @ApiOperation(value = "获取添加用户家庭时的初始化数据")
    public R getInitData(){
        // 主控板列表
        List<Ttec> ttecList = ttecService.list();

        // 房间列表
        Map roomCondition = new HashMap();
        roomCondition.put("dictType","房间类型");
        List<Dictionary> roomList = dictionaryService.queryMap(roomCondition);

        Map resultMap = new HashMap();
        resultMap.put("ttecList",ttecList);
        resultMap.put("roomList",roomList);

        return R.ok(resultMap);

    }


    @GetMapping("/getPatterns/{familyId}")
    @ApiOperation(value = "根据家庭id获取该家庭的模式列表")
    public R getPatterns(@PathVariable Long familyId){
        List<PatternsVO> patternsVOS = patternsService.getPatternsByFamilyId(familyId);
        if(patternsVOS!=null && patternsVOS.size()>0){
            Map resultMap = new HashMap();
            resultMap.put("patternsVOS",patternsVOS);

            return R.ok(resultMap);
        }else{
            return R.error("获取模式列表失败");
        }
    }

    @GetMapping("/getPatternDetail/{patternId}")
    @ApiOperation(value = "获取模式的详细信息")
    public R getPatternDetail(@PathVariable Long patternId){
        Patterns patterns = patternsService.getById(patternId);

        if(patterns!=null){
            String patternConstitute = patterns.getPatternConstitute();
            PatternDetailVO patternDetailVO = new PatternDetailVO();
            patternDetailVO.setPatternId(patternId);
            patternDetailVO.setPatternName(patterns.getPatternName());
            patternDetailVO.setCurrentStatus(patterns.getCurrentStatus());
            List<PatternTemp> temps = new ArrayList<>();
            if(StringUtils.isNotEmpty(patternConstitute)){
                String[] constitute = patternConstitute.split(";");
                for(String str : constitute){
                    PatternTemp patternTemp = new PatternTemp();
                    String[] ids = str.split("_");
                    Long roomId = Long.valueOf(ids[0]);
                    Long electricInstructId = Long.valueOf(ids[1]);
                    ElectricInstruct electricInstruct = electricInstructService.getById(electricInstructId);
                    if(electricInstruct!=null){
                        String actionName=electricInstruct.getActionName();
                        if(ids[2].equals("6")){
                            String[] actionNames=actionName.split("_");
                            if(actionNames[0].equals("0")){
                                actionName="关";
                            }else{
                                String model="";
                                if(actionNames[1].equals("0")){
                                    model="制冷";
                                }else if(actionNames[1].equals("1")){
                                    model="制热";
                                }else if(actionNames[1].equals("2")){
                                    model="自动";
                                }else{
                                    model="除湿";
                                }
                                actionName=model+" "+actionNames[2]+"℃";
                            }
                        }else{
                            if(actionName.equals("0")){
                                actionName="关";
                            }else{
                                actionName="开";
                            }
                        }
                        patternTemp.setActionName(actionName);
                        patternTemp.setTypeId(ids[2]);
                        patternTemp.setInstructId(electricInstructId);
                        Long electricId = electricInstruct.getElectricId();
                        patternTemp.setElectricId(electricId);
                        Electrics electrics = electricsService.getById(electricId);
                        patternTemp.setElectricName(electrics.getElectricName());
                        Dictionary dictionary = dictionaryService.getById(roomId);
                        patternTemp.setRoomName(dictionary.getDictName());
                        patternTemp.setRoomId(roomId);
                        temps.add(patternTemp);
                    }
                }
                patternDetailVO.setPatternTempList(temps);

            }else{
                patternDetailVO.setPatternTempList(temps);
            }
            return R.ok(patternDetailVO);
        }else{
            return R.error("该场景模式不存在");
        }
    }

    @PostMapping("/updateRoom")
    @ApiOperation(value = "修改房间名称")
    public R updateRoom(@RequestBody RoomFrom from){
       try {
           FamilyEntity familyEntity=familyService.getById(from.getFamilyId());
           String[] roomConstitute = familyEntity.getRoomConstitute().split("_");
           String[] roomName = familyEntity.getRoomName().split("_");
           for(String name:roomName){
               if(from.getRoomName().equals(name)){
                   return R.error("房间名称不能重复");
               }
           }
           String name="";
           for(int i=0;i<roomConstitute.length;i++){
               if(roomConstitute[i].equals(from.getRoomId())){
                   name+=from.getRoomName()+"_";
               }else{
                   name+=roomName[i]+"_";
               }
           }
           name = name.substring(0, name.length() - 1);
           familyEntity.setRoomName(name);
           boolean result=familyService.updateById(familyEntity);
           if(result){
               return R.ok("修改成功");
           }else{
               return R.error("修改失败");
           }
       }catch (Exception e){
           return R.error("服务器异常,请稍后重试");
       }
    }

    @PostMapping("/addRoom")
    @ApiOperation(value = "添加房间")
    public R addRoom(@RequestBody RoomFrom from){
        FamilyEntity familyEntity=familyService.getById(from.getFamilyId());
        String roomConstitute = familyEntity.getRoomConstitute();
        String roomName = familyEntity.getRoomName();
        String[] roomConstitutes =roomConstitute.split("_");
        String[] roomNames = roomName.split("_");
        int id=8;
        for(String roomId:roomConstitutes){
            if(Integer.valueOf(roomId)==id){
                id++;
            }
        }
        if(StringUtils.isNotEmpty(roomConstitute)){
            roomConstitute+="_"+id;
            for(String name:roomNames){
                if(from.getRoomName().equals(name)){
                    return R.error("房间名称不能重复");
                }
            }
            roomName+="_"+from.getRoomName();
        }else{
            roomConstitute=""+id;
            roomName=from.getRoomName();
        }
        familyEntity.setRoomConstitute(roomConstitute);
        familyEntity.setRoomName(roomName);
        boolean result=familyService.updateById(familyEntity);
        if(result){
            return R.ok("添加成功");
        }else{
            return R.error("添加失败");
        }

    }

    @PostMapping("/addPattern")
    @ApiOperation(value = "新建模式")
    public R addPattern(@RequestBody Patterns patterns){
        if(StringUtils.isNotEmpty(patterns.getPatternName())) {
            Patterns pattern = patternsService.getOne(new QueryWrapper<Patterns>().eq("pattern_name", patterns.getPatternName()));
            if (pattern != null) {
                return R.error("该模式名称已存在");
            } else {
                patterns.setCreateTime(DateUtils.getDateNow());
                boolean result = patternsService.save(patterns);
                if (result) {
                    return R.ok("添加成功");
                } else {
                    return R.error("添加失败");
                }
            }
        }else{
            return R.error("模式名称不能为空");
        }
    }

    @PostMapping("/updatePattern")
    @ApiOperation(value = "修改模式")
    public R updatePattern(@RequestBody Patterns patterns){
        boolean result=patternsService.updateById(patterns);
        if(result){
            return R.ok("修改成功");
        }else{
            return R.error("修改失败");
        }
    }

    @PostMapping("/addDeviceToPattern")
    @ApiOperation(value = "添加设备到模式里")
    public R addDeviceToPattern(@RequestBody AddPatternForm form){
        Patterns patterns=patternsService.getById(form.getPatternId());
        String actionName,actionInstruct="";
        if(patterns!=null){
            Electrics electrics=electricsService.getById(form.getElectricId());
            String constitute=patterns.getPatternConstitute();
            if(form.getTypeId()==6){
                actionName=form.getAir().getStatus()+"_"+form.getAir().getModel()+"_"+
                           form.getAir().getTemperature()+"_"+form.getAir().getWindSpeed()
                           +"_"+form.getAir().getWindDirection()+"_"+form.getAir().getSleep();
                actionInstruct="{\"action\":\"air\",\"num\":\""+electrics.getElectricNo()+"\",\"status\":\""+form.getAir().getStatus()+"\",\"model\":\""+form.getAir().getModel()+"\",\"temp\":\""+form.getAir().getTemperature()+"\",\"speed\":\""+form.getAir().getWindSpeed()+"\",\"wind\":\""+form.getAir().getWindDirection()+"\",\"sleep\":\""+form.getAir().getSleep()+"\"}";
            }else{
                actionName=form.getStatus();
                if(form.getStatus().equals("0")){
                    actionInstruct="{\"action\":\"close\",";
                }else{
                    actionInstruct="{\"action\":\"open\",";
                }
                actionInstruct+="\"num\":\""+electrics.getElectricNo()+"\"}";
            }
            ElectricInstruct instruct=new ElectricInstruct();
            instruct.setElectricId(form.getElectricId());
            instruct.setActionName(actionName);
            instruct.setActionInstruct(actionInstruct);
            electricInstructService.save(instruct);
            if(StringUtils.isNotEmpty(constitute)){
                constitute=constitute+";"+form.getRoomId()+"_"+instruct.getId()+"_"+form.getTypeId();
            }else{
                constitute=form.getRoomId()+"_"+instruct.getId()+"_"+form.getTypeId();
            }
            patterns.setPatternConstitute(constitute);
            patternsService.updateById(patterns);
        }
        return R.ok();
    }

    @GetMapping("/getDeviceAction/{instructId}")
    @ApiOperation(value = "获取设备动作")
    public R getDeviceAction(@PathVariable Long instructId){
        ElectricInstruct instruct=electricInstructService.getById(instructId);
        Map map=new HashMap();
        if(instruct!=null) {
            Electrics electrics=electricsService.getById(instruct.getElectricId());
            map.put("id",electrics.getId());
            map.put("electricNo",electrics.getElectricNo());
            if (instruct.getActionName().length() > 1) {
                String[] actionNames = instruct.getActionName().split("_");
                map.put("status", actionNames[0]);
                map.put("model", actionNames[1]);
                map.put("temperature",Integer.valueOf(actionNames[2]));
                map.put("windSpeed", actionNames[3]);
                map.put("windDirection", actionNames[4]);
                map.put("sleep", actionNames[5]);
            } else {
                map.put("electricStatus", instruct.getActionName());
                map.put("electricName",electrics.getElectricName());
            }
            return R.ok(map);
        }else{
            return R.error("没有该设备操作");
        }

    }

    @PostMapping("/updateDeviceAction")
    @ApiOperation(value = "修改设备动作")
    public R updateDeviceAction(@RequestBody AddPatternForm form) {
        ElectricInstruct instruct=electricInstructService.getById(form.getInstructId());
        if(instruct!=null) {
            Electrics electrics=electricsService.getById(instruct.getElectricId());
            String actionName,actionInstruct="";
            if(instruct.getActionName().length()>1){
                actionName=form.getAir().getStatus()+"_"+form.getAir().getModel()+"_"+
                        form.getAir().getTemperature()+"_"+form.getAir().getWindSpeed()
                        +"_"+form.getAir().getWindDirection()+"_"+form.getAir().getSleep();
                actionInstruct="{\"action\":\"air\",\"num\":\""+electrics.getElectricNo()+"\",\"status\":\""+form.getAir().getStatus()+"\",\"model\":\""+form.getAir().getModel()+"\",\"temp\":\""+form.getAir().getTemperature()+"\",\"speed\":\""+form.getAir().getWindSpeed()+"\",\"wind\":\""+form.getAir().getWindDirection()+"\",\"sleep\":\""+form.getAir().getSleep()+"\"}";
            }else{
                actionName=form.getStatus();
                if(form.getStatus().equals("0")){
                    actionInstruct="{\"action\":\"close\",";
                }else{
                    actionInstruct="{\"action\":\"open\",";
                }
                actionInstruct+="\"num\":\""+electrics.getElectricNo()+"\"}";
            }
            instruct.setActionName(actionName);
            instruct.setActionInstruct(actionInstruct);
            electricInstructService.updateById(instruct);
            return R.ok("修改成功");
        }else{
            return R.ok("该设备指令不存在");
        }
    }

    @PostMapping("/enablePattern")
    @ApiOperation(value = "启动模式")
    public R enablePattern(@RequestBody Map<String,Long> params) {
        try {
            Ttec ttec=familyService.getBoardNo(Long.valueOf(params.get("familyId").toString()));
            ChannelContext channelContext = Tio.getChannelContextByBsId(TioServerStarter.serverGroupContext,ttec.getScmId());
            if(channelContext!=null){
                String order="";
                Patterns patterns=patternsService.getById(params.get("patternId"));
                String constitute=patterns.getPatternConstitute();
                OperateRecordEntity record=new OperateRecordEntity();
                record.setOprName("启动模式");
                record.setOprTime(new Date());
                if(StringUtils.isNotEmpty(constitute)) {
                    String[] constitutes = constitute.split(";");
                    for(String str : constitutes){
                        String[] ids = str.split("_");
                        ElectricInstruct electricInstruct = electricInstructService.getById(Long.valueOf(ids[1]));
                        order+=electricInstruct.getActionInstruct();
                        if(HexUtil.str2HexStr(order).length()+11>=3072){
                           record.setOprContent(order);
                           operateRecordService.save(record);
                           commonService.sendToBoard(ttec.getScmId(),order);
                           order="";
                        }
                    }
                    commonService.sendToBoard(ttec.getScmId(),order);
                    record.setOprContent(order);
                    operateRecordService.save(record);
                    return R.ok("启动成功");
                }else{
                    return R.error("请绑定设备");
                }

            }else{
                return R.error("控制面板不在线");
            }
        } catch (Exception e) {
            return R.error("数据异常");
        }
    }

    @PostMapping("/getFamily")
    @ApiOperation(value = "获取家庭")
    public R getFamily(@RequestBody Map<String,Long> params) {
        try {
            Map<String,Object> condition = new HashMap<>();
            condition.put("userId",params.get("userId").toString());
            List<UserFamilyVO> list = userFamilyVOService.getUserFamily(condition);
            for (int i=0;i<list.size();i++){
                if(list.get(i).getFamilyId().toString().equals(params.get("familyId").toString())){
                    list.remove(i);
                }
            }
            return R.ok(commonService.convertorRooms(list));
        } catch (Exception e) {
            return R.error("数据异常");
        }
    }

    @PostMapping("/updateFamilyName")
    @ApiOperation(value = "修改家庭名称")
    public R updateFamilyName(@RequestBody FamilyEntity familyEntity){
        FamilyEntity family=familyService.getById(familyEntity.getId());
        family.setFamilyName(familyEntity.getFamilyName());
        boolean result=familyService.updateById(family);
        if(result){
            return R.ok("修改成功");
        }else{
            return R.error("修改失败");
        }
    }


}
