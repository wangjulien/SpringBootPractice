package com.telino.aspectj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;

@Configuration
@EnableSpringConfigured
@EnableLoadTimeWeaving
public class AspectJconfig implements LoadTimeWeavingConfigurer {

	@Override
	public LoadTimeWeaver getLoadTimeWeaver() {
		return new InstrumentationLoadTimeWeaver();
	}
}
