package net.yisasin.springcloud.registerbak;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class RegisterBakApplication {


    public static void main(String[] args) {
        SpringApplication.run(RegisterBakApplication.class, args);

        log.info("RegisterBak-service running ......");
    }
}
