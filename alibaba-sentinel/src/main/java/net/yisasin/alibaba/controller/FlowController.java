package net.yisasin.alibaba.controller;

import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/flow")
public class FlowController {

    @GetMapping("/success")
    public CommonResult success() {
        return CommonResult.success(
                "Send -> " + new Random().nextInt()
                + " of " + UUID.randomUUID().toString().replace("-", "").toUpperCase()
        );
    }
}
