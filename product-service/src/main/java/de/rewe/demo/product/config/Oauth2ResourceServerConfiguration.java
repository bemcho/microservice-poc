package de.rewe.demo.product.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		//define security for the POST endpoints
		
		// @formatter:off
		http.authorizeRequests().
				antMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN").
			and().
		    	authorizeRequests().anyRequest().permitAll();
		// @formatter:on
	}
	
	@LoadBalanced
    @Bean(name="loadBalancedTemplate")
    RestTemplate restTemplate() {
        
		//Ribbon template
		RestTemplate restTemplate = new RestTemplate();
        
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			// Kindly borrowerd from remote token services
			public void handleError(ClientHttpResponse response) throws IOException {
				if (response.getRawStatusCode() != 400) {
					super.handleError(response);
				}
			}
		});
        
        return restTemplate;
    }
	
	@Autowired
	@Qualifier("loadBalancedTemplate")
	RestTemplate restTemplate;
	
	@Bean
	@ConfigurationProperties("tokenservice")
	RemoteTokenServices remoteTokenServices() {
		
		RemoteTokenServices eurekaAwareTokenService = new RemoteTokenServices();
		
		//add the ribbon template which consults Eureka
		eurekaAwareTokenService.setRestTemplate(restTemplate);
		
		return eurekaAwareTokenService;
	}
	
	
}