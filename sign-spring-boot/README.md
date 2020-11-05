## 使用方法

引入依赖：
```xml
<dependency>
    <groupId>com.zhangtory</groupId>
    <artifactId>sign-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```
配置文件中配置secret
```yml
sign:
  secret: 123456
```

## 功能说明

### 1. 时间戳校验
毫秒时间戳，仅允许时间误差在60秒（默认）内。

### 2. 签名方式
md5(k + v + ... + secret)
即对请求的参数按照key的ascii码升序排序，当value不为空时，按照key的顺序构造为key1 + value1 + key2 + value2 + ... 的字符串，并在最后拼接秘钥secret。再对整个字符串取md5摘要。

### 3. 签名接口基础请求
请求对象可以继承BaseSignRequest。
