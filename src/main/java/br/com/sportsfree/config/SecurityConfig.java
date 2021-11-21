package br.com.sportsfree.config;

import br.com.sportsfree.config.filter.FirebaseTokenAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final FirebaseTokenAuthorizationFilter tokenAuthorizationFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
            .csrf().disable()
            .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
            .and()
            .sessionManagement().sessionCreationPolicy(STATELESS)
            .and()
            .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
            .addFilterBefore(tokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests(auth -> auth
            .antMatchers("/login/**").permitAll()
            .antMatchers(HttpMethod.POST, "/professor/**").hasAnyRole("admin")
            .antMatchers(HttpMethod.PUT, "/professor/**").hasAnyRole("admin")
            .antMatchers(HttpMethod.DELETE, "/professor/**").hasAnyRole("admin")
            .antMatchers(HttpMethod.GET, "/professor/**").hasAnyRole("user")
            .anyRequest().authenticated());
    }
}
