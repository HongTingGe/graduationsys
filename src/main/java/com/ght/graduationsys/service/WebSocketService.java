package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.Message;

public interface WebSocketService {
    public void sendChatMessage(Message message);
}
