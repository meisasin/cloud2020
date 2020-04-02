package net.yisasin.alibaba.seata.controller;

import lombok.extern.slf4j.Slf4j;
import net.yisasin.alibaba.seata.service.StorageService;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;
    /**
     * 扣除存储数量
     */
    @GetMapping("/deduct")
    public CommonResult deduct(@RequestParam("commodityCode") String commodityCode,
                               @RequestParam("count") int count) {
        log.info("Storage service [扣除存储数量]接收到请求 -> commodityCode: {}, count: {}", commodityCode, count);
        storageService.deduct(commodityCode, count);
        return CommonResult.success("扣除存储成功");
    }
}
