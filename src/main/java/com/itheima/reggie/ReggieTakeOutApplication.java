package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@ServletComponentScan
@MapperScan("com.itheima.reggie.mapper")
@EnableCaching
public class ReggieTakeOutApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ReggieTakeOutApplication.class, args);
    }

}
