package com.xwbing.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 项目名称: sb-demo
 * 创建时间: 2017/9/18 9:24
 * 作者: xiangwb
 * 说明: web.xml
 */
@Order(1)
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() { //DispatcherServlet映射,从"/"开始
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {//根容器
        return new Class<?>[]{ApplicationContextConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//Spring mvc容器
        return new Class<?>[]{DispatcherServletConfig.class};
    }
}
