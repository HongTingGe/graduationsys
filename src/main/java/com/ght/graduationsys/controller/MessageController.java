package com.ght.graduationsys.controller;


import com.ght.graduationsys.entity.Message;
import com.ght.graduationsys.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/getMessages")
    public List<Message> getMessages(String from, String to){
        return messageService.getMessages(from,to);
    }

    @RequestMapping("/addMessage")
    public void addMessage(Message message){
        messageService.addMessage(message);
    }
}
