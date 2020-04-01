package net.yisasin.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import net.yisasin.alibaba.common.GlobalExceptionHandler;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/block")
public class BlockController {

    @GetMapping("/handler_1")
    public CommonResult handler_1() {
        return CommonResult.success("Handler-1 is OK.");
    }


    @GetMapping("/handler_2")
    @SentinelResource(value = "handler_2", blockHandlerClass = GlobalExceptionHandler.class, blockHandler = "handle_2")
    public CommonResult handler_2() {
        return CommonResult.success("Handler-2 is OK.");
    }


    @GetMapping("/handler_3")
    @SentinelResource(value = "handler_3", blockHandlerClass = GlobalExceptionHandler.class, blockHandler = "handle_3")
    public CommonResult handler_3() {
        return CommonResult.success("Handler-3 is OK.");
    }
}
