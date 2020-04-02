package net.yisasin.alibaba.seata.controller;


import lombok.extern.slf4j.Slf4j;
import net.yisasin.alibaba.seata.service.AccountService;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private AccountService accountService;
    /**
     * 从用户账户中借出
     */
    @GetMapping("/debit")
    public CommonResult debit(@RequestParam("userId") String userId,
                               @RequestParam("money") int money) {
        log.info("Account service [从用户账户中借出]接收到请求 -> userId: {}, money: {}", userId, money);
        accountService.debit(userId, money);
        return CommonResult.success("从用户账户中借出成功");
    }


}
