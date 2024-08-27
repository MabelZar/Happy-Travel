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
                    .requestMatchers(HttpMethod.POST,"/auth/log_in").permitAll()
                    .requestMatchers(HttpMethod.POST,"/auth/sign_in").permitAll()
                    .requestMatchers(HttpMethod.POST,"/auth/destinations").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/destinations/add").authenticated()
                    .requestMatchers(HttpMethod.PUT,"/api/destinations/update").authenticated()
                    .requestMatchers(HttpMethod.GET,"/api/destinations/location").authenticated()
                    .requestMatchers(HttpMethod.DELETE,"/api/destinations/delete/{id}").authenticated()
                    .requestMatchers(HttpMethod.GET,"/api/destinations/details/{id}").authenticated()
                    //.requestMatchers(HttpMethod.GET,"/api/destinations/details/{id}").hasAuthority("ADMIN").anyRequest().authenticated()
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
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
            }
    }
}

