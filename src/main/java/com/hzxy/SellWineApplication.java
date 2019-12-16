/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hzxy;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SellWineApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SellWineApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
