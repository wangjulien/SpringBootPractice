package com.obbo.security.sso.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

@Configuration
@EnableOAuth2Client
@EnableAuthorizationServer
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

	private final OAuth2ClientContext oauth2ClientContext;

	public WebSecurityConfigure(OAuth2ClientContext oauth2ClientContext) {
		super();
		this.oauth2ClientContext = oauth2ClientContext;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin().disable()
			.antMatcher("/**")
			.authorizeRequests()
				.antMatchers("/", "/login**", "/webjars/**", "/error**").permitAll()
				.anyRequest().authenticated()
			.and()
			.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
			.and()
			.logout()
				.logoutSuccessUrl("/").permitAll()
			.and()
			.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}
	
	private Filter ssoFilter() {
		
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		
		filters.add(ssoFilter(facebook(), "/login/facebook"));
		filters.add(ssoFilter(github(), "/login/github"));

		filter.setFilters(filters);		
		return filter;
	}
	
	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
				client.getClient().getClientId());
		tokenServices.setRestTemplate(template);
		filter.setTokenServices(tokenServices);
		return filter;
	}
	
	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
	  return new ClientResources();
	}

	@Bean
	@ConfigurationProperties("facebook")
	public ClientResources facebook() {
	  return new ClientResources();
	}
	
	@Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}
}
