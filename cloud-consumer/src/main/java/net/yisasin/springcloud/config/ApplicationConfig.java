package net.yisasin.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    @LoadBalanced  // 使用@LoadBalanced注解赋予 RestTemplate 负载均衡能力
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
