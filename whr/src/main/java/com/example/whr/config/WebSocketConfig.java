package com.example.whr.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * web socket 配置类
 * 发送消息
 * STOMP在WebSocket之上提供了一个基于帧的线路格式层，用来定义消息的语义。
 * STOMP帧由命令、一个或多个头信息以及负载所组成
 *
 * @author huangchunmei
 * @create 2019/9/13 18:59
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        // 为 /ws/endpointChat 路径启用SockJS功能
        registry.addEndpoint("/ws/endpointChat").withSockJS();
    }

    /**
     * 配置了一个简单的消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        // 表明在queue、topic这两个域上可以向客户端发消息。
        registry.enableSimpleBroker("/queue","/topic");
    }
}
