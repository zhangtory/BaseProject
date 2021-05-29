## 使用方法
引入依赖：
```xml
<dependency>
    <groupId>com.zhangtory.base</groupId>
    <artifactId>jwt-spring-boot-starter</artifactId>
    <version>1.0.0</version>
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
此处需要设置issue颁发机构，jwt验证的secretKey，和开放接口路径等。

## 功能说明

### 1. 开放接口
对"/login", "/register"不进行拦截。
若需要增加可在JwtConfig的initJwtConfig增加配置。

### 2. 对接口请求进行拦截检查
从headers的“Authorization”中提取token，并检查token是否有效。

### 3. 对用户颁发令牌
在用户登录后，对用户颁发令牌。调用JwtHelper的createToken方法。
目前令牌中只记录用的id、username信息，其他信息后期扩展。

### 4. 用户上下文
使用UserContext可获取用户信息。
```java
@Autowired
private UserContext userContext;
```

### 5. jwt令牌操作
使用JwtHelper可对令牌进行操作。UserContext也是基于JwtHelper获取用户信息的。
```java
@Autowired
private JwtHelper jwtHelper;
```