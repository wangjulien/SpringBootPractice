package com.obbo.boot.websocket.model;

public class ChatMessage {

	private MessageType type;
	private String content;
	private String sender;

	public enum MessageType {
		CHAT, JOIN, LEAVE
	}

	public ChatMessage() {
		super();
	}

	public ChatMessage(String sender, String content, MessageType type) {
		super();
		this.sender = sender;
		this.content = content;
		this.type = type;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
