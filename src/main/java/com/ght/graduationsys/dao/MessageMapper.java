package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    public List<Message> getMessages(@Param("from") String from, @Param("to") String to);
    public void addMessage(Message message);
}
