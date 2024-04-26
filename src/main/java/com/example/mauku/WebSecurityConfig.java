package com.example.mauku;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.example.mauku.web.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

        @Autowired
        private UserDetailServiceImpl userDetailService;

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                   .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                         antMatcher("/css/**"), 
                         antMatcher("/signup"),
                         antMatcher("/"),
                         antMatcher("/cats"),
                         antMatcher("/location/**")
                   ).permitAll()
                   .requestMatchers(
                         antMatcher("/addcat"), 
                         antMatcher("/delete/**"), 
                         antMatcher("/editlocation/**"),
                         antMatcher("/password"),
                         toH2Console()
                         ).authenticated()
                         .anyRequest().authenticated()
                     )
                     .csrf(csrf -> csrf
                         .ignoringRequestMatchers("/password")
                         .ignoringRequestMatchers(toH2Console())
                        )
                     .headers(headers -> headers
                         .frameOptions(frameoptions -> frameoptions
                             .disable()
                         )
                     )
                .formLogin(formlogin -> formlogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/cats")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/cats")
                        .permitAll()
                );

                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
