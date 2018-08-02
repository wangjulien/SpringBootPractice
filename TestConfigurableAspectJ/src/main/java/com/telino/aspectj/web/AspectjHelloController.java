package com.telino.aspectj.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.telino.aspectj.service.HelloService;

@RestController
@RequestMapping("/SayHello")
public class AspectjHelloController {
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST} )
	public String sayHello() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		// SockJs CLient Example
		List<Transport> transports = new ArrayList<>(2);
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		transports.add(new RestTemplateXhrTransport());

		SockJsClient sockJsClient = new SockJsClient(transports);
		sockJsClient.doHandshake(new MyWebSocketHandler(), "ws://localhost:8080/sockjs");
		
		
		// HelloService hs = new HelloService();
		
		Class<?> classDefinition = Class.forName("com.telino.aspectj.service.HelloService");
		HelloService hs = (HelloService) classDefinition.newInstance();
		return hs.sayHello();
	}
}
