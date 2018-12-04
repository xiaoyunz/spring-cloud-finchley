package com.zhouxy.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


/**
 * 为什么需要将https的协议改为http？
 * 首先gateway代替nginx需要具备nginx的能力，所以https也跑不了，
 * 但使用后发现在loadbalance的时候调用微服务节点也是https，
 * 这样就会导致每开发一个服务就需要加一次证书，这就很尴尬。基于该问题，
 * 所以我们需要gateway对前端是https，对后端是http。

 * 如果检测到有ribbon，则开启LoadBalancerClientFilter,
 * 首先获取scheme，如果不是lb开头，则直接往下一个filter传递,
 * 之后通过loadBalancer.choose(url.getHost())来选取服务实例,
 * 最后构造好requestUrl，设置到GATEWAY_REQUEST_URL_ATTR属性中
 * 
 * 在LoadBalancerClientFilter执行之前将Https修改为Http
 * https://github.com/spring-cloud/spring-cloud-gateway/issues/378
 */
@Component
public class HttpsToHttpFilter implements GlobalFilter, Ordered {

    private static final int HTTPS_TO_HTTP_FILTER_ORDER = 10099;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI originalUri = exchange.getRequest().getURI();
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String forwardedUri = request.getURI().toString();
        if (forwardedUri != null && forwardedUri.startsWith("https")) {
            try {
                URI mutatedUri = new URI("http",
                        originalUri.getUserInfo(),
                        originalUri.getHost(),
                        originalUri.getPort(),
                        originalUri.getPath(),
                        originalUri.getQuery(),
                        originalUri.getFragment());
                mutate.uri(mutatedUri);
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    /**
     * 由于LoadBalancerClientFilter的order是10100，
     * 要在LoadBalancerClientFilter执行之前将Https修改为Http，需要设置
     * order为10099
     * @return
     */
    @Override
    public int getOrder() {
        return HTTPS_TO_HTTP_FILTER_ORDER;
    }
}
