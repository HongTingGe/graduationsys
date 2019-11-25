package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.MessageMapper;
import com.ght.graduationsys.entity.Message;
import com.ght.graduationsys.entity.MessageJson;
import com.ght.graduationsys.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


/**
 * 
 * 功能描述：简单消息模板，用来推送消息
 *
 *
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

	
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private MessageMapper messageMapper;


	@Override
	public void sendChatMessage(Message message) {
		//messageMapper.addMessage(message);

		MessageJson messageJson=new MessageJson();
		messageJson.setFrom(message.getFrom());
		messageJson.setContent(message.getContent());
		messageJson.setCreatetime(message.getCreatetime());


		template.convertAndSend("/chat/single/"+message.getTo(), messageJson);
	}

	
	
}
