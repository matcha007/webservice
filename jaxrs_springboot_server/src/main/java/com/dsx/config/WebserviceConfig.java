package com.dsx.config;

import com.dsx.service.IUserService;
import com.dsx.service.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 段顺兴
 * @create 2020/06/11 20:50
 * @description
 */
@Configuration
public class WebserviceConfig {

    @Bean
    public ServletRegistrationBean<CXFServlet> cxfservlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/ws/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }


    @Autowired
    private UserServiceImpl userService;

    //JAX-RS发布
    @Bean
    public Server restfullServer() {

        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getOutInterceptors().add(new LoggingOutInterceptor());
        endpoint.setBus(springBus());
        endpoint.setAddress("/userService");
        endpoint.setServiceBean(userService);
        return endpoint.create();
    }
}
