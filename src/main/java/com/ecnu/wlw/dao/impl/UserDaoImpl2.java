package com.ecnu.wlw.dao.impl;

import com.ecnu.wlw.dao.IUserDao;
import com.ecnu.wlw.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl2 implements IUserDao {
    @Resource(name = "runner")
    private QueryRunner runner1;
    public List<User> findAllUsers() {
        try {
            System.out.println(runner1);

            return runner1.query("select * from userInfo",new BeanListHandler<User>(User.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

}
