package com.java.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.chatbot.model.Chat;
import com.java.chatbot.service.ChatService;


@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
    private SimpMessagingTemplate template;
	
    @MessageMapping("/chat.conversation")
    @CrossOrigin
    public void sendMessage(@Payload Chat chat) {
    	template.convertAndSend("/topic/private/"+chat.getChatId(), chat);
    	chatService.sendMessage(chat);
    }
    @CrossOrigin
    @MessageMapping("/chat.join")
    public void joinUser(@Payload Chat chat) {
    	template.convertAndSend("/topic/private/"+chat.getChatId(), chat);
    	chatService.joinUser(chat);
    }
    @CrossOrigin
    @RequestMapping(value = "/getChatId", method = RequestMethod.GET)
    public String getChatId() {
    	return chatService.getchatId();
    }
}
