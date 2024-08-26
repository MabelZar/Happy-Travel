package com.travel.travel.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
class SecurityConfig{

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
            .cors(Customizer.withDefaults()).csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.POST,"/auth/log_in").permitAll()
                    .requestMatchers(HttpMethod.POST,"/auth/sign_in").permitAll()
                    .requestMatchers(HttpMethod.POST,"/auth/destinations").permitAll()
                    //.requestMatchers(HttpMethod.POST,"/api/destinations").authenticated().anyRequest()
            );
            //.formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")  // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
}

