package com.telino.ldap.mng;

import java.util.Collections;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ExampleInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("Telino", Collections.singletonMap("AppName", "LDAP Auth"));
	}

}
