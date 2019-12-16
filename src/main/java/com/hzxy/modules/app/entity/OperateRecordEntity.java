package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author liuningying
 * @email 1591686150@qq.com
 * @date 2019-12-02 15:57:55
 */
@Data
@TableName("tb_operate_record")
public class OperateRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * tokenid
	 */
	@TableId
	private Integer id;
	/**
	 * 操作名称
	 */
	private String oprName;
	/**
	 * 操作内容
	 */
	private String oprContent;
	/**
	 * 操作时间
	 */
	private Date oprTime;

}
