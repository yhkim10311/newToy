package com.bulletin.toy.config;

import com.bulletin.toy.domain.user.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                  .headers().frameOptions().disable()
                .and()
                  .authorizeRequests()
                  .antMatchers("/").permitAll()
                  .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                  .anyRequest().authenticated()
                .and()
                  .logout().logoutSuccessUrl("/");

    }
}
