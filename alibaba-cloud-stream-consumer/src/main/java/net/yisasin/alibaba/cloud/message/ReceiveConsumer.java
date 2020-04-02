package net.yisasin.alibaba.cloud.message;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@Slf4j
@EnableBinding(Sink.class)
public class ReceiveConsumer {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(value = "input")
    public void input(Message<Object> message) {

        log.info("消费者 1 号接收到消息 >>> {}", JSON.toJSONString(message.getPayload()));

    }
}
