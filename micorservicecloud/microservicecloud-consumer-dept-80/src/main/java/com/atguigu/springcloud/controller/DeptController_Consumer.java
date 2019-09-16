package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 请编写注释
 *
 * @Auther: zxc001g
 * @Date: 2019/9/16
 */
@RestController
public class DeptController_Consumer {

    /**
     * 使用resttemplate访问restful接口非常简单无脑粗暴
     * （url，requestMap，ResponseBean.class）这三个参数分别代表rest请求地址，请求参数，HTTP响应转换成的对象类型
     */
    private static final String REST_URL_PREFIX = "HTTP://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @GetMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @GetMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    /**
     * 测试@EnableDiscoveryClient,消费端可以电泳服务发现
     *
     * @return
     */
    @GetMapping(value = "/consumer/dept/discovery")
    public Object discovery() {
        return restTemplate.getForEntity(REST_URL_PREFIX + "/dept/discovery", Object.class);
    }
}
