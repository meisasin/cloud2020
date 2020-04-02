package net.yisasin.alibaba.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SteamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteamConsumerApplication.class);

        log.info("SteamConsumerApplication service is run ....");
    }
}
