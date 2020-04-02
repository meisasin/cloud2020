package net.yisasin.alibaba.seata.controller;

import net.yisasin.alibaba.seata.service.BusinessService;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("/purchase")
    public CommonResult purchase(@RequestParam("userId") String userId,
                                 @RequestParam("commodityCode") String commodityCode,
                                 @RequestParam("orderCount") int orderCount) {

        businessService.purchase(userId, commodityCode, orderCount);

        return CommonResult.success("采购业务执行成功");
    }

}
