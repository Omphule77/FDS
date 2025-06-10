package com.fds.foodiexpress.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "SELECT username, password, enabled FROM users WHERE username=?"
        );
 
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT username, authority FROM authorities WHERE username=?"
        );
 
        return jdbcUserDetailsManager;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/showRegister","/customerRegister","/showLogin","/restaurantRegister","/deliveryRegister").anonymous()
                .requestMatchers("/dashboard").hasRole("CUSTOMER")
                .requestMatchers("/restro-dashboard").hasRole("RESTAURANT")
                .requestMatchers("/delivery-dashboard").hasRole("DELIVERY")
                .anyRequest().authenticated()
            )
            .headers(headers -> headers
                .cacheControl(cache -> cache.disable()) 
            )
            .formLogin(login -> login
                .loginPage("/showLogin")
                .defaultSuccessUrl("/goto")
                .permitAll()
            )
            .logout(logout -> logout.logoutSuccessUrl("/showLogin"));
        return http.build();
    }

}
