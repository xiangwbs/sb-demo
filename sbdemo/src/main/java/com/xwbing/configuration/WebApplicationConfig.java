package com.xwbing.configuration;

import com.xwbing.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

/**
 * 说明: 程序配置
 * 项目名称: sbdemo
 * 创建时间: 2017/5/10 16:36
 * 作者:  xiangwb
 */
@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationConfig.class);

    /***
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("addInterceptors......");
        //登录判断
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/user/login", "/servlet/captchaCode", "/doc.html", "/swagger-ui.html");
        super.addInterceptors(registry);
    }

    /**
     * 线程池
     * @return
     */
    @Bean
    public static ThreadPoolTaskExecutor getPoolTaskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(5);
        poolTaskExecutor.setKeepAliveSeconds(30000);
        poolTaskExecutor.setMaxPoolSize(1000);
        poolTaskExecutor.setQueueCapacity(200);
        return poolTaskExecutor;
    }

    /**
     *文件上传解析器
     * @return
     */
    @Bean
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(104857600);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
    /**
     * encoding编码问题
     *
     * @return
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
