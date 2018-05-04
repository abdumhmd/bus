package com.abd.abcrbts.abcrbts.Config;

import com.abd.abcrbts.abcrbts.Repository.UserRepository;
import com.abd.abcrbts.abcrbts.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/").authenticated()

                .antMatchers("/notification/**/**").hasAuthority("ADMIN")
                .antMatchers("/ticket/**/**").hasAuthority("TICKET_OFFICER")
                .antMatchers("/routes/**/**").hasAuthority("ADMIN")
                .antMatchers("/buses/**/**").hasAuthority("ADMIN")

                .antMatchers("/schedule/**/**").hasAuthority("ADMIN")
                .antMatchers("/user/**/**").hasAuthority("ADMIN")
                .antMatchers("/agents/**/**").hasAuthority("ADMIN")
                .antMatchers("/reports/**/**").hasAnyAuthority()
                .antMatchers("/login").anonymous()
                .and()
                .formLogin().loginPage("/login").successForwardUrl("/home")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/403");
    }
}
