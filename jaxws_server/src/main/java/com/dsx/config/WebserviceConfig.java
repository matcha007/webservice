package com.dsx.config;



import com.dsx.service.impl.HelloServiceImpl;
import com.dsx.service.impl.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.jaxws.support.JaxWsServiceFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author 段顺兴
 * @create 2020/06/11 20:50
 * @description
 */
@Configuration
public class WebserviceConfig {

    @Bean
    public ServletRegistrationBean<CXFServlet> cxfservlet() {
        ServletRegistrationBean<CXFServlet> cxfServletServletRegistrationBean = new ServletRegistrationBean<>(new CXFServlet(), "/ws/*");
        cxfServletServletRegistrationBean.setLoadOnStartup(1);
        return cxfServletServletRegistrationBean;
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }


    //JAX-RS发布
    @Bean
    public Endpoint endpoint1() {
        EndpointImpl endpoint = new EndpointImpl(springBus(),new HelloServiceImpl());
        //  添加日志输入、输出拦截器，观察soap请求、soap响应内容
        endpoint.getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getOutInterceptors().add(new LoggingOutInterceptor());

        endpoint.publish("/hello");
        return endpoint;
    }


    //JAX-RS发布
    @Bean
    public Endpoint endpoint2() {
        EndpointImpl endpoint = new EndpointImpl(springBus(),new UserServiceImpl());
        //  添加日志输入、输出拦截器，观察soap请求、soap响应内容
        endpoint.getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getOutInterceptors().add(new LoggingOutInterceptor());

        endpoint.publish("/user");
        return endpoint;
    }

}
