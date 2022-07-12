package uz.pdp.apelsinspringapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("1")).authorities("READ_PAYMENT","ADD_PAYMENT","UPDATE_PAYMENT","READ_CATEGORY","ADD_CATEGORY","UPDATE_CATEGORY","DELETE_PAYMENT")
                .and()
                .withUser("manager").password(passwordEncoder().encode("2")).authorities("READ_PAYMENT","ADD_PAYMENT","UPDATE_PAYMENT","DELETE_CATEGORY")
                .and()
                .withUser("user").password(passwordEncoder().encode("3")).authorities("READ_OWN_PAYMENT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/category/*").hasAuthority("READ_CATEGORY")
//                .antMatchers(HttpMethod.DELETE,"/api/category/**").hasAuthority("DELETE_CATEGORY")
//                .antMatchers(HttpMethod.PUT,"/api/category/*").hasAuthority("UPDATE_CATEGORY")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


//    role based
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .cors().disable()
//                .formLogin().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/category").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }
}
