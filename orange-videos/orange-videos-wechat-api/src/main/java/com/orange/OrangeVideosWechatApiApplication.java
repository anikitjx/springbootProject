package com.orange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.orange.mapper")
@ComponentScan(basePackages = ("com.orange"))
public class OrangeVideosWechatApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeVideosWechatApiApplication.class, args);
    }
}
