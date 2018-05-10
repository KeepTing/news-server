package com.keepting.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by keepspy on 2018/4/8.
 */
@SpringBootApplication
@EnableTransactionManagement
//@ComponentScan(basePackages = {"com.keepting.news"})
public class Server extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Server.class,args);
    }
}
