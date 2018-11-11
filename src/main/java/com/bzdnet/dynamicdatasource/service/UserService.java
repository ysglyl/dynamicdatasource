package com.bzdnet.dynamicdatasource.service;

import com.bzdnet.dynamicdatasource.dao.TenementDao;
import com.bzdnet.dynamicdatasource.dao.UserDao;
import com.bzdnet.dynamicdatasource.domain.Tenement;
import com.bzdnet.dynamicdatasource.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * 2018-11-07 21:04
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> list() {
        return userDao.list();
    }
}
