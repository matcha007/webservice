package com.dsx;

import com.dsx.entity.User;
import com.dsx.service.IUserService;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.MediaType;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class JaxrsClientApplicationTests {

    @Test
    public void testSave(){
        User user = new User();
        user.setId(100);
        user.setUsername("Jerry");
        user.setCity("gz");
        // 通过WebClient对象远程调用服务端
        WebClient
                .create("http://localhost:8080/ws/userService/user")
                .type(MediaType.APPLICATION_JSON)  // 指定请求的数据格式为json
                .post(user);
    }

    @Test
    public void testGet(){
        // 查询一个
        User user =
                WebClient
                        .create("http://localhost:8080/ws/userService/user/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .get(User.class);
        System.out.println(user);
    }


}
