package com.bzdnet.dynamicdatasource.datasource;

import com.bzdnet.dynamicdatasource.domain.Tenement;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 2018-11-07 21:17
 **/
public class DynamicDatasource extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(DynamicDatasource.class);

    private Map<Object, Object> dynamicTargetDataSources;
    private Object dynamicTargetDataSource;


    @Override
    protected Object determineCurrentLookupKey() {
        Tenement tenement = TenementContextHolder.getTenement();
        return "db_" + tenement.getId();
    }

    /**
     * 添加数据源
     *
     * @param list
     * @return
     */
    public boolean addDataSources(List<Tenement> list) {
        try {
            Map<Object, Object> existDynamicTargetDataSources = this.dynamicTargetDataSources;
            for (Tenement tenement : list) {
                if (existDynamicTargetDataSources.containsKey("db_" + tenement.getId())) {
                    continue;
                }
                HikariDataSource dataSource = new HikariDataSource();
                dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                dataSource.setJdbcUrl("jdbc:mysql://" +
                        tenement.getIp() + ":" + tenement.getPort() + "/" + tenement.getDb() +
                        "?zeroDateTimeBehavior=convertToNull" +
                        "&useUnicode=TRUE" +
                        "&characterEncoding=utf8" +
                        "&autoReconnect=true");
                dataSource.setUsername(tenement.getUsername());
                dataSource.setPassword(tenement.getPassword());
                existDynamicTargetDataSources.put("db_" + tenement.getId(), dataSource);
            }
            setTargetDataSources(existDynamicTargetDataSources);
            super.afterPropertiesSet();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除数据源
     *
     * @param tenementId
     * @return
     */
    public boolean deleteDataSource(long tenementId) {
        try {
            Map<Object, Object> existDynamicTargetDataSources = this.dynamicTargetDataSources;
            String key = "db_" + tenementId;
            if (existDynamicTargetDataSources.containsKey(key)) {
                Iterator<Object> iterator = existDynamicTargetDataSources.keySet().iterator();
                while (iterator.hasNext()) {
                    Object removeKey = iterator.next();
                    if (key.equals(removeKey)) {
                        iterator.remove();
                        break;
                    }
                }
                setTargetDataSources(existDynamicTargetDataSources);
                super.afterPropertiesSet();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.dynamicTargetDataSources = targetDataSources;
    }

    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        this.dynamicTargetDataSource = defaultTargetDataSource;
    }


    public Map<Object, Object> getDynamicTargetDataSources() {
        return dynamicTargetDataSources;
    }

    public void setDynamicTargetDataSources(Map<Object, Object> dynamicTargetDataSources) {
        this.dynamicTargetDataSources = dynamicTargetDataSources;
    }

    public Object getDynamicTargetDataSource() {
        return dynamicTargetDataSource;
    }

    public void setDynamicTargetDataSource(Object dynamicTargetDataSource) {
        this.dynamicTargetDataSource = dynamicTargetDataSource;
    }
}
