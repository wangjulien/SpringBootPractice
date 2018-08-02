package com.telino.aspectj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

public class MyDecoratedWebSocketHandler extends WebSocketHandlerDecorator {
	
	@Autowired
	public MyDecoratedWebSocketHandler(WebSocketHandler myWebSocketHandler) {
		super(myWebSocketHandler);
	}

}
