package com.zx.hivedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class HiveDemoApplicationTests {

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    JdbcTemplate hive;
    @Autowired
    @Qualifier("impalaJdbcTemplate")
    JdbcTemplate impala;

    @Test
    void contextLoads() {
//        hive.queryForList("select count(1) as num from 000lishui2").forEach(System.out::println);
        System.out.println(impala.queryForObject("select count(1) as num from 000lishui2", Long.class));
    }

}
