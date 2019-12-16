package com.hzxy.modules.tio.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzxy.common.utils.*;
import com.hzxy.modules.app.entity.*;
import com.hzxy.modules.app.service.*;
import com.hzxy.modules.jpush.PushExample;
import com.hzxy.modules.tio.common.JsonData;
import com.hzxy.modules.tio.common.TioPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.Tio;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TioServerAioHandler
 * @Author liuningying
 * @Date 2019-09-05
 */
public class TioServerAioHandler implements ServerAioHandler {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	TtecService ttecService = (TtecService) SpringContextUtils.getBean("ttecService");
	RoomElectricService roomElectricService = (RoomElectricService) SpringContextUtils.getBean("roomElectricService");
	ElectricsService electricsService = (ElectricsService) SpringContextUtils.getBean("electricsService");
	CommonService commonService = (CommonService) SpringContextUtils.getBean("commonService");
	OperateRecordService operateRecordService = (OperateRecordService) SpringContextUtils.getBean("operateRecordService");
	FamilyService familyService = (FamilyService) SpringContextUtils.getBean("familyService");
	PatternsService patternsService = (PatternsService) SpringContextUtils.getBean("patternsService");
	ElectricInstructService electricInstructService = (ElectricInstructService) SpringContextUtils.getBean("electricInstructService");
	/**
	 * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
	 * 总的消息结构：消息头 + 消息体
	 * 消息头结构：    4个字节，存储消息体的长度
	 * 消息体结构：   对象的json串的byte[]
	 */
	@Override
	public TioPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
		//提醒：buffer的开始位置并不一定是0，应用需要从buffer.position()开始读取数据
		//收到的数据组不了业务包，则返回null以告诉框架数据不够
		if (readableLength < TioPacket.HEADER_LENGHT) {
			return null;
		}

		//读取消息体的长度
		int bodyLength = buffer.getInt();

		//数据不正确，则抛出AioDecodeException异常
		if (bodyLength < 0) {
			throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
		}

