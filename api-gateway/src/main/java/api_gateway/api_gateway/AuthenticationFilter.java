package api_gateway.api_gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.Data;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final Jwtutils jwtutils;

    public AuthenticationFilter(Jwtutils jwtutils) {
        super(Config.class); 
        this.jwtutils = jwtutils;
    }

   
    @Data
    public static class Config {
        private String baseMessage;
    }

    @Override
    public GatewayFilter apply(Config config) {
        
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

           
            if (request.getURI().getPath().contains("/login")) {
                return chain.filter(exchange);
            }

     
            if (!request.getHeaders().containsKey("Authorization")) {
            	  return unauthorized(exchange, "Invalid Authorization");
            }

            String authHeader = request.getHeaders().get("Authorization").get(0);
            if (!authHeader.startsWith("Bearer ")) {
            	// System.out.println("Validation Failed: " + e.getMessage()); // Console check karein
                 return unauthorized(exchange, "Invalid authHeader");
            }

            String token = authHeader.substring(7);

           
            
              if(jwtutils.validate(token) == true)
              {
            	  ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
            		        .header("X-Gateway-Header", "WajidKey@123") 
            		        .build();
            	  return chain.filter(exchange.mutate().request(modifiedRequest).build());
              }else {
                System.out.println("Validation Failed: "); 
                return unauthorized(exchange, "Invalid Token");
            }

             
        };
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String msg) {
       
        System.out.println("Unauthorized: " + msg);
        
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        
       
        return exchange.getResponse().setComplete();
    }
}