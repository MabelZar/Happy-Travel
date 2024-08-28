package com.travel.travel.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.travel.travel.security.JWTAuthorizationFilter;

import static com.travel.travel.security.ConstansSecurity.*;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
class SecurityConfig{

    private JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter){
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
            .cors(Customizer.withDefaults()).csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                    .requestMatchers(HttpMethod.POST, SIGNIN_URL).permitAll()
                    .requestMatchers(HttpMethod.POST, DESTINATIONS_URL).permitAll()
                    .requestMatchers(HttpMethod.POST, DESTINATIONS_ADD_URL).authenticated()
                    .requestMatchers(HttpMethod.PUT, DESTINATIONS_UPDATE_URL).authenticated()
                    .requestMatchers(HttpMethod.GET, DESTINATIONS_LOCATION_URL).authenticated()
                    .requestMatchers(HttpMethod.DELETE, DESTINATIONS_DELETE_URL).authenticated()
                    .requestMatchers(HttpMethod.GET,DESTINATIONS_DETAILS_URL).authenticated()
            ).addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(LOCALHOST_FRONT_URL)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
            }
    }
}

