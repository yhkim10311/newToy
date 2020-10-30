package com.authser.config;

import org.h2.engine.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .headers()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(entryPointUnauthorizedHandler)
//                .and()
                .authorizeRequests()
                .antMatchers("/","/js/**","/h2-console/**").permitAll()
                .antMatchers("/join","/login","/post").permitAll()
                .antMatchers("/api/hcheck").permitAll()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/api/user/join").permitAll()
//                .antMatchers("/api/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/");
//        http
//                .addFilterBefore(redisAuthFilter(),
//                        UsernamePasswordAuthenticationFilter.class);

    }
}
