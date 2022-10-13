//package se452.project.grocery.security;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//	
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.authorizeHttpRequests()
//		.antMatchers("/").permitAll()
//		.antMatchers("/login").permitAll()
//		.and()
//		.httpBasic().disable();
//		
//		
//	}
//	
//}