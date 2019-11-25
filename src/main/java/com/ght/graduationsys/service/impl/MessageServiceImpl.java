package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.MessageMapper;
import com.ght.graduationsys.entity.Message;
import com.ght.graduationsys.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getMessages(String from, String to) {
        return messageMapper.getMessages(from,to);
    }

    @Override
    public void addMessage(Message message) {
        messageMapper.addMessage(message);
    }
}
