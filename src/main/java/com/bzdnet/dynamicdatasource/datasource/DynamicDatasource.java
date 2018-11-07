package com.bzdnet.dynamicdatasource.datasource;

import com.bzdnet.dynamicdatasource.domain.Tenement;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator
 * 2018-11-07 21:17
 **/
public class DynamicDatasource extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(DynamicDatasource.class);

    private Map<Object, Object> dynamicTargetDataSources;
    private Object dynamicTargetDataSource;

    public DynamicDatasource() {
    }

    public DynamicDatasource(DataSource dataSource) {
        this.setDefaultTargetDataSource(dataSource);
    }


    @Override
    protected Object determineCurrentLookupKey() {
        log.info("determineCurrentLookupKey");
        Tenement tenement = TenementContextHolder.getTenement();
        if (tenement == null) {
            return null;
        }
        return tenement == null ? "0" : String.valueOf(tenement.getId());
    }

    /**
     * 添加数据源
     *
     * @param key
     * @param ip
     * @param port
     * @param db
     * @param username
     * @param password
     * @return
     */
    public boolean createDataSource(String key, String ip, int port, String db, String username, String password) {
        try {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://" +
                    ip + ":" + port + "/" + db +
                    "?zeroDateTimeBehavior=convertToNull" +
                    "&useUnicode=TRUE" +
                    "&characterEncoding=utf8" +
                    "&autoReconnect=true");
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            Map<Object, Object> existDynamicTargetDataSources = this.dynamicTargetDataSources;
            existDynamicTargetDataSources.put(key, dataSource);
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
     * @param key
     * @return
     */
    public boolean deleteDataSource(String key) {
        try {
            Map<Object, Object> existDynamicTargetDataSources = this.dynamicTargetDataSources;
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
