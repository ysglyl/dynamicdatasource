package com.bzdnet.dynamicdatasource.controller;

import com.bzdnet.dynamicdatasource.datasource.DynamicDatasource;
import com.bzdnet.dynamicdatasource.domain.Tenement;
import com.bzdnet.dynamicdatasource.service.TenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * 2018-11-11 18:33
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DynamicDatasource dataSource;

    @Autowired
    private TenementService tenementService;

    @RequestMapping("/ds/init")
    public String init() {
        List<Tenement> list = tenementService.list();
        dataSource.addDataSources(list);
        return "ok";
    }

    @RequestMapping("/ds/del")
    public String del(long tenementId) {
        dataSource.deleteDataSource(tenementId);
        return "ok";
    }

}
