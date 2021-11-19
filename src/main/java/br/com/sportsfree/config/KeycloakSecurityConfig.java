package br.com.sportsfree.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests(auth -> auth
                .antMatchers(HttpMethod.POST, "/professor/**").hasAnyRole("admin")
                .antMatchers(HttpMethod.PUT, "/professor/**").hasAnyRole("admin")
                .antMatchers(HttpMethod.DELETE, "/professor/**").hasAnyRole("admin")
                .antMatchers(HttpMethod.GET, "/professor/**").hasAnyRole("user")
                .anyRequest().authenticated());
        http.csrf().disable();
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider provider = keycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(provider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) {
        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setPrefix("ROLE_");

        KeycloakAuthenticationProvider provider = keycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(mapper);
        authenticationManagerBuilder.authenticationProvider(provider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }

    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
