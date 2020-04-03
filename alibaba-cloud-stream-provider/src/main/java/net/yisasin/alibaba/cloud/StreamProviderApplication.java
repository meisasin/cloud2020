package net.yisasin.alibaba.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class StreamProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamProviderApplication.class);

        log.info("StreamProviderApplication service is run ....");
    }

    public ApplicationRunner runner() {
        return args -> {

        };
    }

}
