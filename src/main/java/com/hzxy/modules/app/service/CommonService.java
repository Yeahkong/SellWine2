package com.hzxy.modules.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.*;
import com.hzxy.modules.app.VO.UserFamilyVO;
import com.hzxy.modules.app.entity.*;
import com.hzxy.modules.app.form.UserFamilyForm;
import com.hzxy.modules.app.utils.JwtUtils;
import com.hzxy.modules.tio.common.TioPacket;
import com.hzxy.modules.tio.server.TioServerStarter;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-09-27 08:26
 * @Description:
 */
@Service("commonService")
public class CommonService {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserFamilyService userFamilyService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private TtecService ttecService;

    @Autowired
    private RoomElectricService roomElectricService;

    @Autowired
    private ElectricsService electricsService;

    @Autowired
    private AirConditioningService airConditioningService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private JwtUtils jwtUtils;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Transactional
    public R saveUserFamily(UserFamilyForm userFamilyForm){
        try{
            // 保存用户
            String dateNow = DateUtils.getDateNow();

            UserEntity userEntitySearchResult = userService.queryByMobile(userFamilyForm.getMobile());
            Long userId = 0L;
            if(userEntitySearchResult == null){
                UserEntity userEntity = new UserEntity();
                userEntity.setCreateTime(dateNow);
                userEntity.setPassword(DigestUtils.sha256Hex("123456"));
                userEntity.setMobile(userFamilyForm.getMobile());
                userEntity.setUserName(userFamilyForm.getMobile());

                userService.save(userEntity);
                QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
                userEntityQueryWrapper.lambda()
                        .eq(UserEntity::getMobile, userFamilyForm.getMobile())
                        .eq(UserEntity::getCreateTime, dateNow);

                UserEntity resultUser = userService.getOne(userEntityQueryWrapper);
                userId = resultUser.getUserId();
            }else{
                userId = userEntitySearchResult.getUserId();
            }

            // 保存家庭
            FamilyEntity familyEntitySearchResult = familyService.getOne(new QueryWrapper<FamilyEntity>()
            .lambda().eq(FamilyEntity::getFamilyAddress,userFamilyForm.getFamilyAddress()));
            Long familyId = 0L;
            if(familyEntitySearchResult ==null){

                // 聪鸟设备id的查重处理
                FamilyEntity familyEntityForCnei = familyService.getOne(new QueryWrapper<FamilyEntity>()
                .lambda().eq(FamilyEntity::getCnei,userFamilyForm.getCnei()));
                if(familyEntityForCnei==null){
                    FamilyEntity familyEntity = new FamilyEntity();
                    familyEntity.setCreateTime(dateNow);
                    familyEntity.setFamilyAddress(userFamilyForm.getFamilyAddress());
                    familyEntity.setFamilyName(userFamilyForm.getMobile()+"的家");
                    familyEntity.setRoomConstitute(userFamilyForm.getRoomConstitute());
                    String[] roomConstitute =userFamilyForm.getRoomConstitute().split("_");
                    String roomName="";
                    for(String room:roomConstitute){
                        Dictionary dictionary=dictionaryService.getById(room);
                        roomName+=dictionary.getDictName()+"_";
                    }
                    roomName = roomName.substring(0, roomName.length() - 1);
                    familyEntity.setRoomName(roomName);
                    familyEntity.setCnei(userFamilyForm.getCnei());  // 聪鸟设备id 不能重复
                    familyService.save(familyEntity);

                    QueryWrapper<FamilyEntity> familyEntityQueryWrapper = new QueryWrapper<>();
                    familyEntityQueryWrapper.lambda()
                            .eq(FamilyEntity::getCreateTime,dateNow)
                            .eq(FamilyEntity::getFamilyAddress,userFamilyForm.getFamilyAddress());
                    FamilyEntity resultFamily = familyService.getOne(familyEntityQueryWrapper);
                    familyId = resultFamily.getId();
                }else{
                    return R.error("聪鸟设备id不能重复录入");
                }
            }else{
                return R.error("家庭地址已经存在");
            }


            // 保存关系表
            UserFamilyEntity userFamilyEntity = new UserFamilyEntity();
            userFamilyEntity.setFamilyId(familyId);
            userFamilyEntity.setIsPrimary(1);
            userFamilyEntity.setUserId(userId);
            userFamilyEntity.setNickName("管理员");
            userFamilyService.save(userFamilyEntity);

            return R.ok("添加用户及家庭信息成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error("未知错误,请联系管理员");
    }

    public List<UserFamilyVO> convertorRooms(List<UserFamilyVO> resource){
        for(UserFamilyVO userFamilyVO : resource){
            if(userFamilyVO.getRoomConstitute()!=null){
                String[] roomIds = userFamilyVO.getRoomConstitute().split("_");
                StringBuffer stringBuffer = new StringBuffer();
                for(String roomId : roomIds){
                    Dictionary dictionary = dictionaryService.getById(roomId);

                    if(dictionary!=null){
                        stringBuffer.append(dictionary.getDictName());
                        stringBuffer.append(",");
                    }
                }
                String rooms = stringBuffer.toString();
                if(rooms.endsWith(",")){
                    rooms = rooms.substring(0,rooms.length()-1);
                }
                userFamilyVO.setRoomConstitute(rooms);
            }

        }
        return resource;
    }

    @Transactional
    public R deleteTtec(String[] cneis){
        try{
            ttecService.deleteBatchIds(cneis);
            for(String cnei : cneis){
                FamilyEntity familyEntity = familyService.getOne(new QueryWrapper<FamilyEntity>()
                        .eq(StringUtils.isNotEmpty(cnei),"cnei",cnei));
                if(familyEntity!=null){
                    familyEntity.setCnei("");
                    familyService.updateById(familyEntity);
                }
            }
        }catch (Exception e){
            return R.error(e.getMessage());
        }

        return R.ok();
    }

    /**
     * 发送指令给主板
     * @param boardNo 板子编号
     * @param order 指令
     * @return
     * @throws Exception
     */
    public boolean sendToBoard (String boardNo,String order) throws Exception{
        boolean flag=false;
        ChannelContext channelContext = Tio.getChannelContextByBsId(TioServerStarter.serverGroupContext,boardNo);
        if(channelContext!=null){
            TioPacket packet = new TioPacket();
            packet.setBody(order.getBytes(TioPacket.CHARSET));
            logger.info("发送指令给主板:" + order);
            Tio.sendToBsId(TioServerStarter.serverGroupContext, boardNo, packet);
            flag=true;
        }
        return flag;
    }
    public void addDevice(long familyId,long roomId,long electricId,long type,int sort) throws Exception{
        Ttec ttec=familyService.getBoardNo(familyId);
        Map condition = new HashMap();
        condition.put("family_id",familyId);
        condition.put("room_id",roomId);
        RoomElectric roomElectric = roomElectricService.getRoomElectric(condition);
        String order="{\"action\":\"add\",\"room\":\""+roomId+"\",\"num\":\""+roomId+"\",\"id\":\""+roomElectric.getId()+"\",\"deviceId\":\""+electricId+"\",\"type\":\""+type+"\",\"sort\":\""+sort+"\"}";
        sendToBoard(ttec.getScmId(),order);

    }

    /**
     * 更新空调相关的信息
     * @param air
     */
    public void updateAir(AirConditioningEntity air){
        air=airConditioningService.getOne(new QueryWrapper<AirConditioningEntity>().eq("electric_no", air.getElectricNo()));
        if(air!=null){
            airConditioningService.updateById(air);
        }
        Electrics electrics=electricsService.getOne(new QueryWrapper<Electrics>().eq("electric_no",air.getElectricNo()));
        if(electrics!=null){
            electrics.setElectricStatus(air.getStatus());
            electricsService.updateById(electrics);
        }
    }
    public AccessTokenEntity getAccessToken(){
        AccessTokenEntity token=accessTokenService.getById(1);
        String data= HttpUtil.sendPost("https://open.ys7.com/api/lapp/token/get?appKey=4085c94079af40f1877398b9c82b00fd&appSecret=f240f82ebc84d2567b40f477291b5667");
        Map map = JsonUtil.getMap4Json(JsonUtil.getMap4Json(data).get("data").toString());
        AccessTokenEntity tokenEntity=new AccessTokenEntity();
        tokenEntity.setAccessToken(map.get("accessToken").toString());
        tokenEntity.setExpireTime(DateUtils.longToDate(Long.valueOf(map.get("expireTime").toString())));
        tokenEntity.setUpdateTime(new Date());
        if(token!=null){
            if(jwtUtils.isTokenExpired(token.getExpireTime())){
                tokenEntity.setId(token.getId());
                accessTokenService.updateById(tokenEntity);
            }
        }else{
            accessTokenService.save(tokenEntity);
        }
        return token;
    }

}
