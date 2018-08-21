package com.obbo.boot.websocket.client;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.obbo.boot.websocket.model.ChatMessage;

public class ClientStompSessionHandler extends StompSessionHandlerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientStompSessionHandler.class);

	private String userId;

	public ClientStompSessionHandler(String userId) {
		this.userId = userId;
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		LOGGER.info("Connected! Headers:");
		showHeaders(connectedHeaders);

		subscribeTopic("/topic/public", session);
		sendJsonMessage(session);
	}

	private void showHeaders(StompHeaders headers) {
		LOGGER.info(headers.entrySet().stream()
				.map(e -> e.getValue().stream().collect(Collectors.joining(",", e.getKey() + ": ", "")))
				.collect(Collectors.joining("\n")));
	}

	private void sendJsonMessage(StompSession session) {
		ChatMessage msg = new ChatMessage(userId, "", ChatMessage.MessageType.JOIN);
		session.send("/app/chat.addUser", msg);
	}

	private void subscribeTopic(String topic, StompSession session) {
		session.subscribe(topic, new StompFrameHandler() {

			@Override
			public Type getPayloadType(StompHeaders headers) {
				return ChatMessage.class;
			}

			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				LOGGER.info(((ChatMessage) payload).getContent());
			}
		});
	}

}
