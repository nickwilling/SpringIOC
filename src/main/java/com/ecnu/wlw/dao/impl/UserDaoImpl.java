package com.ecnu.wlw.dao.impl;

import com.ecnu.wlw.dao.IUserDao;
import com.ecnu.wlw.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

//@Repository("userDao")
public class UserDaoImpl implements IUserDao {
//    dbutil的类，封装了jdbc
    private QueryRunner runner;
    @Override
    public List<User> findAllUsers() {
        try {
            System.out.println(runner);

            return runner.query("select * from userInfo",new BeanListHandler<User>(User.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public User findUserById(int id) {
        try {
            return runner.query("select * from userInfo where id = ?",new BeanHandler<User>(User.class),id);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            runner.update("insert into userInfo values(?,?,?)",user.getId(),user.getName(),user.getAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }
}
