package com.bulletin.toy.config;

import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.security.JwtAuthenticationFilter;
import com.bulletin.toy.service.auth.JwtUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailService jwtUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager){
        return new JwtAuthenticationFilter(authenticationManager);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(JwtUserDetailService jwtUserDetailService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(jwtUserDetailService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider(jwtUserDetailService));
    }

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
                .authorizeRequests()
                  .antMatchers("/","/js/**","/h2-console/**").permitAll()
                  .antMatchers("/join","/login","/post").permitAll()
                  .antMatchers("/api/hcheck").permitAll()
                  .antMatchers("/api/auth").permitAll()
                  .antMatchers("/api/user/join").permitAll()
                  .antMatchers("/api/**").hasRole(Role.USER.name())
                  .anyRequest().authenticated()
                  .and()
                .logout()
                  .logoutSuccessUrl("/");
        http
                .addFilterBefore(jwtAuthenticationFilter(authenticationManagerBean()),
                        UsernamePasswordAuthenticationFilter.class);

    }
}
