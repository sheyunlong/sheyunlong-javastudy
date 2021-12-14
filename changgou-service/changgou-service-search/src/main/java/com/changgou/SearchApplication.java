package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/28 11:07
 * @description 标题
 * @package com.changgou
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
//basePackages = "com.changgou.goods.feign"  扫描该包下的接口
@EnableFeignClients(basePackages = "com.changgou.goods.feign")
public class SearchApplication {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SearchApplication.class,args);
    }
}
