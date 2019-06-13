package com.jba.opencms.web.configuration;

import com.jba.opencms.security.service.UserDetailsProvider;
import com.jba.opencms.type.user.enu.AuthorityEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("security")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsProvider userDetailsProvider;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SecurityConfiguration(UserDetailsProvider userDetailsProvider) {
        this.userDetailsProvider = userDetailsProvider;
        logger.info("Using security configuration class: {}", this.getClass().getName());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/page/**").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/dashboard/**")
                .hasAnyRole(
                    AuthorityEnum.Administrator.name(),
                    AuthorityEnum.Editor.name(),
                    AuthorityEnum.Reviewer.name()
                )
        .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll()
        .and()
            .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers( "/webjars/**", "/css/**", "/js/**", "/image/**", "/images/**", "**/favicon.ico");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
