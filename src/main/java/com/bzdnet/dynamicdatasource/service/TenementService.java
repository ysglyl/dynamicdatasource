package com.bzdnet.dynamicdatasource.service;

import com.bzdnet.dynamicdatasource.dao.TenementDao;
import com.bzdnet.dynamicdatasource.domain.Tenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * 2018-11-07 21:04
 **/
@Service
public class TenementService {

    @Autowired
    private TenementDao tenementDao;

    public List<Tenement> list() {
        return tenementDao.list();
    }
}
