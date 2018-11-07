package com.bzdnet.dynamicdatasource.dao;

import com.bzdnet.dynamicdatasource.domain.Tenement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 **/
@Mapper
@Repository
public interface TenementDao {

    /**
     * 获取租户列表
     * @return
     */
    List<Tenement> list();

}
