package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 请编写注释
 * 
 * @author 谢朝辉
 * @Date: 2019/9/16
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)//链式风格访问
public class Dept implements Serializable {
    /**
     * 主键
     */
    private Long deptno;
    /**
     * 部门名称
     */
    private String dname;
    /**
     * 来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库
     * 
     */
    private String db_source;

}