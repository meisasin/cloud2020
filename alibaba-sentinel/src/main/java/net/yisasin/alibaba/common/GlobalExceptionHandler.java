package net.yisasin.alibaba.common;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import net.yisasin.springcloud.common.model.CommonResult;

@Slf4j
public class GlobalExceptionHandler {


    public static CommonResult handle_1(BlockException blcokE) {
        log.error("GlobalExceptionHandler.handle_1 error. ", blcokE);
        return CommonResult.error("GlobalExceptionHandler.handle_1 handler exception -> " + blcokE.getMessage());
    }

    public static CommonResult handle_2(BlockException blcokE) {
        log.error("GlobalExceptionHandler.handle_2 error. ", blcokE);
        return CommonResult.error("GlobalExceptionHandler.handle_2 handler exception -> " + blcokE.getMessage());
    }

    public static CommonResult handle_3(BlockException blcokE) {
        log.error("GlobalExceptionHandler.handle_3 error. ", blcokE);
        return CommonResult.error("GlobalExceptionHandler.handle_3ø handler exception -> " + blcokE.getMessage());
    }
}
