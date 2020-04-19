package com.ecnu.wlw.service.impl;
import com.ecnu.wlw.pojo.User;
import com.ecnu.wlw.service.IUserService;
import com.ecnu.wlw.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl2 implements IUserService {
//    当用注解注入的时候set方法就不再是必须的了
    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    public List<User> findAllUsers(){
        return userDao.findAllUsers();
    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public void saveUser(User user) {
        User u = new User(2,"wwl",18);
        userDao.saveUser(u);
    }

}
