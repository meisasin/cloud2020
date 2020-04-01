package net.yisasin.alibaba.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.yisasin.alibaba.config.GlobalConfig;
import net.yisasin.alibaba.config.NacosConfig;
import net.yisasin.springcloud.common.model.CommonResult;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Value("${config.info}")
    private String configInfo;

    @Autowired
    private NacosConfig nacosConfig;

    @Autowired
    private GlobalConfig globalConfig;

    @GetMapping("/query")
    public CommonResult query() {
        return CommonResult.success(JSON.toJSONString(configInfo));
    }

    @GetMapping("/nacos/config")
    public CommonResult nacosConfig() {
        log.info(" >>> {}", JSON.toJSONString(nacosConfig));
        return CommonResult.success(JSON.toJSONString(nacosConfig));
    }

    @GetMapping("/native/config")
    public CommonResult nativeConfig() {

        NacosConfig bean = applicationContext.getBean(NacosConfig.class);

        log.info(" >>> {}", JSON.toJSONString(bean));
        return CommonResult.success(JSON.toJSONString(bean));
    }

    @GetMapping("/global")
    public CommonResult global() {
        log.info(" >>> {}", JSON.toJSONString(globalConfig));
        return CommonResult.success(JSON.toJSONString(globalConfig));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
