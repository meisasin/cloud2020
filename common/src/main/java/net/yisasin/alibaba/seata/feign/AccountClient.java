package net.yisasin.alibaba.seata.feign;

import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${cservice.nodeMap.alibaba-seata-account-service}", path = "/account")
public interface AccountClient {

    @GetMapping("/debit")
    CommonResult debit(@RequestParam("userId") String userId,
                       @RequestParam("money") int money);

}
