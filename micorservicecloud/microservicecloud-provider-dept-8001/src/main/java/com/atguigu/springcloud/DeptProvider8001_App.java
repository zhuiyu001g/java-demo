package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 请编写注释
 *
 * @Auther: zxc001g
 * @Date: 2019/9/16
 */
@SpringBootApplication
@EnableEurekaClient   //本服务启动后辉自动注册进eureka服务中
@EnableDiscoveryClient
public class DeptProvider8001_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_App.class, args);
    }
}
