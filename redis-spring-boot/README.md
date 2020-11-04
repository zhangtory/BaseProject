配置文件：
集群：
spring:
  redis:
    cluster:
      nodes: 192.168.0.111:6379,192.168.0.112:6379,192.168.0.113:6379,192.168.0.114:6379,192.168.0.115:6379,192.168.0.116:6379
    timeout: 5000
    database: 0
  
单机：
spring:
  redis:    
    host: 192.168.31.50
    pool: 6379 
    timeout: 5000
    database: 0   