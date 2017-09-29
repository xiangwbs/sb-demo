package com.xwbing.configuration;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.xwbing.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明: 程序配置
 * 项目名称: sbdemo
 * 创建时间: 2017/5/10 16:36
 * 作者:  xiangwb
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.xwbing.controller"}, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = RestController.class)})
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServletConfig.class);

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
     * 扩展消息转换器，增加fastjson
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getFastJsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    /**
     * 请求处理程序映射适配器,使用fastJson
     *
     * @return
     */
    @Bean
    public HttpMessageConverter getFastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_HTML);//避免IE出现下载JSON文件的情况
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        httpMessageConverter.setSupportedMediaTypes(mediaTypes);
        return httpMessageConverter;
    }

    /**
     * 文件上传解析器
     *
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
