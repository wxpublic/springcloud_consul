package com.itmayiedu.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @version 1.0
 * @Description:
 * @author: ChenRuiQing.
 * Create Time:  2019-01-21 下午 8:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AppOrder {
    public static void main(String[] args) {
        SpringApplication.run(AppOrder.class);
    }
    // 默认rest方式开启 负载均衡功能 如果以app-itmayiedu-member名称进行调用服务接口的时候 必须
    @Bean
//    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
