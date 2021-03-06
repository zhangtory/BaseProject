## 使用方法
引入依赖：
```xml
<dependency>
    <groupId>com.zhangtory.base</groupId>
    <artifactId>core</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 功能说明
### 1. 跨域请求处理
CorsFilter

### 2. 统一异常处理
GlobalExceptionHandler

### 3. 日志拦截器
LogAop
打印请求信息、返回信息、处理耗时，包括Method、url、params。

### 4. 通用返回码
CommonResult
包含code和message，提供了常见错误。
自定义返回码需要继承CommonResult即可。

### 5. 统一返回对象
返回json格式字符串，例子：
```json
{
  "code": 0,
  "message": "SUCCESS",
  "data": {
    "name": "zhangsan",
    "age": 18
  }
}
```
使用统一返回包装ResponseBuilder可快速构建返回。

### 6. 加密工具 EncryptUtils
目前仅提供md5摘要。

### 7. IP相关工具 IpUtils
可获取用户真实IP。

### 8. 用户密码工具 PasswordUtils
对密码进行加密和验证密码是否正确。通过加随机盐，提高用户密码信息的安全性。

getEncryptedPassword：对明文密码加随机盐并计算md5摘要，拼接摘要和盐值，返回密文。

checkPassword：判断明文密码是否正确。根据密文提取盐值，再对明文密码加盐计算摘要，判断密码是否正确。
