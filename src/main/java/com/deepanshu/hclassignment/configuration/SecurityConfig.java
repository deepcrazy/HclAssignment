package com.deepanshu.hclassignment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()  // Allow unrestricted access to all paths
                .and()
                .csrf().disable();  // Disable CSRF for simplicity, consider enabling in a production environment

        // Allow access to the H2 console
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll();

        // Disable CSRF and frame options for H2 console
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public HttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true); // Allow double slash
        return firewall;
    }
}
