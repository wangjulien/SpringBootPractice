package com.obbo.boot.react;

import java.time.Duration;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class TryReactCore {

	public static void main(String[] args) {
//		List<Integer> elements = new ArrayList<>();
		
		ConnectableFlux<Object> publish = Flux.create(fluxSink -> {while(true) {
			fluxSink.next(System.currentTimeMillis());
			}}).sample(Duration.ofSeconds(2)).publish();
		
		publish.subscribe(System.out::println);        
//		publish.subscribe(System.out::println);
		
		publish.connect();
	}

}
