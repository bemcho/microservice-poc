package de.rewe.demo.product.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * A server token service which retrieves the access token information from a
 * remote source and also supports the circuit breaker pattern in case that the
 * auth service is unavailable.
 */
@Primary
@Service
@Qualifier("hystrixRemoteTokenServices")
public class HystrixRemoteTokenServices implements ResourceServerTokenServices {

	@Autowired
	@Qualifier("loadBalancedTemplate")
	private RestTemplate restTemplate;

	@LoadBalanced
	@Bean(name = "loadBalancedTemplate")
	private RestTemplate restTemplate() {

		// Ribbon template
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
	@Qualifier("remotetokenservice")
	private RemoteTokenServices delegate;

	@Bean
	@ConfigurationProperties("tokenservice")
	@Qualifier("remotetokenservice")
	RemoteTokenServices remoteTokenServices() {

		RemoteTokenServices eurekaAwareTokenService = new RemoteTokenServices();

		// add the ribbon template which consults Eureka
		eurekaAwareTokenService.setRestTemplate(restTemplate);

		return eurekaAwareTokenService;
	}

	@Override
	@HystrixCommand
	public OAuth2Authentication loadAuthentication(String accessToken)
			throws AuthenticationException, InvalidTokenException {
		try {
			return delegate.loadAuthentication(accessToken);
		} catch (AuthenticationException | InvalidTokenException e) {
			//TODO: This seems to be a bug that is still not fixed?
			//https://github.com/Netflix/Hystrix/pull/776
			throw new HystrixBadRequestException(e.getMessage(), e);
		}
	}

	@Override
	public OAuth2AccessToken readAccessToken(String accessToken) {
		throw new UnsupportedOperationException("This operation is not supported by a remote service!");
	}

}
