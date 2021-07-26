package com.ld.service;

import com.ld.dao.UserDao;
import com.ld.dao.UserDaoImpl;
import com.ld.dao.UserDaoMysqlImpl;
import com.ld.dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService{
    //private UserDao userDao= new UserDaoImpl();
    //private UserDao userDao= new UserDaoMysqlImpl();
    //private UserDao userDao= new UserDaoOracleImpl();
    private UserDao userDao;

    //利用set进行动态实现的注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
