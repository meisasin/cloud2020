package net.yisasin.alibaba.seata.controller;

import lombok.extern.slf4j.Slf4j;
import net.yisasin.alibaba.seata.entity.Order;
import net.yisasin.alibaba.seata.service.OrderService;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 创建订单
     */
    @GetMapping("/create")
    public CommonResult create(@RequestParam("userId") String userId,
                               @RequestParam("commodityCode") String commodityCode,
                               @RequestParam("orderCount") int orderCount) {
        log.info("Order service [创建订单]接收到请求 -> userId: {}, commodityCode: {}, count: {}", userId, commodityCode, orderCount);

        Order order = orderService.create(userId, commodityCode, orderCount);
        return CommonResult.success(order);
    }

}
