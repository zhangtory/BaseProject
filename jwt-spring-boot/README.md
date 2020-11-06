## 使用方法
引入依赖：
```xml
<dependency>
    <groupId>com.zhangtory</groupId>
    <artifactId>jwt-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```

## 功能说明

### 1. 开放接口
对"/login", "/register"不进行拦截。
若需要增加可在JwtWebMvcConfig增加配置（后续开发配置功能）。

### 2. 对接口请求进行拦截检查
从headers的“Authorization”中提取token，并检查token是否有效。

### 3. 对用户颁发令牌
在用户登录后，对用户颁发令牌。调用JwtUtils的createToken方法。
目前令牌中只记录用的id、username信息，其他信息后期扩展。

### 4. 用户上下文
使用UserContext可获取用户信息。
```java
@Autowired
private UserContext userContext;
```