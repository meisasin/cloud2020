package net.yisasin.springcloud.feign;

import net.yisasin.springcloud.common.constants.ServiceConstant;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = ServiceConstant.CLOUD_PAYMENT_SERVICE, path = "/payment")
public interface PaymentClient {

    @GetMapping("/feign/{name}")
    CommonResult feign(@PathVariable("name") String name);


    @GetMapping("/feign/easyError")
    CommonResult easyError();
}
