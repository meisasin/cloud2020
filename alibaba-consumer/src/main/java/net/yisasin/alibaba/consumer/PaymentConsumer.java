package net.yisasin.alibaba.consumer;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "${cservice.nodeMap.alibaba-payment-service}", path = "/store", fallback = PaymentConsumerFallback.class)
public interface PaymentConsumer {

    @GetMapping("/consumer")
    CommonResult consumer();

}

@Slf4j
@Component
@NoArgsConstructor
class PaymentConsumerFallback implements PaymentConsumer {

    @Override
    public CommonResult consumer() {
        log.error("PaymentConsumerFallback 捕捉到异常... ");
        return CommonResult.error("PaymentConsumerFallback ....");
    }

}

//@Slf4j
//@Component
//@NoArgsConstructor
//class PaymentConsumerFallback implements PaymentConsumer, FallbackFactory<PaymentConsumer> {
//
//    private Throwable throwable;
//
//    private PaymentConsumerFallback(Throwable throwable){
//        this.throwable = throwable;
//    }
//
//    @Override
//    public PaymentConsumer create(Throwable throwable) {
//        return new PaymentConsumerFallback(throwable);
//    }
//
//    @Override
//    public CommonResult consumer() {
//        log.error("PaymentConsumerFallback 捕捉到异常... ", throwable);
//        return CommonResult.error("PaymentConsumerFallback ....");
//    }
//
//
//}
