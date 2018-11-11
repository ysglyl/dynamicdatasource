package com.bzdnet.dynamicdatasource.datasource;

import com.bzdnet.dynamicdatasource.domain.Tenement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * 2018-11-07 21:29
 **/
public class TenementContextHolder {

    private static final Logger log = LoggerFactory.getLogger(TenementContextHolder.class);

    private static final ThreadLocal<Tenement> contextHolder = new ThreadLocal<Tenement>();

    public static void setTenement(Tenement tenement) {
        contextHolder.set(tenement);
    }

    public static Tenement getTenement() {
        Tenement tenement = contextHolder.get();
        return tenement;
    }

}
