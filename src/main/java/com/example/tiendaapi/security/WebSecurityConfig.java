package com.example.tiendaapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/productos" ).permitAll()
                .antMatchers("/api/productos/id/{id}").permitAll()
                .antMatchers("/api/productos/nombre/{nombre}").permitAll()
                .antMatchers("/api/productos/guardar").hasRole("ADMIN")
                .antMatchers("/api/productos/modificar").hasRole("ADMIN")
                .antMatchers("/api/productos/borrar/{id}").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails usuarioUno = User.builder()
                .username("Melisa")
                .password(passwordEncoder().encode("holaquetal"))
                .roles("USER")
                .build();

        UserDetails usuarioDos = User.builder()
                .username("Matias")
                .password(passwordEncoder().encode("123451"))
                .roles("ADMIN")
                .build();

        UserDetails usuarioTres = User.builder()
                .username("Juan")
                .password(passwordEncoder().encode("asdfasdf"))
                .roles("USER")
                .build();

        UserDetails usuarioCuatro = User.builder()
                .username("Franco")
                .password(passwordEncoder().encode("34567"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuarioUno, usuarioDos, usuarioTres, usuarioCuatro);
    }
}
