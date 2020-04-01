package net.yisasin.alibaba.controller;

import net.yisasin.alibaba.consumer.PaymentConsumer;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentConsumer paymentConsumer;

    @Value("${cservice.nodeMap.alibaba-payment-service}")
    private String paymentServiceNode;

    @GetMapping("/query")
    public CommonResult query() {
        return restTemplate.getForObject("http://"+ paymentServiceNode + "/payment/queryPort", CommonResult.class);
    }

    @GetMapping("/decrStore")
    public CommonResult decrStore() {
        return restTemplate.getForObject("http://" + paymentServiceNode + "/store/consumer", CommonResult.class);
    }

    @GetMapping("/feignDecrStore")
    public CommonResult feignDecrStore() {

        CommonResult consumer = paymentConsumer.consumer();
        return consumer;
    }

}
