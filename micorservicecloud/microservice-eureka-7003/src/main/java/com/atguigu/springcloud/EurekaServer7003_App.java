package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 请编写注释
 *
 * @Auther: zxc001g
 * @Date: 2019/9/16
 */
@SpringBootApplication
@EnableEurekaServer   //Eureka Server服务器端启动，接受其它微服务注册进来
public class EurekaServer7003_App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7003_App.class, args);
    }
}
