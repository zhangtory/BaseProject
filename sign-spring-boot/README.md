## 使用方法

引入依赖：
```xml
<dependency>
    <groupId>com.zhangtory</groupId>
    <artifactId>sign-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```
自定义配置
继承SignChecker并重写initCheckInterceptor和getSecret方法。
并且将bean命名为"signChecker"。
```java
@Component("signChecker")
public class SignCheckerImpl extends SignChecker {

    @Override
    public void initCheckInterceptor() {
    }

    @Override
    public String getSecret() {
        // 比如从数据库查询secret
        return "123456";
    }
}
```

## 功能说明

### 1. 默认约定
时间戳超时时间为60秒。  
签名值请求参数名称为sign。  
时间戳请求参数名称timestamp，并且时间戳为毫秒时间戳。  
默认接口列表中加入不检查的接口url。  

### 1. 签名方式
md5(k + v + ... + secret)
即对请求的参数按照key的ascii码升序排序，当value不为空时，按照key的顺序构造为key1 + value1 + key2 + value2 + ... 的字符串，并在最后拼接秘钥secret。再对整个字符串取md5摘要。

### 2. 签名接口基础请求
请求对象可以继承BaseSignRequest。
