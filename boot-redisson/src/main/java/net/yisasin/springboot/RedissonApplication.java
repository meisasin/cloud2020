package net.yisasin.springboot;

import lombok.extern.slf4j.Slf4j;
import net.yisasin.springcloud.common.model.CommonResult;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
@RestController
public class RedissonApplication {

    private static final String STORE_LOCK = "STORE_LOCK";
    private static final String STORE_KEY = "STORE_KEY";
    @Autowired
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        SpringApplication.run(RedissonApplication.class);

        log.info("RedissonApplication Service running ......");
    }

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/set/{key}/{value}")
    public CommonResult set(@PathVariable("key") String key, @PathVariable("value") Integer value) {
        RBucket<Integer> bucket = redissonClient.getBucket(key);

        Integer andSet = bucket.getAndSet(value);

        return CommonResult.success("Old value is > " + andSet);

    }

    /**
     * 取值
     * @param key
     * @return
     */
    @GetMapping("/get/{key}")
    public CommonResult get(@PathVariable("key") String key) {
        RBucket<Integer> bucket = redissonClient.getBucket(key);

        Integer value = bucket.get();

        return CommonResult.success("Curr value is > " + value);

    }

    /**
     * 并发消费
     * @return
     */
    @GetMapping("/consumer")
    public CommonResult consumer() throws InterruptedException {

        RLock rLock = redissonClient.getLock(STORE_LOCK);
        // 加锁
        boolean isLock = rLock.tryLock(1, TimeUnit.SECONDS);
        if (!isLock) {
            return CommonResult.error("未获取到锁");
        }

        try {

            log.info("获取到锁 >>> 睡觉 😪");
            TimeUnit.SECONDS.sleep(2);
            log.info("获取到锁 >>> 睡醒 😪");
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
            return CommonResult.success("消费库存，执行完毕，剩余库存 -> " + number);
        } finally {
            // 释放锁
            rLock.unlock();
        }
    }

}
