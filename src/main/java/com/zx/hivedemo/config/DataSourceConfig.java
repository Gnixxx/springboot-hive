package com.zx.hivedemo.config;


import org.apache.hive.jdbc.HiveDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.hive.jdbc-url}")
    private String hiveUrl;
    @Value("${spring.datasource.hive.username}")
    private String hiveUsername;
    @Value("${spring.datasource.hive.password}")
    private String hivePassword;


    @Bean("impalaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.impala")
    public DataSource impalaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("hiveDataSource")
    public DataSource hiveDataSource() {

        return new SimpleDriverDataSource(new HiveDriver(), hiveUrl,hiveUsername , hivePassword);

    }


    @Bean(name = "hiveJdbcTemplate")
    public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveDataSource") DataSource hiveDataSource) {

        return new JdbcTemplate(hiveDataSource);
    }


    @Bean(name = "impalaJdbcTemplate")
    public JdbcTemplate impalaJdbcTemplate(@Qualifier("impalaDataSource") DataSource impalaDataSource) {

        return new JdbcTemplate(impalaDataSource);
    }
}
