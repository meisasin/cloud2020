package net.yisasin.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/testA")
    public CommonResult testA() {
        return CommonResult.success("Message from testA. ");
    }

    @GetMapping("/testB")
    public CommonResult testB() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);
        return CommonResult.success("Message from testB. ");
    }

    @GetMapping("/testD")
    public CommonResult testD() throws InterruptedException {
        log.info("Recivice a request ...");
        TimeUnit.SECONDS.sleep(1L);
        return CommonResult.success("Message from testD.");
    }

    @GetMapping("/testE")
    public CommonResult testE() {

        int i = 10 / 0;
        return CommonResult.success("Message from testE.");
    }

    @GetMapping("/hot-key")
    @SentinelResource(value = "test-hotkey", blockHandler = "deal_hotkey")
    public CommonResult hotKey(@RequestParam(value = "p1", required = false) String p1,
                               @RequestParam(value = "p2", required = false) String p2) {

        return CommonResult.success("Message from hotKye.");
    }

    public CommonResult deal_hotkey(String p1, String p2, BlockException bolckE) {
        log.error("Hotkey is Forbidden.", bolckE);
        return CommonResult.error("Hotkey is Forbidden From " + p1 + " with " + p2);
    }
}
