package net.yisasin.springcloud;

import com.alibaba.fastjson.JSON;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.StringBuilders;
import org.junit.Test;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 多实现监听测试
 */
@Slf4j
public class MutilImplListener extends EventApplicationTest {

    @Test
    public void noticePublish() throws InterruptedException {

        Notice notice = new Notice();
        notice.setSender("张三");
        notice.setRecevicer("李四");
        notice.setTitle("不要走，决战到天亮");
        notice.setContent("我张三要你李四死");
        notice.setPlatformList(Arrays.asList(Platform.SELLER, Platform.BUYYER));

        log.info("发布事件 >>> {}", JSON.toJSONString(notice));
        applicationContext.publishEvent(notice);

        TimeUnit.SECONDS.sleep(2L);
    }

}

/**
 * 消息监听类实现
 */
@Slf4j
@Component
class NewsListener<T extends News>{
//
//    @EventListener
//    public void handlerAnnounce(Announce announce) {
//        log.info("接收到 announce listener 收到的消息 -> {}", JSON.toJSONString(announce));
//    }
//
//    @EventListener
//    public void handlerNotice(Notice notice) {
//        log.info("接收到 notice listener 收到的消息 -> {}", JSON.toJSONString(notice));
//    }
//
//    @EventListener
//    public void handlerMessage(Message message) {
//        log.info("接收到 message listener 收到的消息 -> {}", JSON.toJSONString(message));
//    }

    @EventListener()
    @Async
    public void handlerNews(T news) {
        log.info("接收到 news listener 收到的消息 -> {}", JSON.toJSONString(news));
    }

//    @EventListener()
//    public void handlerNews(News news) {
//        log.info("接收到 object listener 收到的消息 -> {}", news);
//    }
}

/**
 * 消息父类
 */
@Data
class News {

    private String sender;

    private String recevicer;

    private String title;

    private String content;

    private List<Platform> platformList;

}
/**
 * 系统公告
 */
@Data
class Announce extends News {
    private String tag = "announce";
}

/**
 * 系统通知
 */
@Data
class Notice extends News {
    private String tag = "notice";
}

/**
 * 服务消息
 */
@Data
class Message extends News{
    private String tag = "message";
}

@Getter
enum Platform {

    OP("运营"),
    SELLER("卖家"),
    BUYYER("买家")
    ;

    Platform(String name) {
        this.name = name;
    }
    private String name;
}
