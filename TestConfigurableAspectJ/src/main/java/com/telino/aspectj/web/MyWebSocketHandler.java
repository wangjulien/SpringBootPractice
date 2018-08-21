package com.telino.aspectj.web;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// The WebSocket has been closed
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// The WebSocket has been opened
		// Let's send the first message
		session.sendMessage(new TextMessage("You are now connected to the server. This is the first message."));
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// A message has been received
		System.out.println("Message received: " + message.getPayload());
	}
}
