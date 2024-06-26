package com.zensar.springBoot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtAuthFilter jwtAuthFilter;

	//SecurityConfiguration.java
		@Bean
		public DaoAuthenticationProvider getAuthenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setPasswordEncoder(passwordEncoder);
			authProvider.setUserDetailsService(userDetailsService);
			return authProvider;
		}
		
		@Bean
		public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
			return config.getAuthenticationManager();
		}
	 
	//Authorization
	@Bean
	public SecurityFilterChain authorize(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth-> {
			auth.requestMatchers("/zensar/create").hasAnyRole("ADMIN")
			.requestMatchers("/zensar/").permitAll()
			.requestMatchers("/zensar/byId/{stockId}").hasRole("USER")
			.requestMatchers("/zensar/update/{stokId}").hasAnyRole("MANAGER","ADMIN")
			.requestMatchers("/zensar/delete/{stockId}").hasRole("ADMIN")
			.requestMatchers("/login/authenticate","/stock/db/url").permitAll()
			.requestMatchers("/swagger-ui.html/index.html").permitAll()
			.anyRequest().authenticated();
		})
		//.formLogin(Customizer.withDefaults())
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
 
	/*@Bean
	public UserDetailsService userss() {
		UserBuilder users = User.builder();
		UserDetails habiUser= users.username("Habi").password(passwordEncoder.encode("786786")).roles("USER").build();
		UserDetails abdullaUser= users.username("Abdulla").password(passwordEncoder.encode("786786")).roles("USER").build();
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(habiUser,abdullaUser);
        return inMemoryUserDetailsManager;
	
	}*/

}
