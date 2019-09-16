package com.atguigu.springcloud.cfgbean;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 请编写注释
 *
 * @Auther: zxc001g
 * @Date: 2019/9/16
 */
@Configuration //boot -->spring applicationContext.xml ---@Configuration配置 ConfigBean = applicationContext.xml
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
