package net.yisasin.springcloud.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.yisasin.springcloud.common.model.CommonResult;
import net.yisasin.springcloud.feign.PaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

//    private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentClient paymentClient;

    @GetMapping("/create/{serial}")
    public CommonResult create(@PathVariable("serial") String serial) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/create/" + serial, CommonResult.class);
    }

    @GetMapping("/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getById/" + id, CommonResult.class);
    }

    @GetMapping("/discovery")
    public CommonResult discovery() {

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("SERVICE : {}", service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(serviceInstance -> {
            log.info("ServiceID:{}\tInstanceId:{}\tHost:{}\tPort:{}\tUri:{}\tScheme:{}\tMetadata:{}",
                    serviceInstance.getServiceId(),
                    serviceInstance.getInstanceId(),
                    serviceInstance.getHost(),
                    serviceInstance.getPort(),
                    serviceInstance.getUri(),
                    serviceInstance.getScheme(),
                    JSONUtil.toJsonStr(serviceInstance.getMetadata()));
        });

        return CommonResult.success(discoveryClient);
    }

    @GetMapping("/feign/{name}")
    public CommonResult feign(@PathVariable("name") String name) {

        return paymentClient.feign(name);

    }

    @GetMapping("/feign/easyError")
    public CommonResult easyError() {
        return paymentClient.easyError();
    }

}
