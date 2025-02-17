package be.bstorm.demospringmvc.configs;

import be.bstorm.demospringmvc.handlers.CustomAuthenticationFailureHandler;
import be.bstorm.demospringmvc.handlers.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r.requestMatchers(
                                        "/",
                                        "/styles/**",
                                        "/scripts/**",
                                        "/product",
                                        "/product/{id}").permitAll()
                                .requestMatchers(
                                        "/login",
                                        "/register").anonymous()
                                .requestMatchers(
                                        "/logout",
                                        "/order/create").authenticated()
                                .requestMatchers(
                                        "/product/create",
                                        "/product/update/{id}",
                                        "/product/delete/{id}").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(l -> l.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/product")
//                        .successHandler(authenticationSuccessHandler())
//                        .failureHandler(authenticationFailureHandler())
                )
                .logout(l -> l.logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
