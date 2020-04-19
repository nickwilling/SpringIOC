package com.ecnu.wlw;

import com.ecnu.wlw.config.JdbcConfig;
import com.ecnu.wlw.config.SpringConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"com.ecnu.wlw"}) //指定要扫描的bean的包,springBoot可以不加
@Import({SpringConfiguration.class,JdbcConfig.class})  //导入配置类 一定要加的
@PropertySource("classpath:jdbcConfig") //spring一定要配置，并且一定要是properties配置文件里配置的才可以springBoot不用加，用yml配
//ImportResource从配置文件中注入bean对象，Springboot官方不推荐，推荐如下方式注入对象
//springboot项目使用@Configuration注解的方式注入bean
//@ImportResource("classpath:dbAnnotation.xml")
public class BootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootRedisApplication.class, args);
    }

}
