package com.example.postbar;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.postbar.mapper")
public class PostbarApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostbarApplication.class, args);
    }

}
