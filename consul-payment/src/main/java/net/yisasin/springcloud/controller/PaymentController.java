package net.yisasin.springcloud.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import net.yisasin.springcloud.common.model.CommonResult;
import net.yisasin.springcloud.dao.PaymentDao;
import net.yisasin.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentDao paymentDao;

    @Value("${server.port}")
    private String port;


    @GetMapping("/create/{serial}")
    public CommonResult create(@PathVariable("serial") String serial) {

        final Payment payment = new Payment(null, serial);

        int id = paymentDao.insert(payment);
        if (id > 0) {
            return CommonResult.success("数据库保存成功, port : " + port, id);
        }
        return CommonResult.error("新增失败, port : " + port);
    }

    @GetMapping("/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        return CommonResult.success(port, paymentDao.getById(id));
    }


    @GetMapping("/consul")
    public CommonResult consul() {
        String message = StrUtil.format("Port: {}, Random: {}", port, IdUtil.fastSimpleUUID());
        return CommonResult.success(message);
    }


}
