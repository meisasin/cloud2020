package net.yisasin.alibaba.seata.service;

import com.alibaba.fastjson.JSON;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import net.yisasin.alibaba.seata.feign.OrderClient;
import net.yisasin.alibaba.seata.feign.StorageClient;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private StorageClient storageClient;

    @Autowired
    private OrderClient orderClient;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void purchase(String userId, String commodityCode, int orderCount) {

        log.info("globalTransactional begin, Xid:{}", RootContext.getXID());
        log.info("globalTransactional begin, XIDInterceptorType:{}", RootContext.getXIDInterceptorType());

        // 扣除库存
        storageClient.deduct(commodityCode, orderCount);

        // 创建订单
        CommonResult commonResult = orderClient.create(userId, commodityCode, orderCount);
        log.info("订单服务订单成功 -> {}", JSON.toJSONString(commonResult.getObj()));
    }
}
