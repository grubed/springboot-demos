package com.example.springbooteventbus.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nepxion.eventbus.core.Event;
import com.nepxion.eventbus.core.EventControllerFactory;

/**
 * 发布
 *
 * @author maxiucheng
 * @date 2020/01/05 21:19:42
 */
@Service
public class MyPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(MyPublisher.class);

    @Autowired(required = false)
    private EventControllerFactory eventControllerFactory;

    public void publish() {
        if (eventControllerFactory != null) {
            LOG.info("发送事件...");

            // 异步模式下(默认)，子线程中收到派发的事件
            eventControllerFactory.getAsyncController().post("Sync Event String Format");

            // 同步模式下，主线程中收到派发的事件
            // 事件派发接口中eventControllerFactory.getSyncController(identifier)必须和@EventBus参数保持一致，否则会收不到事件
            eventControllerFactory.getSyncController().post("Sync Event String Format");

            // 异步模式下(默认)，子线程中收到派发的事件
            eventControllerFactory.getAsyncController().post(12345L);

            // 同步模式下，主线程中收到派发的事件
            // 事件派发接口中eventControllerFactory.getSyncController(identifier)必须和@EventBus参数保持一致，否则会收不到事件
            eventControllerFactory.getSyncController().post(Boolean.TRUE);

            // 异步模式下(默认)，子线程中收到派发的事件
            eventControllerFactory.getAsyncController().postEvent(new Event("Async Event"));

            // 同步模式下，主线程中收到派发的事件
            // 事件派发接口中eventControllerFactory.getSyncController(identifier)必须和@EventBus参数保持一致，否则会收不到事件
            eventControllerFactory.getSyncController().postEvent(new Event("Sync Event"));
        } else {
            LOG.error("EventBus功能未开启");
        }
    }
}