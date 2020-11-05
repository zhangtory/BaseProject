# BaseProject

基于SpringBoot的基础框架，可以根据需求快速组装组件，构建基础框架。  
目前主要是个人使用，配置都基于个人约定，后期会慢慢维护。

## 使用方法

  选择对应starter引入依赖并根据README增加配置或查看使用说明。
  
  #### 0. base-project
  基础项目demo。
  
  #### 1. [core-spring-boot-starter](core-spring-boot/README.md)
  核心core-spring-boot-starter必须引入，包含了swagger集成、CORS跨域请求处理、统一返回对象、全局异常处理、logback日志配置、请求日志打印拦截器、通用异常类以及可能用到的utils。

  #### 2. [mybatis-plus-spring-boot-starter](mybatis-plus-spring-boot-starter/README.md)
  支持mybatis-plus，加入了分页拦截器和乐观锁拦截器，并提供代码生成器。
  
  #### 3. [redis-spring-boot-starter](redis-spring-boot-starter/README.md)
  引入Redis的支持，并提供RedisHelper封装常用操作。
  
  #### 4. [sign-spring-boot-starter](sign-spring-boot-starter/README.md)
  提供对接口的验签支持。
  
  #### 5. [jwt-spring-boot-starter](jwt-spring-boot-starter/README.md)
  提供json web token的支持，验证用户登录等功能。
  

## 以core-spring-boot-starter为例创建项目
  新建springboot项目或以base-project为基础修改名称。
  
  #### a. 新建springboot项目
  
  1. 通过Spring initialzr创建SpringBoot工程，根据需要勾选lambok依赖即可。
  2. 创建后引入core依赖。
  ```xml
  <dependency>
	<groupId>com.zhangtory</groupId>
	<artifactId>core-spring-boot-starter</artifactId>
	<version>1.0</version>
  </dependency>
  ````
  3. 创建对应环境的配置文件。若有必要可以增加maven对profiles的支持。
  application.yml:
  ```yml
  spring:
    profiles:
      active: @env@
  ```
  pom.xml
  ```xml
  <profiles>
        <profile>
            <!-- 开发 -->
            <id>dev</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <env>dev</env>
            </properties>
        </profile>
        <profile>
            <!-- 生产 -->
            <id>prod</id>
            <properties>
                <env>prod</env>
            </properties>
        </profile>
    </profiles>
  ```
  如只有dev和prod的情况，对应创建application-dev.yml和application-prod.yml。
  
  4. 启动项目，访问http://127.0.0.1:8080/swagger-ui/index.htm可以进入swagger，项目配置完成。

  
  #### b. 以base-project为基础修改
  
  1. 修改pom中的GAV坐标和name标签的值。
  
  2. 修改BaseProjectApplication为对应的XXXApplication
  
  3. 修改包名，将com.zhangtory.base修改为GAV坐标对应的com.domain.project。
  
  4. 启动项目，访问http://127.0.0.1:8080/swagger-ui/index.htm可以进入swagger，项目配置完成。
  
