package com.blueteam.bluequiz.security;

import com.blueteam.bluequiz.service.QuizUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    final QuizUserService quizUserService;

    public WebSecurityConfig(QuizUserService quizUserService) {
        this.quizUserService = quizUserService;
    }

/*    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/registration**", "/registration/error**", "/api/user/add/**");
    }*/

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login/error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //NoOpPasswordEncoder.getInstance() используется тк я не смог победить "Encoded password does not look like BCrypt"
        auth.userDetailsService(quizUserService)
                .passwordEncoder(passwordEncoder);
    }
}