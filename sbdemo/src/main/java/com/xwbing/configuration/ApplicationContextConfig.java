package com.xwbing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 说明: 程序上下文配置
 * 项目名称: sbdemo
 * 创建时间: 2017/5/10 16:36
 * 作者:  xiangwb
 */
@Configuration
//@Import(xxx.class)//用来导入其他配置类
//@ImportResource("classpath:applicationContext.xml")//用来加载其他xml配置文件
public class ApplicationContextConfig {
    /**
     * 线程池
     *
     * @return
     */
    @Bean(name = "taskExecutor")
    public static ThreadPoolTaskExecutor getPoolTaskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(5);
        poolTaskExecutor.setKeepAliveSeconds(30000);
        poolTaskExecutor.setMaxPoolSize(1000);
        poolTaskExecutor.setQueueCapacity(200);
        return poolTaskExecutor;
    }
}
