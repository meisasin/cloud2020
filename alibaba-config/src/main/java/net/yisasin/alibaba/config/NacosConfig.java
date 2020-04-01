package net.yisasin.alibaba.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Component
@RefreshScope
public class NacosConfig implements Serializable {

    @Value("${name}")
    private String name;

    @Value("${has.life}")
    private boolean hasLife;

    @Value("${address}")
    private String address;

    @Value("${number}")
    private Integer number;

    @Value("${nodes}")
    private List<String> nodes;

}
