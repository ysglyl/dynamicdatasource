package com.bzdnet.dynamicdatasource.dao;

import com.bzdnet.dynamicdatasource.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * 2018-11-11 20:42
 **/
@Mapper
@Repository
public interface UserDao {

    List<User> list();

}
