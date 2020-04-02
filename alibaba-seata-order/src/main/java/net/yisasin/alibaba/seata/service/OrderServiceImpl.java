package net.yisasin.alibaba.seata.service;

import net.yisasin.alibaba.seata.dao.OrderDao;
import net.yisasin.alibaba.seata.entity.Order;
import net.yisasin.alibaba.seata.feign.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountClient accountClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order create(String userId, String commodityCode, int orderCount) {

        // 扣除账户余额
        accountClient.debit(userId, orderCount * 10);

        // 手动异常
        int money = 10 / 0;

        Order order = new Order(null, userId, commodityCode, orderCount, orderCount * 10);
        // 创建订单
        orderDao.insert(order);

        return order;
    }
}
