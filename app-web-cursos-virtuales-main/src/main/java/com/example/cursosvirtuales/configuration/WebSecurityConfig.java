package com.example.cursosvirtuales.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/css/**", "/img/**", "/js/**", "/", "/logeo", "/login", "/integrantes")
            .permitAll()
            // Estudiante
            .antMatchers("/estudiantes/listar").hasAnyRole("ADMIN", "LECTOR", "CREADOR", "EDITOR", "DEPURADOR")
            .antMatchers("/estudiantes/agregar").hasAnyRole("ADMIN", "CREADOR")
            .antMatchers("/estudiantes/guardar").hasAnyRole("ADMIN", "CREADOR", "EDITOR")
            .antMatchers("/estudiantes/editar/**").hasAnyRole("ADMIN", "EDITOR")
            .antMatchers("/estudiantes/eliminar/**").hasAnyRole("ADMIN", "DEPURADOR")
            // Resto de las reglas de autorización
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.sendRedirect("/custom-error");
            });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
            .withUser("rodrigo").password(encoder.encode("rodrigo")).roles("ADMIN")
            .and()
            .withUser("piero").password(encoder.encode("piero")).roles("LECTOR")
            .and()
            .withUser("elio").password(encoder.encode("elio")).roles("CREADOR", "LECTOR")
            .and()
            .withUser("sabrina").password(encoder.encode("sabrina")).roles("LECTOR", "DEPURADOR")
            .and()
            .withUser("eddy").password(encoder.encode("eddy")).roles("EDITOR", "LECTOR");
    }
}
