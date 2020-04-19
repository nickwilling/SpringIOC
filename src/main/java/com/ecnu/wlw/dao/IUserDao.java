package com.ecnu.wlw.dao;

import com.ecnu.wlw.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    public List<User> findAllUsers();
    public User findUserById(int id);
    public void saveUser(User user);

}
