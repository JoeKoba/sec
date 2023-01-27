package com.example.sec.config;

import com.example.sec.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // сервис, с помощью которого тащим пользователя
    private final AppService appService;
    private final CustomAuthenticationSuccessHandler successHandler;
    // класс, в котором описана логика перенаправления пользователей по ролям
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(AppService appService,
                          CustomAuthenticationSuccessHandler successHandler, CustomAuthenticationSuccessHandler authenticationSuccessHandler,
                          PasswordEncoder passwordEncoder) {
        this.appService = appService;
        this.successHandler = successHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // конфигурация для прохождения аутентификации
        auth.userDetailsService(appService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().loginPage("/")
                .loginProcessingUrl("/process_login")
                .successHandler(successHandler)
                .failureUrl("/auth/login?error");

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() //выключаем кроссдоменную секьюрность
//                .authorizeRequests(authorize -> authorize
//                        .antMatchers("/").permitAll()
//                        .antMatchers("/login", "/error", "/auth/registration", "/auth/index").permitAll()
//                        .antMatchers("/admin/**").hasRole("ADMIN")
//
//                        .anyRequest().authenticated());
//
//        http.formLogin().loginPage("/auth/login")
//                .loginProcessingUrl("/process_login")
//                .successHandler(successHandler)
//                .failureUrl("/auth/login?error");
//
//
//    }
}