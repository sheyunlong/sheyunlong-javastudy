package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/12/22 11:27
 * @description 标题
 * @package com.changgou
 */
@SpringBootApplication
@EnableEurekaClient//启动eureka-client
//组件扫描 mapper 注意要使用tk的包下的注解扫描
@MapperScan(basePackages = "com.changgou.goods.dao")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }
}
