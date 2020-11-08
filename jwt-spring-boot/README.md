## 使用方法
引入依赖：
```xml
<dependency>
    <groupId>com.zhangtory</groupId>
    <artifactId>jwt-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```
自定义配置
继承JwtConfig并重写initJwtConfig方法。
并且将bean命名为"jwtConfig"。
```java
@Component("jwtConfig")
public class JwtConfigImpl extends JwtConfig {

    @Override
    public void initJwtConfig() {
        // TODO 设置放开的接口
        this.patterns.add("/index/**");
    }

}
```

## 功能说明

### 1. 开放接口
对"/login", "/register"不进行拦截。
若需要增加可在JwtConfig的initJwtConfig增加配置。

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