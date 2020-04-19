package com.ecnu.wlw;
import com.ecnu.wlw.config.JdbcConfig;
import com.ecnu.wlw.config.SpringConfiguration;
import com.ecnu.wlw.dao.IUserDao;
import com.ecnu.wlw.pojo.User;
import com.ecnu.wlw.service.AccountService;
import com.ecnu.wlw.service.IUserService;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class DIApplicationTests {
    @Resource(name = "userService")
    private IUserService userService;
//测试runner是否多例
    @Test
    void testQueryRunner(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner r1 = ac.getBean(QueryRunner.class);//从IOC容器中byType取bean
        QueryRunner r2 = (QueryRunner) ac.getBean("runner");//从IOC容器中byName取bean
        System.out.println(r1==r2);
    }
    @Test
    void test(){
//        如果将配置类作为spring的配置文件则需要给该配置类主名@ComponentScan注解才能去扫描整个项目的bean实现与xml相同功能
        ApplicationContext ac = new AnnotationConfigApplicationContext(BootRedisApplication.class);
        IUserService userService2 = (IUserService) ac.getBean("userService");
        List<User> users = userService2.findAllUsers();

//        或者直接用springBoot代替spring就不用写获取容器,再用容器获取bean的代码
//        List<User> users = userService.findAllUsers();
        for(User u : users){
            System.out.println(u);
        }
    }
//    测试使用XML定义的bean
    @Test
    void diXML(){
//        通过XML文件获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        如果用配置文件类来配置bean的话该配置类就是一个配置文件，通过spring容器
        ApplicationContext ac2 = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        根据id获取bean对象
        AccountService as = (AccountService) ac.getBean("accountService");
        as.saveAccount();

        AccountService as2 = (AccountService) ac.getBean("accountService2");
        as2.saveAccount();

        AccountService as3 = (AccountService) ac.getBean("accountService3");
        as3.saveAccount();

    }
//    测试使用注解定义的bean
    @Test
    void diAnnotation(){
//        spring核心容器默认会到XML配置文件里去找定义的bean，如果bean是使用注解定义的，
//        则在xml配置文件里找不到这个bean的id就会报错
//        此时需要在xml配置文件里配置---context名称空间和约束
//        告知spring在创建容器时要扫描的包及其子包，并扫描所有类或接口上的注解放入spring容器管理
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean1.xml");
//        根据id获取bean对象
        IUserService us = (IUserService) ac.getBean("userService");
        System.out.println(us);

        IUserDao ud = (IUserDao) ac.getBean("userDao");
        ud.saveUser(new User());
    }

//    测试使用注解定义的bean的依赖注入
    @Test
    void beanInjection(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean1.xml");

        IUserService us = (IUserService) ac.getBean("userService");
        us.saveUser(new User()); //这个时候我只注册了UserService这个bean，但是里面又依赖UserDao没有注入，而saveUser方法又调用了Dao这个依赖就会报空指针
    }

}
