//package net.yisasin.springcloud;
//
//import cn.hutool.core.date.DateUtil;
//import com.alibaba.fastjson.JSON;
//import lombok.Builder;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
///**
// * 简单事件监听测试
// */
//@Slf4j
//public class SimpleListener extends EventApplicationTest {
//
//    @Test
//    public void simplePublish() {
//        log.info("Simple publish ....");
//        applicationContext.publishEvent(BeanBuilder.orderEvent());
//    }
//
//    @Test
//    public void servicePublish() {
//        log.info("Service publish ....");
//        applicationContext.getBean(OrderService.class).publishOrder();
//    }
//
//    @Test
//    public void initPublish() throws InterruptedException {
//        log.info("ApplicationRunner publish ....");
//
//        TimeUnit.SECONDS.sleep(2);
//
//    }
//
//    @Bean
//    public ApplicationRunner runner() {
//        log.info("ApplicationRunner Init.");
//        return args -> {
//            applicationContext.getBean(OrderService.class).publishOrder();
//        };
//    }
//
//
//}
//
//
//@Service
//class OrderService {
//
//    @Autowired
//    private ApplicationContext context;
//
//    public void publishOrder() {
//        context.publishEvent(BeanBuilder.orderEvent());
//    }
//}
//
//
///**
// * 监听对象
// */
//@Data
//@Builder(toBuilder = true)
//class OrderEvent {
//
//    private String msg;
//    private Date cTime;
//
//}
//
///**
// * 监听器
// */
//@Slf4j
//@Component
//class OrderEventListener {
//
//    @EventListener
//    public void handleOrderEvent(OrderEvent orderEvent) {
//
//        log.info("监听到了 handleOrderEvent 发布的消息为：{}", JSON.toJSONString(orderEvent));
//    }
//
//    @EventListener
//    public void handleOrderEventToExit(OrderEvent orderEvent) {
//
//        log.info("监听到了 handleOrderEventToExit 发布的消息为：{}", JSON.toJSONString(orderEvent));
//    }
//}
//
///**
// * 对象构建
// */
//class BeanBuilder {
//    public static OrderEvent orderEvent() {
//        return OrderEvent.builder().msg("建立订单").cTime(DateUtil.parse("2019-07-26")).build();
//    }
//}