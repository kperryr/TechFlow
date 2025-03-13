package org.taskntech.tech_flow.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration

//this annotation exposes SecurityFilterChain bean & enables Spring Security integration with Spring MVC.
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //CSRF (Cross-Site Request Forgery) helps protect against malicious state-changing requests
                .csrf(Customizer.withDefaults())
                //requires user to provide credentials
                .httpBasic(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> {
                    //allows forwarding and error handling request to be done without authentication
                    auth.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll();
                    //anyone is allowed to access these endpoints
                    auth.requestMatchers("/", "/login", "/error", "/ws/**", "/topics/**", "/css/**").permitAll();
                    //authentication is required for all other requests
                    auth.anyRequest().authenticated();
                })

                //Configures OAuth 2.0 login and logout at root
                .oauth2Login(form -> form
                        .loginPage("/")
                        .defaultSuccessUrl("/tickets/create", true))
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )

                //finalizes and constructs the security filter chain with spring security
                // applies filters above to all HTTP requests
                .build();
    }

}


