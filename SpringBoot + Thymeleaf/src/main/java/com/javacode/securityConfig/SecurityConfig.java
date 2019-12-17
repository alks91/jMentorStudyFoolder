package com.javacode.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("getUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SuccessHandlerImpl successHandlerImpl;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/registration").permitAll()
                .and()
                .authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')")
                .anyRequest().authenticated();


        http
                .formLogin().successHandler(successHandlerImpl)
                .loginPage("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .logout()
                .permitAll();
    }

}

