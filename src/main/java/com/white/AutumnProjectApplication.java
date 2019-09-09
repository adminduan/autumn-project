package com.white;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.white.mapper")
public class AutumnProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutumnProjectApplication.class, args);
    }

}
