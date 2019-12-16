/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hzxy.modules.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 定时任务配置
 * 基于jdbc的作业存储 , 配置quartz属性文件，持久化任务调度信息,即使系统崩溃重启，任务调度信息将自动恢复
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);

        //quartz参数
        Properties prop = new Properties();
        // 设置调度器的实例名称instanceName和实例id instanceId
        // 如果使用jobstoreTX 实例名称严禁使用DefaultQuartzScheduler , 内存方式instanceid默认为DefaultQuartzScheduler，重名的时候，默认使用内存的配置

        prop.put("org.quartz.scheduler.instanceName", "RenRenScheduler");
        //如果使用集群 ,instanceId必须唯一,设置成Auto
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        //线程池配置
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        //  指定多少个工作者线程被创建用来处理 Job
        prop.put("org.quartz.threadPool.threadCount", "20");
        // 设置工作者线程的优先级（最大值10，最小值1，常用值5）
        prop.put("org.quartz.threadPool.threadPriority", "5");
        //JobStore配置  持久化配置（存储方式使用JobStoreTX，也就是数据库）
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        //集群配置  , 一台服务器就不用了
        prop.put("org.quartz.jobStore.isClustered", "false");
       /* prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");*/


        // 用来指定调度引擎设置触发器超时的"临界值" , 单位毫秒
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        // 数据库中quartz表的表名前缀
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        // 这必须是一个从 LOCKS 表查询一行并对这行记录加锁的 SQL 语句。
        // 假如未设置，默认值就是 SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE，
        // 这能在大部分数据库上工作。{0} 会在运行期间被前面你配置的 TABLE_PREFIX 所替换。
        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");

        //PostgreSQL数据库，需要打开此注释
        //prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");

        factory.setQuartzProperties(prop);

        factory.setSchedulerName("RenrenScheduler");
        //延时启动
        factory.setStartupDelay(30);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
