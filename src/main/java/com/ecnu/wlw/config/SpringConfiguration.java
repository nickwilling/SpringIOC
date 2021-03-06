package com.ecnu.wlw.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

//该类是一个配置类，它的作用和bean.xml是一样的
/*
* @Configuration
*   作用：指定当前类是一个配置类
*   细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写，当参数穿进去spring就默认认为这个类是配置类
*       只有当spring认为这个类是配置类时才会去类里扫描它的注解
* @ComponentScan <beans></beans>
*   作用：用于通过注解指定spring在创建容器时要扫描的包
*   属性：
*       value：它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包
*              我们使用此注解就等同于在xml中配置了 <context:component-scan base-package="com.ecnu.wlw"></context:component-scan>
*@Bean
*   作用：用于把当前方法的返回值作为bean对象存到IOC容器中（容器是键值对，要有key【id】和value【对象】）
*   属性：
*       name：用于指定bean的id，当不写时默认值是当前方法的名称
*   细节：当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象
*        查找的方式于Autowired一致，先byTye再byName
* @Import(实现一个大的综合配置类包含其他小的配置类，这样只要通过这个配置类就可以获取整个容器的bean)
*   作用：用于导入其他的配置类,这样其他配置类就不用加@ComponentScan和@Configuration了。
*   属性：value：用于指定其他配置类的字节码
*               当使用Import注解后，有Import注解的类就是父配置类，而导入的都是子配置类
*/

//@Configuration
//@ComponentScan("com.ecnu.wlw")
//@Import(JdbcConfig.class)
public class SpringConfiguration {


}
