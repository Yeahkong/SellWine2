package com.hzxy.modules.tio.common;

import org.tio.core.intf.Packet;

/**
 * @author tanyaowu
 */
public class TioPacket extends Packet {
	private static final long serialVersionUID = -172060606924066412L;
	public static final int HEADER_LENGHT = 4;//消息头的长度
	public static final String CHARSET = "utf-8";
	/*public static ServerGroupContext serverGroupContext;
	public static ServerGroupContext getServerGroupContext() {
		return serverGroupContext;
	}
	public static void setServerGroupContext(ServerGroupContext serverGroupContext) {
		TioPacket.serverGroupContext = serverGroupContext;
	}*/
	private byte[] body;

	/**
	 * @return the body
	 */
	public byte[] getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(byte[] body) {
		this.body = body;
	}
}
