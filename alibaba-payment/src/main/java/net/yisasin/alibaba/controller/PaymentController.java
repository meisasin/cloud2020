package net.yisasin.alibaba.controller;


import lombok.extern.slf4j.Slf4j;
import net.yisasin.springcloud.common.model.CommonResult;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    public String port;

    @Autowired
    public RedissonClient redissonClient;

    @GetMapping("/queryPort")
    public CommonResult queryPort() {
        return CommonResult.success(port);
    }


    @GetMapping("/store/{num}")
    public CommonResult store(@PathVariable("num") Integer num) {

        RBucket<Integer> store = redissonClient.getBucket("store");

        store.set(num);

        return CommonResult.success(store.get());
    }

    @GetMapping("/getStore")
    public CommonResult getStore() {
        return CommonResult.success(redissonClient.getBucket("store").get());
    }

    @GetMapping("/consumer")
    public CommonResult consumer() {

        RLock consumer = redissonClient.getLock("CONSUMER");

        // 加锁
        try {
            consumer.lock();
            RBucket<Integer> bucket = redissonClient.getBucket("store");
            Integer number = bucket.get();

            if (number == null) {
                log.error("未找到库存数据");
                return CommonResult.error("Not key.");
            }

            if (number <= 0) {
                log.warn("库存数量为 0");
                return CommonResult.error("已无库存");
            }

            number--;
            log.info("剩余库存数量 -> {}", number);

            bucket.set(number);
            return CommonResult.success("执行完毕，剩余库存" + number);
        } finally {
            // 释放锁
            consumer.unlock();
        }
    }

}
