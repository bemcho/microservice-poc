package de.rewe.demo.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
			//currently - wide open
			http.
				authorizeRequests().
				anyRequest().
				permitAll();
			
			http.csrf().disable();	
		//@formatter:on
	}
}
