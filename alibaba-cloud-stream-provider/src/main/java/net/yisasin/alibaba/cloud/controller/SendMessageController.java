package net.yisasin.alibaba.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import net.yisasin.alibaba.cloud.service.IMessageProvider;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/send")
public class SendMessageController {

    @Autowired
    private IMessageProvider iMessageProvider;

    @GetMapping("/{message}")
    public CommonResult doSimple(@PathVariable("message") String message) {
        log.info("简单发送MQ信息接口接收到请求，Param -> {}", message);

        return CommonResult.success(iMessageProvider.send(message));
    }
}
