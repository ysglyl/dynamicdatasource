package com.bzdnet.dynamicdatasource.datasource;

import com.bzdnet.dynamicdatasource.domain.Tenement;

/**
 * @author Administrator
 * 2018-11-07 21:29
 **/
public class TenementContextHolder {

    private static final ThreadLocal<Tenement> contextHolder = new ThreadLocal<Tenement>();

    public static void setTenement(Tenement tenement) {
        contextHolder.set(tenement);
    }

    public static Tenement getTenement() {
        return contextHolder.get();
    }

    public static void clearTenement() {
        contextHolder.remove();
    }
}
