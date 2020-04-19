# SpringIOC
该项目为SpringIOC的XML配置和注解配置

SpringBoot使用@ImportResource注解倒入xml配置，但是官方不推荐这种做法，建议使用@Configuration注解定义配置文件
```java
@ImportResource("classpath:dbAnnotation.xml")
public class BootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootRedisApplication.class, args);
    }

}
```

其中
- Account用来演示xml配置bean的过程
- UserServiceImpl演示注解配置bean的过程
- bean.xml演示bean的三种注入方式
- bean1.xml演示使用注解时如何在配置文件里配置spring查找注解的目录
- dbxml.xml配置文件、UserServiceImpl和UserDaoImpl演示基于MySQL的XMLspringIOC配置
- dbAnnotation.xml、UserServiceImpl2和UserDaoImpl2配置文件演示基于MySQL的注解IOC配置(主程序入口配置xml路径)
- config
使用c3p0封装的dbutil连接MySQL时，数据源使用的driver一开始是`"com.mysql.cj.jdbc.Driver"` ，一直连不上数据库

会报：Caused by: java.sql.SQLException: Connections could not be acquired from the underlying database!

后来把pom里的`mysql-connector-java`依赖提高到8。0。16，并将数据源改成`com.mysql.cj.jdbc.Driver`即可连接成功
```xml
  <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.cj.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/eesy"></property>
        <property name="user" value="root"></property>
        <property name="password" value="123456"></property>
   </bean>
```