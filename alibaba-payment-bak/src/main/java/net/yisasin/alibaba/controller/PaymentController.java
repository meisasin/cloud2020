package net.yisasin.alibaba.controller;


import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    public String port;

    @GetMapping("/queryPort")
    public CommonResult queryPort() {
        return CommonResult.success(port);
    }
}
