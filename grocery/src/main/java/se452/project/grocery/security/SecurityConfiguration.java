// package se452.project.grocery.security;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

//     //https://stackoverflow.com/questions/23636368/how-to-disable-spring-security-login-screen
//     @Override
//     protected void configure(HttpSecurity security) throws Exception{
//         security.httpBasic().disable();
        
//     }
//     @Override
//     public void configure(WebSecurity web) throws Exception {
//         web
//             .ignoring()
//             .antMatchers("/**");
//     }

// }