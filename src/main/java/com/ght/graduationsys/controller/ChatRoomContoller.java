package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.Message;
import com.ght.graduationsys.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


/**
 * 
 * 功能描述：简单版单人聊天
 *
 */
@Controller
public class ChatRoomContoller {

	@Autowired
	private WebSocketService ws;
	
	


	@MessageMapping("/single/chat")
	public void singleChat(Message message) {


		ws.sendChatMessage(message);

	}
	
	
	
}
