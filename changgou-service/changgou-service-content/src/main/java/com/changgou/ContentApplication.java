package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/26 16:09
 * @description 标题
 * @package com.changgou
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.changgou.content.dao")
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class,args);
    }
}
