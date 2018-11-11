package com.bzdnet.dynamicdatasource.interceptor;

import com.bzdnet.dynamicdatasource.datasource.TenementContextHolder;
import com.bzdnet.dynamicdatasource.domain.Tenement;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * 2018-11-11 18:13
 **/
public class ChangeDatasource extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenementId = request.getHeader("tid");
        Tenement tenement = new Tenement();
        tenement.setId(Long.valueOf(tenementId));
        TenementContextHolder.setTenement(tenement);
        return super.preHandle(request, response, handler);
    }

}
