package com.telino.aspectj.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.telino.aspectj.model.Greeting;
import com.telino.aspectj.model.HelloMessage;

@Controller
public class GreetingController {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		String msg = "Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!\n";
		
		// Get session attributes
		Map<String, Object> attrs = headerAccessor.getSessionAttributes();
		msg += attrs.toString();
		
		// send messages to connected clients from any part of the application
		// this.template.convertAndSend("/topic/greetings", msg);
		
		Thread.sleep(1000);
		return new Greeting(msg);
	}
}
