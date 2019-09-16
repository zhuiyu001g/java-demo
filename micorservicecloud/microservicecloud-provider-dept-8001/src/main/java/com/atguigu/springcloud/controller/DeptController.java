package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 请编写注释
 *
 * @Auther: zxc001g
 * @Date: 2019/9/16
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient client;

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptService.findByIdGet(id);
    }

    @GetMapping(value = "/dept/list")
    public List<Dept> list() {
        return deptService.findAllList();
    }

    @GetMapping(value = "dept/discovery")
    public Object discovery(){
        List<String> list = client.getServices();
        System.out.println("****"+list);
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        srvList.stream().forEach(serviceInstance -> System.out.println(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri()));
        return this.client;
    }
}
