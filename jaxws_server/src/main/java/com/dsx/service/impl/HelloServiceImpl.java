package com.dsx.service.impl;


import com.dsx.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return name + ",Welcome to Itheima!";
    }
}
