## 使用方法
引入依赖：
```xml
<dependency>
    <groupId>com.zhangtory</groupId>
    <artifactId>mybatis-plus-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```

配置数据库连接，和mybatis-plus一致。
```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: username
    password: 123456
    url: jdbc:mysql://IP:3306/dbName?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai
    type: com.zaxxer.hikari.HikariDataSource
```

## 功能说明
### 1. 加入了MyBatis-plus的分页插件和乐观锁插件。
可见文档：https://baomidou.com/guide/interceptor.html#%E4%BD%BF%E7%94%A8%E6%96%B9%E5%BC%8F-%E4%BB%A5%E5%88%86%E9%A1%B5%E6%8F%92%E4%BB%B6%E4%B8%BE%E4%BE%8B

### 2.使用代码生成器
生成代码方法：
```java
@Autowired
private CodeGenerator codeGenerator;

codeGenerator.create("tableName", "base");
```
模板在resources/templates下，可根据自己实际情况修改。
