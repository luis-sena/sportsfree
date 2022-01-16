package br.com.sportsfree.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests(auth -> auth
                    .antMatchers(HttpMethod.PUT, "/professor/**").hasAnyAuthority("SCOPE_ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/professor/**").hasAnyAuthority("SCOPE_ADMIN")
                    .antMatchers(HttpMethod.GET, "/professor/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_PROFESSOR")
                    .antMatchers(HttpMethod.POST, "/professor/**").permitAll()

                    .antMatchers(HttpMethod.PUT, "/doador/**").hasAnyAuthority("SCOPE_ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/doador/**").hasAnyAuthority("SCOPE_ADMIN")
                    .antMatchers(HttpMethod.GET, "/doador/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_DOADOR")
                    .antMatchers(HttpMethod.POST, "/doador/**").permitAll()

                    .antMatchers(HttpMethod.PUT, "/esporte/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_PROFESSOR")
                    .antMatchers(HttpMethod.DELETE, "/esporte/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_PROFESSOR")
                    .antMatchers(HttpMethod.GET, "/esporte/**").hasAnyAuthority("SCOPE_ADMIN","SCOPE_PROFESSOR")
                    .antMatchers(HttpMethod.POST, "/esporte/**").permitAll()
                    .anyRequest().authenticated())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
