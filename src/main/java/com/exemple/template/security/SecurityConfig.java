package com.exemple.template.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("seck").password(passwordEncoder.encode("12345")).roles("USER");
        auth.inMemoryAuthentication().withUser("ousmane").password(passwordEncoder.encode("oussou")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("258")).roles("ADMIN");
    }
 /*                            ou
    auth
            .inMemoryAuthentication()
            .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
          .and()
          .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
}*/
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll(); /*accés sans authentification*/
        http.authorizeRequests().antMatchers("/delete/**", "/edit/**", "/save/**", "/formProfesseurs/**").hasRole("ADMIN");
        //http.authorizeRequests().antMatchers("/index/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll(); /*pour permetre au page statique de s'afficher sans authentification*/
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}