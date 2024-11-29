package net.tomczek.invoice.manager.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Profile("!develop")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Alle Requests mit GET müssen die VIEWERT Rolle haben
        // Alle anderen Request die Manager rolle
        http.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.GET, "/**").hasRole("VIEWER")
                .anyRequest().hasRole("MANAGER")
        ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    @Profile("develop")
    public SecurityFilterChain securityFilterChainWithoutSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request.anyRequest().permitAll())
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .oauth2ResourceServer(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
