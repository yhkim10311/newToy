package com.bulletin.toy.config;

import com.bulletin.toy.security.EntryPointUnauthorizedHandler;
import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.security.JwtAuthHelper;
import com.bulletin.toy.security.RedisAuthFilter;
import com.bulletin.toy.service.auth.CustomUserDetailService;
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

    private final CustomUserDetailService customUserDetailService;

    private final EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RedisAuthFilter redisAuthFilter() {
        return new RedisAuthFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailService customUserDetailService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider(customUserDetailService));
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
                .exceptionHandling()
                  .authenticationEntryPoint(entryPointUnauthorizedHandler)
                  .and()
                .authorizeRequests()
                  .antMatchers("/","/js/**","/h2-console/**").permitAll()
                  .antMatchers("/join","/login","/post","/accessdenied").permitAll()
                  .antMatchers("/api/hcheck").permitAll()
                  .antMatchers("/api/auth","/api/auth/callback").permitAll()
                  .antMatchers("/api/user/join").permitAll()
                  .antMatchers("/api/**").hasRole(Role.USER.name())
                  .anyRequest().authenticated()
                  .and()
                .logout()
                  .logoutSuccessUrl("/")
                  .invalidateHttpSession(true)
                  .deleteCookies(JwtAuthHelper.ACCESS_TOKEN_NAME);
        http
                .addFilterBefore(redisAuthFilter(),
                        UsernamePasswordAuthenticationFilter.class);

    }
}
