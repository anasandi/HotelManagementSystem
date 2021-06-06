/*package com.example.hotelmanagenetsystem.security;

import com.example.hotelmanagenetsystem.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
public class WebMvc extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailServiceImpl userDetailsService;

    public WebMvc(BCryptPasswordEncoder bCryptPasswordEncoder,UserDetailServiceImpl userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/layout/**").permitAll()
                .antMatchers("/view/**").permitAll()
                .antMatchers("/room/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                 .usernameParameter("email")
                 .and()
                 .logout()
                  .and()
                .rememberMe()
                 .and()
                  .csrf()
                  .disable()

        ;
    }
*/
  //  @Override
   // protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*auth.inMemoryAuthentication()
                .withUser("ana")
                .password(bCryptPasswordEncoder.encode("ana"))
                .roles("ADMIN")
               .and()
                .withUser("thaw")
              .password(bCryptPasswordEncoder.encode("thaw"))
             .roles("USER");*/

       // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
   // }

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**");
    }
}*/
