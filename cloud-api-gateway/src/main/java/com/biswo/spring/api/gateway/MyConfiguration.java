package com.biswo.spring.api.gateway;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.cache.LoadBalancerCacheManager;
import org.springframework.cloud.loadbalancer.core.CachingServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.DiscoveryClientServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.HealthCheckServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MyConfiguration {

	@Bean
	public WebClient webClient() {
		return WebClient.create();
	}

	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ReactiveDiscoveryClient discoveryClient, Environment environment,
			LoadBalancerProperties loadBalancerProperties, ApplicationContext context, WebClient webClient) {

		DiscoveryClientServiceInstanceListSupplier firstDelegate = new DiscoveryClientServiceInstanceListSupplier(
				discoveryClient, environment);
		HealthCheckServiceInstanceListSupplier delegate = new HealthCheckServiceInstanceListSupplier(firstDelegate,
				new org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerProperties.HealthCheck(),
				webClient);
		ObjectProvider<LoadBalancerCacheManager> cacheManagerProvider = context
				.getBeanProvider(LoadBalancerCacheManager.class);
		if (cacheManagerProvider.getIfAvailable() != null) {
			return new CachingServiceInstanceListSupplier(delegate, cacheManagerProvider.getIfAvailable());
		}
		return delegate;
	}
}
