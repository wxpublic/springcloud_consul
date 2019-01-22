package com.itmayiedu.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @version 1.0
 * @Description:
 * @author: ChenRuiQing.
 * Create Time:  2019-01-21 下午 9:29
 */
@RestController
public class OrderController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancer;

    // springcloud 中使用那些技术实现调用服务接口 feign 或者rest
    @RequestMapping("/getOrder")
    public String getOrder(){
//        String memberUrl = discoveryClient.getInstances("service-member").get(0).getUri().toString()+"/getMember";
        String memberUrl = testOrder()+"/getMember";
        System.out.println("当前URL："+memberUrl);
        String resultMember = restTemplate.getForObject(memberUrl, String.class);
        System.out.println("RPC getMember方法结果："+memberUrl);
        return "我是getOrder订单服务，我要调用的会员服务说：>>"+resultMember;
    }

    @RequestMapping("/testOrder") //如果要使用此方法，将启动类中的@loadBalancer注解需要去掉，因为这里使用了在本类中重复注入的对象，会发生重复
    public String testOrder(){
        ServiceInstance serviceInstance = loadBalancer.choose("service-member");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());
        String callServiceResult = restTemplate.getForObject(serviceInstance.getUri().toString() + "/getMember", String.class);
        System.out.println("testOrder方法："+callServiceResult);
        return serviceInstance.getUri().toString();
    }

    /**
     * 下面方法使用discoveryClient获取方法通过配置文件中的serviceName作为远程服务调取时，不可以在启动类中使用@LoadBalance注解
     * @return
     */
    @RequestMapping("/discoveryClientMember")
    public List<ServiceInstance> discoveryClientMember() {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-member");
        for (ServiceInstance serviceInstance : instances) {
            System.out.println("url:" + serviceInstance.getUri());
        }
        return instances;
    }
}