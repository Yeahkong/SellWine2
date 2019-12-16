package com.hzxy.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author liuningying
 * @email 1591686150@qq.com
 * @date 2019-11-13 09:43:45
 */
@Data
@TableName("tb_air_conditioning")
public class AirConditioningEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	/**
	 * 电器安装端口编号
	 */
	private String electricNo;
	/**
	 * 温度
	 */
	private Integer temperature;
	/**
	 * 模式（0制冷 1制热 2自动 3除湿）
	 */
	private String model;
	/**
	 * 风速（0 低速   1中速   2高速）
	 */
	private String windSpeed;
	/**
	 * 风向（0上下扫风  1左右扫风  2无扫风  3上下左右扫风）
	 */
	private String windDirection;
	/**
	 * 是否睡眠（0是  1否）
	 */
	private String sleep;
	/**
	 * 开关状态（0关 1开）
	 */
	@TableField(exist=false)
	private String status;
	/**
	 * 家庭id
	 */
	@TableField(exist=false)
	private long familyId;

}
