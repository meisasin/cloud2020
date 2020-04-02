package net.yisasin.alibaba.seata.feign;

import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${cservice.nodeMap.alibaba-seata-order-service}", path = "/order")
public interface OrderClient {

    @GetMapping("/create")
    CommonResult create(@RequestParam("userId") String userId,
                        @RequestParam("commodityCode") String commodityCode,
                        @RequestParam("orderCount") int orderCount);
}
