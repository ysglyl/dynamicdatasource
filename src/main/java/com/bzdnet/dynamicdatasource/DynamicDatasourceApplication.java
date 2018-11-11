package com.bzdnet.dynamicdatasource;

import com.bzdnet.dynamicdatasource.datasource.DynamicDatasource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@SpringBootApplication
public class DynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }

    @Bean(name = "db_0")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dbAdmin() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource")
    public DynamicDatasource getDynamicDatasource() {
        DynamicDatasource dynamicDataSource = new DynamicDatasource();
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>(2);
        dataSourceMap.put("db_0", dbAdmin());
        dynamicDataSource.setDefaultTargetDataSource(dbAdmin());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }


    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDynamicDatasource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.bzdnet.dynamicdatasource.domain");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:com/bzdnet/dynamicdatasource/mapper/**/*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
}
