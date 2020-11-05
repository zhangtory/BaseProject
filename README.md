# BaseProject
基于springboot的基础框架，可以根据需求快速组装组件，构建基础框架。

## 使用方法

  选择对应starter引入依赖并根据README增加配置或查看使用说明。

### 创建项目
  新建springboot项目或以base-project为基础修改名称。
  
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
  
  
  
  
  
  b.以base-project为基础，修改pom中name标签的值，修改BaseProjectApplication