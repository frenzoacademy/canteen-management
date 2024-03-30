//package com.example.CanteenManagementSystem.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//
//	// @Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(inMemoryUserDetailsManager());
//	}
//
//	@Bean
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
//				.build();
//
//		UserDetails cantennManager = User.withDefaultPasswordEncoder().username("manager").password("managerpassword")
//				.roles("CANTENN_MANAGER").build();
//
//		return new InMemoryUserDetailsManager(user, cantennManager);
//	}
//
////	public SecurityFilterChain security(HttpSecurity http) throws Exception {
////		http.authorizeHttpRequests().requestMatchers("/user/login").authenticated()
////		.requestMatchers("/admin/login").authenticated()
////		.requestMatchers("canteen_manager/login").authenticated();
////		http.formLogin();
////		http.httpBasic();
////		return http.build();
////
////	}
//}
