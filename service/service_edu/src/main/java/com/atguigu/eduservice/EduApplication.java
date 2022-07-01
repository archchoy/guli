package com.atguigu.eduservice;

import com.atguigu.client.VodClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching // 开启缓存支持
@EnableFeignClients(clients = VodClient.class)
@MapperScan(basePackages = "com.atguigu.eduservice.mapper")
@ComponentScan(basePackages = "com.atguigu")       // 可以扫描其他模块的com.atguigu包下的组件
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
