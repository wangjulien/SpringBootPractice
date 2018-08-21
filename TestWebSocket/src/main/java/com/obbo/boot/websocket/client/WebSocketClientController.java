package com.obbo.boot.websocket.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.obbo.boot.websocket.model.ChatMessage;

@RestController
public class WebSocketClientController {

	@GetMapping("/client-chat/{message}")
	public String launchWebClient(@PathVariable("message") String message) throws InterruptedException, ExecutionException {

		WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport(simpleWebSocketClient));

		SockJsClient sockJsClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		String url = "ws://localhost:8080/ws";
		String userId = "spring-" + ThreadLocalRandom.current().nextInt(1, 99);
		
		// Connection and subscribe the 'topic'
		StompSessionHandler sessionHandler = new ClientStompSessionHandler(userId);
		StompSession session = stompClient.connect(url, sessionHandler).get();
		
		Thread.sleep(1000);
		
		// Send the message
		ChatMessage msg = new ChatMessage(userId, message, ChatMessage.MessageType.CHAT);
		session.send("/app/chat.sendMessage", msg);
		
		return "Message sent";
	}
}
