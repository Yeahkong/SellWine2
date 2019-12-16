package com.hzxy.modules.tio.server;

import com.hzxy.modules.tio.common.Const;
import org.tio.server.ServerGroupContext;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import java.io.IOException;

/**
 *
 * @author tanyaowu
 * 2017年4月4日 下午12:22:58
 */
public class TioServerStarter {
	//handler, 包括编码、解码、消息处理
	public static ServerAioHandler aioHandler = new TioServerAioHandler();

	//事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
	public static ServerAioListener aioListener = null;

	//一组连接共用的上下文对象
	public static ServerGroupContext serverGroupContext = new ServerGroupContext("tio-server", aioHandler, aioListener);

	//tioServer对象
	public static TioServer tioServer = new TioServer(serverGroupContext);

	/**
	 * 启动程序入口
	 */
	public static void main(String[] args) throws IOException {
		serverGroupContext.setHeartbeatTimeout(Const.TIMEOUT);

		tioServer.start(Const.SERVER, Const.PORT);
	}
}