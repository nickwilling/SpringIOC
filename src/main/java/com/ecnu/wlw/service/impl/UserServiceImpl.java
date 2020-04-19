package com.ecnu.wlw.service.impl;

import com.ecnu.wlw.dao.impl.UserDaoImpl;
import com.ecnu.wlw.pojo.User;
import com.ecnu.wlw.service.IUserService;
import com.ecnu.wlw.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/*
* 曾经XML的配置
* <bean id="userService" class="com.ecnu.wwl.service.impl.UserServiceImpl"
*       scope="" init-method="" destroy-method="" >
*   <property name="" value=""></property>
*  </bean>
*
* 注解的分类：
*   1、用于创建对象的注解
*       作用与XML配置文件中方编写一个<bean>标 中
*       @Component：
*           作用：用于把当前类对象存入spring容器中
*           属性：
*               value：用于指定bean的id。不写时默认值是首字母小写的类名
####################以下三个注解的作用和属性与@Component是一模一样的，只是为了使我们三层的对象更加清晰################
*       @Controller：一般用在表现层
*       @Service：一般用在业务层
*       @Repository：一般用在持久层
*
*   2、用于注入数据的注解
*       作用就和在XML中的<bean>标签中写一个<property>标签的作用是一样的，用来设值的
*       @AutoWired（先byType匹配，再byName匹配）
*           作用：自动按照类型注入。只要容器中有唯一的一个bean对象和要注入的变量类型匹配就可以成功注入
*                - 如果只有一个匹配就可以自动注入成功
                 - 如果IOC容器有多个类型匹配，则再按照名字去匹配，如果匹配成功就注入成功
                 - 如果没有匹配的就报错
*           出现的位置：可以是变量也可以是方法
*           细节：在使用注解注入时set方法不是必须的。
*       @Resouce(byName)
*           作用：直接按照bean的id注入
*           属性：
*               name：用于指定bean的id
* ###########以上的注入都只能注入bean类型的数据，而基本类型和String类型无法使用上述注解实现，集合类型的注入只能通过XML实现##########
*       @Value
*           作用：用于注入基本类型和String类型的数据
*           属性：
*               value：用于指定数据的值。他也可以使用spring中SpEL(Spring的el表达式--通过${key}取出配置文件的value)
*                   spEL写法：${表达式}
*                       @Value("${spring.redis.host}") //常见
                        private String host;
*   3、用于改变作用范围的注解
*       作用和在<bean>标签中使用scope属性实现的功能是一样的
*       @Scope
*           作用：用于指定bean的作用范围
*           属性：value：指定范围的取值。常用取值：singleton(默认) prototype
*   4、和生命周期相关的注解（了解）
*       作用就和在<bean>标签中使用init-method和destroy-method的作用是一样的
*       @PreDestroy
*           作用：用于指定销毁方法
*       @PostConstruct
*           作用：用于指定初始化方法
* */
//@Component(value = "userService")只有一个属性就可以不写value=
//这个时候我只注册了userService这个bean，但是里面又依赖UserDao没有注入，而saveUser方法又调用了Dao这个依赖就会报空指针
// @Component("userService")
public class UserServiceImpl implements IUserService {
//    变量注入后引用就不会报空指针异常了
    // @Autowired //自动按照类型装配， springIOC容器是一个map，里面是bean的id和数据类型，如果里面存的只有一个数据类型符合就会自动注入
//    @Resource(name = "userDao")
    private IUserDao userDao; //当用xml注入时要在后面加set方法
//    事物控制应该在业务层
//    private TransactionManager txManager;

    /*三个方法的输出顺序
    * 对象创建了
      执行初始化方法
      执行销毁方法 */

    //构造函数
    public UserServiceImpl(){
        System.out.println("对象创建了");
    }

    @PostConstruct //指定该方法在执行构造函数之后调用
    public void init(){
        System.out.println("执行初始化方法");
    }
    @PreDestroy //指定该方法在实例销毁前调用（spring只负责单例对象(Singleton)的销毁不负责多例对象(Prototype)的销毁）
    public void destroy(){
        System.out.println("执行销毁方法");
    }

//    如果bean注册了以后调用这个方法，但是又没有注入userDao这个bean的话就会报空指针异常
//    public void saveUser(){
//        userDao.saveUser();
//    }

    @Override
    public List<User> findAllUsers(){
        return userDao.findAllUsers();
    }

    @Override
    public User findUserById(int id) {
        try{
//            1、开启事务
//            txManager.beginTransaction();
//            2、执行操作
            User user = userDao.findUserById(id);
//            3、提交事务
//            4、返回结果
            return user;
        }catch (Exception e){
//            出现错误回滚事务
        }finally {
//            释放资源（连接）
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        User u = new User(2,"wwl",18);
        userDao.saveUser(u);
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

//    public void setTxManager(TransactionManager txManager) {
//        this.txManager = txManager;
//    }
}
