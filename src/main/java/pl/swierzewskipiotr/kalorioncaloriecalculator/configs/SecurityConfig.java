package pl.swierzewskipiotr.kalorioncaloriecalculator.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/userform").authenticated()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/")
                .defaultSuccessUrl("/userform");
    }
}