		//计算本次需要的数据长度
		int neededLength = TioPacket.HEADER_LENGHT + bodyLength;
		//收到的数据是否足够组包
		int isDataEnough = readableLength - neededLength;
		// 不够消息体长度(剩下的buffe组不了消息体)
		if (isDataEnough < 0) {
			return null;
		} else //组包成功
		{
			TioPacket imPacket = new TioPacket();
			if (bodyLength > 0) {
				byte[] dst = new byte[bodyLength];
				buffer.get(dst);
				imPacket.setBody(dst);
			}
			return imPacket;
		}
	}

	/**
	 * 编码：把业务消息包编码为可以发送的ByteBuffer
	 * 总的消息结构：消息头 + 消息体
	 * 消息头结构：    4个字节，存储消息体的长度
	 * 消息体结构：   对象的json串的byte[]
	 */
	@Override
	public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
		TioPacket tioPacket = (TioPacket) packet;
		byte[] body = tioPacket.getBody();
		int bodyLen = 0;
		if (body != null) {
			bodyLen = body.length;
		}

		//bytebuffer的总长度是 = 消息头的长度 + 消息体的长度
		int allLen = TioPacket.HEADER_LENGHT + bodyLen;
		//创建一个新的bytebuffer
		ByteBuffer buffer = ByteBuffer.allocate(allLen);
		//设置字节序
		buffer.order(groupContext.getByteOrder());

		//写入消息头----消息头的内容就是消息体的长度
		buffer.putInt(bodyLen);
		//写入消息体
		if (body != null) {
			buffer.put(body);
		}
		return buffer;
	}

	
	/**
	 * 处理消息
	 */
	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		TioPacket tioPacket = (TioPacket) packet;
		byte[] body = tioPacket.getBody();
		if (body != null) {
			TioPacket tp = new TioPacket();
			String message = new String(body, TioPacket.CHARSET);
			logger.info("收到的消息："+message);
			try{
				JsonData jsonData=(JsonData) JsonUtil.getObject4JsonString(message,JsonData.class);
				String action=jsonData.getAction();
				logger.info(action);
				switch (action){
					case "heartbeat":
						//心跳包处理方法
						heartbeat(jsonData,channelContext,tp);
						break;
					case "addReturn":
						//添加设备回复
						addReturn(jsonData);
						break;
					case "openReturn":
						//添加设备回复
						openOrClose(jsonData,1);
						break;
					case "closeReturn":
						//添加设备回复
						openOrClose(jsonData,0);
						break;
					case "checkReturn":
						//获取设备信息
						getDeviceInfo(jsonData);
						break;
					case "airReturn":
						//获取设备信息
						airReturn(jsonData);
						break;
					case "enablePattern":
						//启用场景
						enablePattern(jsonData);
						break;
				}

			}catch (Exception e){
				logger.info("json格式有问题");
				/*tp.setBody("".getBytes(TioPacket.CHARSET));
				Tio.send(channelContext, tp);*/
			}
		}
		return;
	}

	/**
	 * 心跳包处理方法
	 * @param jsonData
	 * @param channelContext
	 * @param tp
	 * @throws Exception
	 */
	public void heartbeat(JsonData jsonData,ChannelContext channelContext,TioPacket tp)throws Exception{
		String boardNo=jsonData.getBoardNo();
		if(StringUtils.isNotEmpty(boardNo)) {
			Tio.bindBsId(channelContext, boardNo);
			try {
				String str = "{\"action\":\"heartReturn\",\"boardNo\":\"" + boardNo + "\"}";
				tp.setBody(str.getBytes(TioPacket.CHARSET));
				Tio.send(channelContext, tp);
				logger.info("心跳包返回:" + str);
				Ttec ttec=ttecService.getOne(new QueryWrapper<Ttec>().eq("scm_id", boardNo));
				if(ttec==null){
					ttec=new Ttec();
					ttec.setScmId(boardNo);
					ttec.setProgramVersion(jsonData.getVersion());
					ttec.setNetType(jsonData.getType());
					ttec.setCnei(boardNo);
					ttec.setCreateTime(DateUtils.getDateNow());
					ttec.setOnLineStatus(0);
					ttecService.save(ttec);
					logger.info("添加新主板");
				}else{
					ttec.setProgramVersion(jsonData.getVersion());
					ttec.setNetType(jsonData.getType());
					ttec.setOnLineStatus(0);
					ttecService.updateTtec(ttec);
					logger.info("更新主板");
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
	}

	/**
	 * 添加设备后板子回复处理方法
	 * @param jsonData
	 */
	public void addReturn(JsonData jsonData){
        if(jsonData.getSuccess().equals("1")){
			RoomElectric roomElectric=roomElectricService.getById(jsonData.getId());
			String[] electricIds = roomElectric.getElectricIds().split("_");
			String[] bindStatus = roomElectric.getBindStatus().split("_");
			String status="";
			for(int i=0;i<electricIds.length;i++){
				if(electricIds[i].equals(jsonData.getDeviceId())){
					status+="0_";
				}else{
					status+=bindStatus[i]+"_";
				}
			}
			status = status.substring(0, status.length() - 1);
			roomElectric.setBindStatus(status);
			roomElectricService.updateById(roomElectric);
		}
	}

	public void openOrClose(JsonData jsonData,int num){
		Electrics electrics=electricsService.getOne(new QueryWrapper<Electrics>().eq("electric_no", jsonData.getNum()));
		if(jsonData.getSuccess().equals("1")){
			electrics.setElectricStatus(num+"");
			electricsService.updateById(electrics);
		}
	}

	public void getDeviceInfo(JsonData jsonData){
		Map map =new HashMap();
		map.put("electricNo", jsonData.getNum());
		map.put("temperature", jsonData.getTemp());
		map.put("model", jsonData.getModel());
		map.put("windSpeed", jsonData.getSpeed());
		map.put("windDirection", jsonData.getWind());
		map.put( "sleep", jsonData.getSleep());
		map.put("status",jsonData.getStatus());
		PushExample.testSendPush(map,"getAc",jsonData.getId());
		OperateRecordEntity record=new OperateRecordEntity();
		record.setOprName("推送空调信息");
		record.setOprContent(map.toString());
		record.setOprTime(new Date());
		operateRecordService.save(record);
		AirConditioningEntity air=new AirConditioningEntity();
		air.setModel(jsonData.getModel());
		air.setTemperature(Integer.valueOf(jsonData.getTemp()));
		air.setWindSpeed(jsonData.getSpeed());
		air.setWindDirection(jsonData.getWind());
		air.setElectricNo(jsonData.getNum());
		air.setStatus(jsonData.getStatus());
		commonService.updateAir(air);
	}

	public void airReturn(JsonData jsonData){
		if(jsonData.getSuccess().equals("0")){
			logger.info(jsonData.getNum()+"操作失败");
		}else{
			logger.info(jsonData.getNum()+"操作成功");
		}
		OperateRecordEntity record=new OperateRecordEntity();
		record.setOprName("操作空调回复");
		Map map=new HashMap();
		map.put("electricNo",jsonData.getNum());
		map.put("success",jsonData.getSuccess());
		record.setOprContent(map.toString());
		record.setOprTime(new Date());
		operateRecordService.save(record);

	}

	public void enablePattern(JsonData jsonData) throws  Exception{
		Ttec ttec=ttecService.getOne(new QueryWrapper<Ttec>().eq("scm_id", jsonData.getBoardNo()));
		FamilyEntity family=familyService.getOne(new QueryWrapper<FamilyEntity>().eq("cnei", ttec.getCnei()));
		String patternName="回家模式";
		if(jsonData.getType().equals("0")){
			patternName="离家模式";
		}
		List<Map<String, Object>> queryResult = patternsService.listMaps(new QueryWrapper<Patterns>()
				.eq("family_id",family.getId())
				.eq("pattern_name",patternName));
		String constitute=queryResult.get(0).get("pattern_constitute").toString();
		String order="";
		if(StringUtils.isNotEmpty(constitute)) {
			String[] constitutes = constitute.split(";");
			for(String str : constitutes){
				String[] ids = str.split("_");
				ElectricInstruct electricInstruct = electricInstructService.getById(Long.valueOf(ids[1]));
				order+=electricInstruct.getActionInstruct();
				if(HexUtil.str2HexStr(order).length()+11>=3072){
					commonService.sendToBoard(ttec.getScmId(),order);
					order="";
				}
			}
			commonService.sendToBoard(ttec.getScmId(),order);
		}
	}


}
