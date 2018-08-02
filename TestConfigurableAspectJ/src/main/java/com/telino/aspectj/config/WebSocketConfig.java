package com.telino.aspectj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.telino.aspectj.web.MyWebSocketHandler;

/**
 * Native Spring WebSocket configuration
 * 
 * @author Jiliang.WANG
 *
 */
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myWebSocketHandler(), "/myWebSocketHandler")
				.addInterceptors(new HttpSessionHandshakeInterceptor()) // built-in interceptor for passing HTTP session attributes to the WebSocket session
				.setAllowedOrigins("http://mydomain.com")
				.withSockJS();
	}

	@Bean
	public WebSocketHandler myWebSocketHandler() {
		return new MyWebSocketHandler();
	}
	
	@Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

}
