package com.obbo.boot.websocket.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.obbo.boot.websocket.model.ChatMessage;

@Controller
public class ChatController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		
		LOGGER.info(chatMessage.getContent());
		return chatMessage;
	}
	
	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		//Add username in web socket session
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		
		LOGGER.info("User joined : {}", chatMessage.getSender());
		return chatMessage;
	}
	
	@MessageExceptionHandler
    @SendToUser("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
