package br.ufc.quixada.npi.gp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.ufc.quixada.npi.ldap.service.LdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "br.ufc.quixada.npi.ldap" })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LdapAuthenticationProvider ldapAuthenticationProvider;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		    .authorizeRequests()
		    	.antMatchers("/404", "/js**", "/css**", "/images**").permitAll()
		    	.antMatchers("/Estagiario/**").hasAnyRole("ESTAGIARIO", "DISCENTE")
		    	.antMatchers("/Supervisor/**").hasAnyRole("SUPERVISOR", "DOCENTE", "STA").and()
		    .formLogin()
		        .loginPage("/login").successHandler(successHandler).permitAll().and()
		        .logout().logoutUrl("/logout").permitAll();
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(ldapAuthenticationProvider);
    }
}