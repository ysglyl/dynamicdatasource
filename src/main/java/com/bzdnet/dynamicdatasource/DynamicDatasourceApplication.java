package com.bzdnet.dynamicdatasource;

import com.bzdnet.dynamicdatasource.datasource.DynamicDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author Administrator
 */
@SpringBootApplication
public class DynamicDatasourceApplication {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }

    @Bean
    public DynamicDatasource getDynamicDatasource(){
        return new DynamicDatasource(dataSource);
    }
}
