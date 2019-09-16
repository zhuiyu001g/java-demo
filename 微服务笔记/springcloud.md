## 微服务是什么
  * 微服务是一种架构模式或者说是一种架构风格，他提倡将单一的应用程序划分成一组小的服务，每个服务运行在其独立的自己的进程中，服务之间相互协调，互相配合，为用户提供最终价值
  * 服务之间采用轻量级的通信沟通（通常是基于HTTP的RESTful API) dubbon是通过RPC
  * 每个服务都围绕着具体业务进行构建，并且能够被独立地部属到生产环境
  * 应尽量避免统一的，集中的服务管理机制，对具体的一个服务而言，应根据业务上下文，先择合适的语言，工具对其进行构建 ，可以有一个非常轻量级的集中式管理来协调这些服务，可以使用不同的语言来编写服务，也可以使用不同的数据存储。
  * 总结：微服务化的核心就是将传统的一站式应用，根据业务拆分成一个一个的服务，彻底地去耦合，每一个微服务提供单个业务功能的服务，一个服务做一件事情，从技术角度看就是一种小而独立的处理过程，类似进程概念，能够自行单独启动或销毁，拥有自己独立的数据库。
  
## eureka
  *Eureka 三大角色：1，Eureka Server提供服务注册和发现
                  2，Sercvice Provider服务提供方将自身服务注册的Eureka，从而是服务消费方能够找到
                  3，Service Consumer服务消费方从Eureka获取注册服务列表，从而能够消费服务
  * 遵守AP原则
  * Eureka是什么：是一个基于REST的夫妇，用于定位服务，以实现云端中间层服务发现和故障转移。服务注册与发现对于微服务来说非常重要的，有了服务发现预注册，
    只需要使用服务的标识，就可以访问到服务，而不需要修改服务调用的配置文件了。功能类似于dubbo的注册中心，比如Zookeeper
   * eureka 采用了c-s的设计架构。Eureka Server作为服务注册功能的服务器，它是服务注册中心
   * 而系统中的其他微服务，使用Eureka的客户端链接到Eureka Server并维持心跳链接
     。这样系统的为湖人员就可以通过Eureka Server来监控系统中各个微服务是否正常运行。SpringCloud的一些其他模块（比如Zuul）就可以通过Eureka Server来发现系统中的其它微服务，并执行相关逻辑
   * Eureka包含二哥组件：Eureka Server和Eureka Client
   * Eureka Server提供服务注册服务：各个节点启动后，会在Eureka Server中进行注册，这样Eureka Server中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到
   * Eyreka Client是一个java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的，使用轮询（round-robin）负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳（默认周期30s）。如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除（默认90s）
   * 假如我需要引入cloud的一个新技术组件基本上有两步：
     1，新增一个相关的maven配置依赖坐标
        <!--eureka server服务端-->
                <dependency>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-starter-eureka-server</artifactId>
                </dependency>
     2，在主启动类上面，标注的启动该新组件技术的相关注解标签
        @EnableEurekaServer
        
        zuul
        @EnableZuulProxy
     3，java业务逻辑编码
     
     EurekaServer ok
     EurekaClient 
     将微服务provider注册进Eureka
      <dependency>
                         <groupId>org.springframework.cloud</groupId>
                         <artifactId>spring-cloud-starter-eureka</artifactId>
                     </dependency>
       <dependency>
                          <groupId>org.springframework.cloud</groupId>
                          <artifactId>spring-cloud-starter-config</artifactId>
                      </dependency>
                      
                      
## 自我保护机制
  * 某一时刻一个微服务不可用了，eureka不会立刻清理，依旧会对改微服务的信息进行保存
  
## 在分布式数据库中CAP原理CAP+BASE
  * 传统的ACID分别是什么：1，A（atomicity）原子性2，C（consistency）一致性3，I（isolation）独立性4，D（durability）持久性
  * CAP C：consistency强一致性 A：availability可用性 P：partition tolerance 分区容错性
  * 经典的CAO图
  * CAP的3进2
  * BASE 是什么
  * 分布式 + 集群简介
  * CAP理论就是说在分布式存储系统中，最多只能实现2点，而由于当前的网络硬件肯定会出现延迟丢包等问题，所以分区容错性是我们必须要实现的
    所以我们只能在一致性和可用性之间权衡，没有NoSQL系统能同时保证这三点
  * zookeeper保证CP  Eureka保证AP 