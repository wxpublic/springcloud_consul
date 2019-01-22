# springcloud_consul
使用consul作为注册中心代替SpringCloud中的Eureka

本项目采用spring.cloud.consul.discovery.serviceName 配置属性来获取远端生产者服务。并利用loadBanlancer支持轮询多生产者服务。
# 获取服务生产者实例：
@Autowired
DiscoveryClient discoveryClient;
@Autowired
private RestTemplate restTemplate;
@Autowired
private LoadBalancerClient loadBalancer;
    
ServiceInstance serviceInstance = loadBalancer.choose("service-member");

List<ServiceInstance> instances = discoveryClient.getInstances("service-member");
