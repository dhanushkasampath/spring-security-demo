package com.learn.spring_security_demo.config;

import com.learn.spring_security_demo.service.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailService myUserDetailService;

    // This bean says to spring. Hey.. don't go for default security filter chain. instead go through this flow.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(customizer ->customizer.disable()); // due to this now we should be able to make POST requests
//        httpSecurity.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());// by doing this no one able to access the resource without authentication
//        httpSecurity.formLogin(Customizer.withDefaults()); // enable the form login in browser
//        httpSecurity.httpBasic(Customizer.withDefaults()); // enable the form login in postman
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


//        we can simply use builder pattern instead of setting values to httpSecurity object

          return httpSecurity
                  .csrf(customizer ->customizer.disable())
                  .authorizeHttpRequests(requests -> requests
                          .requestMatchers("user-login", "register")
                          .permitAll()// allow to access above endpoints without spring-security
                          .anyRequest().authenticated())
                  .formLogin(Customizer.withDefaults())
                  .httpBasic(Customizer.withDefaults())
                  .sessionManagement(session ->
                          session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .build();
    }

    // what we expect is to login with multiple username and password.
    // By default ‘UserDetailsService’ is used. But we want to customize it. for that we return a bean as follows
    // this is a inbuilt thing in spring security
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("dhanushka")
                .password("d@123")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("sampath")
                .password("s@123")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    // above userDetailsService bean is overridden by below authenticationProvider bean which provide
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());//saying that we no need want the default password encoder
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(myUserDetailService);//we need to provide our own implementation
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
