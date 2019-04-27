package com.jba.opencms.web.configuration;

import com.jba.opencms.security.service.UserDetailsProvider;
import com.jba.opencms.type.user.enu.AuthorityEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsProvider userDetailsProvider;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SecurityConfig(UserDetailsProvider userDetailsProvider) {
        this.userDetailsProvider = userDetailsProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //TODO - Go back to role selection after debug
                .authorizeRequests()
                .anyRequest().permitAll()
//                .anyRequest().authenticated()
                .antMatchers("/dashboard/**")
                .permitAll()
//                .hasAnyRole(
//                    AuthorityEnum.Administrator.name(),
//                    AuthorityEnum.Editor.name(),
//                    AuthorityEnum.Reviewer.name()
//                )
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
        web.ignoring().antMatchers( "/webjars/**", "/css/**", "/js/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}
