package com.quinbay.commoninfra.gateway.config;

import com.quinbay.commoninfra.gateway.validation.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Configuration
public class CorsConfig {


    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public WebFilter corsFilter() throws Exception {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();

            // Check if Authorization Header Exists in the request

            if (request.getHeaders().containsKey( "Authorization" )){


                // get the access token from Authorization header

                String accessToken = ctx.getRequest().getHeaders().get( "Authorization" ).get( 0 );


                try {

                    // check if the token is expired

                    if (!jwtUtil.isTokenExpired( accessToken )) {

                        // add username and roles as headers to the response

                        ctx.getResponse().getHeaders().add( "username", jwtUtil.extractUsername( accessToken ) );
                        ctx.getResponse().getHeaders().add( "roles", jwtUtil.extractPayload( accessToken ) );


                        // return the chain

                        return chain.filter( ctx );

                    }
                } catch (Exception e) {
                    ctx.getResponse().setComplete();
                    return chain.filter( ctx);
                }
            }


            // if error occurs at anypoint return with cts.getResponse().setComplete();

            ctx.getResponse().setStatusCode( HttpStatus.FORBIDDEN );
            ctx.getResponse().setComplete();
            return chain.filter( ctx);

        };
    }

}
