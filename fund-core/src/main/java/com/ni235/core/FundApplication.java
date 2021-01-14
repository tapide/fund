package com.ni235.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ni235.core.fund.dao")
@SpringBootApplication
public class FundApplication {
    public static void main(String[] args) {
         SpringApplication.run(FundApplication.class,args);
    }

}
