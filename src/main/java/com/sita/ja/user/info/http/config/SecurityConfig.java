package com.sita.ja.user.info.http.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		/* Create a UserDetails object for a user named "user" with password "user" and
		   role "USER" Encode password using passwordEncoder
		*/ 
		UserDetails abdul = User.builder().username("user").password(passwordEncoder().encode("user")) 
				.roles("USER").build();

		// Return an InMemoryUserDetailsManager initialized with the UserDetails object
		return new InMemoryUserDetailsManager(abdul);
	}

	// Bean definition for PasswordEncoder
	@Bean
	public static PasswordEncoder passwordEncoder() {
		// Return a BCryptPasswordEncoder for encoding passwords
		return new BCryptPasswordEncoder();
	}

}
