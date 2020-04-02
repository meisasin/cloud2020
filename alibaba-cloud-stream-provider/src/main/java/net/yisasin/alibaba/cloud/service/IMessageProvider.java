package net.yisasin.alibaba.cloud.service;

public interface IMessageProvider {

    /**
     * 发送消息
     * @param message
     * @return
     */
    boolean send(Object message);
}
