package com.bzdnet.dynamicdatasource.controller;

import com.bzdnet.dynamicdatasource.domain.Tenement;
import com.bzdnet.dynamicdatasource.domain.User;
import com.bzdnet.dynamicdatasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * 2018-11-07 21:03
 **/
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    List<User> list() {
        return userService.list();
    }

}
