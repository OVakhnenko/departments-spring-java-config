package com.vakhnenko.departments.config;

import com.vakhnenko.departments.filters.GuestAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    GuestAuthenticationFilter guestAuthenticationFilter;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login/");
        web.ignoring().antMatchers(
                "/**/*.css", "/**/*.png", "/**/*.js", "/fonts/*",
                "/**/*.ico", "/**/*.eot", "/**/*.svg", "/**/*.ttf", "/**/*.woff*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();

        http.addFilterBefore(guestAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/authorized/user", true);

        http.logout()
                .logoutUrl("/logout")
                //.deleteCookies("remember-me")
                .logoutSuccessUrl("/departments")
                .permitAll()
                .and()
                .rememberMe();

        http.authorizeRequests()
                .antMatchers("/guest").permitAll()
                .antMatchers("/public").permitAll()
                .antMatchers("/report/**").permitAll()
                .antMatchers("/departments**").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/department/**").permitAll()

                .antMatchers("/add/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/edit/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/remove/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/delete/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/demonstration").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

                .antMatchers("/authorized/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/authorized/admin/**").access("hasRole('ADMIN')")

                .antMatchers("/logout").access("isAuthenticated()")
                .anyRequest().authenticated()

                .and().exceptionHandling().accessDeniedPage("/403");
    }
}
