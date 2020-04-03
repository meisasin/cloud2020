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

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController {

    public static final String STORE_KEY = "STORE_KEY";
    public static final String STORE_LOCK = "STORE_LOCK";
    @Autowired
    private RedissonClient redissonClient;

    @Value("${server.port}")
    public String port;

    @GetMapping("/init/{num}")
    public CommonResult init(@PathVariable("num") Integer num) {
        RBucket<Integer> store = redissonClient.getBucket(STORE_KEY);

        store.set(num);

        return CommonResult.success(store.get());
    }

    @GetMapping("/consumer")
    public CommonResult consumer() throws InterruptedException {

        if (true) {
            throw new RuntimeException("Ha ha ha ..." + UUID.randomUUID().toString());
        }
        RLock rLock = redissonClient.getLock(STORE_LOCK);

        // 加锁
        boolean isLock = rLock.tryLock(30, TimeUnit.SECONDS);
        if (!isLock) {
            return CommonResult.error("Don't got lock.");
        }
        try {

            RBucket<Integer> bucket = redissonClient.getBucket(STORE_KEY);
            Integer number = bucket.get();

            if (number == null) {
                log.error("未找到库存数据");
                return CommonResult.error("No data.");
            }

            if (number <= 0) {
                log.warn("库存数量为 0");
                return CommonResult.error("已无库存");
            }

            number--;
            log.info("剩余库存数量 -> {}", number);

            bucket.set(number);
            return CommonResult.success("In " + port + " 消费库存，执行完毕，剩余库存 -> " + number);
        } finally {
            // 释放锁
            rLock.unlock();
        }
    }


}
