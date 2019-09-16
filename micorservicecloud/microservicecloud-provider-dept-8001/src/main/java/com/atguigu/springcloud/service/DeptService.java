package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;

import java.util.List;

/**
 * 请编写注释
 *
 * @Auther: zxc001g
 * @Date: 2019/9/16
 */
public interface DeptService {

    boolean addDept(Dept dept);

    Dept findByIdGet(Long id);

    List<Dept> findAllList();
}
