package com.dsx;

import com.dsx.service.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JaxwsClientApplicationTests {

    @Test
    void contextLoads() {
        //  服务接口访问地址：http://localhost:8000/ws/hello

        // 创建cxf代理工厂
        JaxWsProxyFactoryBean factory = new  JaxWsProxyFactoryBean();

        //  设置远程访问服务端地址
        factory.setAddress("http://localhost:8080/ws/hello");

        //  设置接口类型
        factory.setServiceClass(HelloService.class);

        //  对接口生成代理对象
        HelloService helloService = factory.create(HelloService.class);

        //  代理对象对象  class com.sun.proxy.$Proxy34       [Java代理： 1. 静态代理；  2.动态代理（jdk接口代理、cglib子类代理）]    $CGLIB123
        System.out.println(helloService.getClass());

        // 远程访问服务端方法
        String content = helloService.sayHello("Jet");
        System.out.println(content);

    }


    @Test
    public void test() {
        HelloServiceImplService helloServiceImplService = new HelloServiceImplService();
        HelloService helloServiceImplPort = helloServiceImplService.getHelloServiceImplPort();
        String result = helloServiceImplPort.sayHello("您好");
        System.out.println(result);
    }

    @Test
    public void test1() {
        UserServiceImplService userServiceImplService = new UserServiceImplService();
        UserService userServiceImplPort = userServiceImplService.getUserServiceImplPort();
        List<User> allUsers = userServiceImplPort.findAllUsers();
        System.out.println(allUsers);
    }

    @Test
    public void test2() {

    }
}
