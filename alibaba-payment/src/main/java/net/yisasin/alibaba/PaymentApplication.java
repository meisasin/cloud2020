package net.yisasin.alibaba;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class);

        log.info("Payment-service running ......");
    }

    @RestController
    @RequestMapping("/echo")
    class EchoController {

        @GetMapping("/{message}")
        public String echo(@PathVariable("message") String message) {
            log.info("EchoController.echo 接收到请求，Param: {}", message);
            return "Hello nacos Discovery " + message;
        }

    }



}
