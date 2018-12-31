package com.bayi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.bayi.mapper")
public class SpringbootshiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootshiroApplication.class, args);
    }

}

