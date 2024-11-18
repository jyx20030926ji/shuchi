package com.testvue.testvue.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final String ONLINE_COUNT_KEY = "online_count";

    // 存储所有连接的 WebSocketSession
    private static final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);  // 将断开连接的 session 从集合中移除
        updateOnlineCountInRedis(-1);  // 更新 Redis 中的在线人数
        broadcastOnlineCount();  // 向所有客户端广播更新后的在线人数
    }

    // 连接时，增加 session
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);  // 将新连接的 session 加入集合
        updateOnlineCountInRedis(1);  // 更新 Redis 中的在线人数
        broadcastOnlineCount();  // 向所有客户端广播更新后的在线人数
    }

    // 断开连接时，移除 session


    // 向所有连接的客户端广播在线人数
    private void broadcastOnlineCount() throws IOException {
        String onlineCount = redisTemplate.opsForValue().get(ONLINE_COUNT_KEY);
        if (onlineCount != null) {
            TextMessage message = new TextMessage("在线人数: " + onlineCount);
            // 遍历所有连接的 session，发送消息
            for (WebSocketSession session : sessions) {
                session.sendMessage(message);
            }
        }
    }

    // 更新 Redis 中的在线人数
    private void updateOnlineCountInRedis(int delta) {
        redisTemplate.opsForValue().increment(ONLINE_COUNT_KEY, delta);  // 增加或减少在线人数
    }
}

