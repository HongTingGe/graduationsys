package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageService {
    public List<Message> getMessages(@Param("from") String from, @Param("to") String to);
    public void addMessage(Message message);
}
