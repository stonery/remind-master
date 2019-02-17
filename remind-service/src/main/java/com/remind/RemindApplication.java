package com.remind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@MapperScan({"com.remind.persistence.mapper"})
public class RemindApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemindApplication.class, args);
    }

}

