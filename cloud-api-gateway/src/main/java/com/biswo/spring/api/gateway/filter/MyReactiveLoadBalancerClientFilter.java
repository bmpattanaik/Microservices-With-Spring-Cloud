package com.biswo.spring.api.gateway.filter;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerUriTools;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.reactive.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.cloud.gateway.support.DelegatingServiceInstance;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.loadbalancer.core.HealthCheckServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class MyReactiveLoadBalancerClientFilter {/*extends ReactiveLoadBalancerClientFilter {

	private static final Logger log = LoggerFactory.getLogger(MyReactiveLoadBalancerClientFilter.class);

	private static final int LOAD_BALANCER_CLIENT_FILTER_ORDER = 10150;

	private final LoadBalancerClientFactory clientFactory;

	private LoadBalancerProperties properties;

	private AtomicInteger position;

	private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
	
	public MyReactiveLoadBalancerClientFilter(LoadBalancerClientFactory clientFactory,
			LoadBalancerProperties properties,
			// RoundRobinLoadBalancer dependencies
			ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, int seed) {
		super(clientFactory,properties);
		this.clientFactory = clientFactory;
		this.properties = properties;
		this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
		this.position = new AtomicInteger(seed);
	}

	@Override
	public int getOrder() {
		return LOAD_BALANCER_CLIENT_FILTER_ORDER;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		
		URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
		String schemePrefix = exchange.getAttribute(GATEWAY_SCHEME_PREFIX_ATTR);
		if (url == null || (!"lb".equals(url.getScheme()) && !"lb".equals(schemePrefix))) {
			return chain.filter(exchange);
		}
		// preserve the original url
		addOriginalRequestUrl(exchange, url);

		if (log.isTraceEnabled()) {
			log.trace(MyReactiveLoadBalancerClientFilter.class.getSimpleName() + " url before: " + url);
		}
		org.springframework.http.HttpHeaders map=exchange.getRequest().getHeaders();
		
		
		
		List<String> serviceId=map.get("X-Service-Id");
		List<String> positionId=map.get("X-Position");
		log.info("ServiceId list{} ,positionId {}",serviceId,positionId);

		
		return choose(url,serviceId.get(0)).doOnNext(response -> {
			

			if (!response.hasServer()) {
				throw NotFoundException.create(properties.isUse404(), "Unable to find instance for " + url.getHost());
			}

			URI uri = exchange.getRequest().getURI();
						// if the `lb:<scheme>` mechanism was used, use `<scheme>` as the default,
			// if the loadbalancer doesn't provide one.
			String overrideScheme = null;
			if (schemePrefix != null) {
				overrideScheme = url.getScheme();
			}

			DelegatingServiceInstance serviceInstance = new DelegatingServiceInstance(response.getServer(),
					overrideScheme);

			URI requestUrl = LoadBalancerUriTools.reconstructURI(serviceInstance, uri);

			if (log.isTraceEnabled()) {
				log.trace("********LoadBalancerClientFilter url chosen: " + requestUrl);
			}
			exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, requestUrl);
		}).then(chain.filter(exchange));
	}

	/**
	 * This method is the implementation of {@link RoundRobinLoadBalancer}
	 *
	 * @return the instance that will be used to connect
	 * @param url the url requested used to qualify versioned instances
	 *
	private Mono<Response<ServiceInstance>> choose(URI url,String serviceId) {
		// TODO: move supplier to Request?
		// Temporary conditional logic till deprecated members are removed.
		
		Mono<Response<ServiceInstance>> monoServiceInstance=null; 
		ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
				.getIfAvailable(NoopServiceInstanceListSupplier::new);
		monoServiceInstance= supplier.get().next().map(s->{return getInstanceResponse(s,serviceId);});
		log.trace(monoServiceInstance.toString());
		return monoServiceInstance;
	}

	private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances,String serviceId) {
		if (instances.isEmpty()) {
			log.warn("No servers available for service: " + serviceId);
			return new EmptyResponse();
		}
		// TODO: enforce order?
		int pos = Math.abs(this.position.incrementAndGet());

		ServiceInstance instance = instances.get(pos % instances.size());
		log.trace("*****************************Sending request to:{}",instance.getUri().toString());

		return new DefaultResponse(instance);
		//return null;
	}*/
}
