package com.visi.standard.websocket;

import com.visi.component.InstallLogsPushDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class LogHandler extends TextWebSocketHandler {

    private Set<String> sessionIds = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("Connection established: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handle message: {}, payload: {}", session.getId(), message.getPayload());
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            // 删除相关的Session数据
            log.info("close Session : {}", session.getId());
            synchronized (this) {
                sessionIds.remove(session.getId());
                InstallLogsPushDispatcher.getInstance().removeLogsPusher(session);
                session.close();
            }
        } else {
            if (!sessionIds.contains(session.getId())) {
                sessionIds.add(session.getId());
                String payload = message.getPayload();
                log.info("payload is {}", payload);
                InstallLogsPushDispatcher.getInstance().addLogsPusher(payload, session);
            }
        }
    }
}
