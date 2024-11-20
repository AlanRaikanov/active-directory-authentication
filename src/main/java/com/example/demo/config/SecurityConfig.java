package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Value("${spring.ldap.urls}")
    private String LDAP_URL;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().fullyAuthenticated()
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    ActiveDirectoryLdapAuthenticationProvider authenticationProvider() {
        return new ActiveDirectoryLdapAuthenticationProvider("gfss.kz", LDAP_URL);
    }
}
