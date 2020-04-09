# Spring Cloud Netflix
使用netflix组件搭建的完整的spring cloud项目

### [Eureka-Server](./Eureka-Server)
注册中心 服务端, 可通过不同的配置文件启动多个(代码里只写了2个)节点

### [Service Provider](./Eureka-Client-Provider)
服务提供方, 提供```/euk/provider```的资源调用, 可通过配置文件启动两个节点提供服务

### [Service Consumer](./Eureka-Client-Consumer)
服务调用方, 由Ribbon选择1个可用节点, 通过RestTemplate发起请求调用(暂时先简单的用这个)

**这里有个问题:** 提供方下线后, 一段时间内, 调用方是不知道它下线了, 需要有容错机制 
 