### 1.配置数据库连接
```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: stock_plat
    password: 123456
    url: jdbc:mysql://192.168.6.123:3306/stock_plat?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai
    type: com.zaxxer.hikari.HikariDataSource
```

### 2.使用代码生成器
```java
@Autowired
private CodeGenerator codeGenerator;

codeGenerator.create("tableName", "base");
```