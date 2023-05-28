package com.blue.cat;

import io.github.asleepyfish.annotation.EnableChatGPT;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableChatGPT
@MapperScan("com.blue.cat.mapper.**")
public class BlueCatApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueCatApplication.class, args);
    }

}
