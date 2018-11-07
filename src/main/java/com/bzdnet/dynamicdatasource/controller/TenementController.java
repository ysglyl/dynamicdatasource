package com.bzdnet.dynamicdatasource.controller;

import com.bzdnet.dynamicdatasource.domain.Tenement;
import com.bzdnet.dynamicdatasource.service.TenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * 2018-11-07 21:03
 **/
@RestController
@RequestMapping("/tenement")
public class TenementController {


    @Autowired
    private TenementService tenementService;

    @RequestMapping("/list")
    List<Tenement> list() {
        return tenementService.list();
    }

}
