## 使用方法
引入依赖：
```xml
<dependency>
    <groupId>com.zhangtory</groupId>
    <artifactId>redis-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```
增加配置：
集群：
```yml
spring:
  redis:
    cluster:
      nodes: 192.168.0.111:6379,192.168.0.112:6379,192.168.0.113:6379,192.168.0.114:6379,192.168.0.115:6379,192.168.0.116:6379
    timeout: 5000
    database: 0
```
单机：
```yml
spring:
  redis:    
    host: 192.168.31.50
    pool: 6379 
    timeout: 5000
    database: 0  
```
 
## 功能说明

redis辅助工具RedisHelper，提供了基础的redis操作，对所有ttl增加随机时间，防止缓存雪崩。
