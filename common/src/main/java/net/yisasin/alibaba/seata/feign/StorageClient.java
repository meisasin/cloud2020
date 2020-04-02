package net.yisasin.alibaba.seata.feign;

import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${cservice.nodeMap.alibaba-seata-storage-service}", path = "/storage")
public interface StorageClient {

    @GetMapping("/deduct")
    CommonResult deduct(@RequestParam("commodityCode") String commodityCode,
                        @RequestParam("count") int count);
}
