/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hzxy.modules.tio.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.hzxy.common.entity.DataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SysTio
 * @author liuningying
 * @version 2019-09-05
 */
@Data
@TableName("sys_tio")
public class SysTio extends DataEntity<SysTio> {

	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String server;		// 服务器地址
	private Integer port;		// 端口号
	private Integer timeout;		// 心跳时间
	private String connectionState;		// 连接状态

}
