package net.yisasin.alibaba.cloud.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {

    @Autowired
    @Output(Source.OUTPUT)
    @Qualifier("outputa")
    private MessageChannel out;

    @Override
    public boolean send(Object message) {

        boolean flag = out.send(MessageBuilder.withPayload(message).build());

        log.info("发送消息 -> {}", JSON.toJSONString(message));

        return flag;
    }
}
