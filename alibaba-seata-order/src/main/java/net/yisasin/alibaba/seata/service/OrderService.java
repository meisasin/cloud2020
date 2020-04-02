package net.yisasin.alibaba.seata.service;

import net.yisasin.alibaba.seata.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Order create(String userId, String commodityCode, int orderCount);

}