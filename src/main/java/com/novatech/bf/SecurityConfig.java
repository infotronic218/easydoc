package com.novatech.bf;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
		String roleQuery="SELECT user_email as  principal, roles_name as role   FROM tusers_roles  WHERE user_email=? ";
		String userQuery ="SELECT email as principal, password as credentials, active  FROM tusers WHERE email=?";
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(userQuery)
		.authoritiesByUsernameQuery(roleQuery).passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
	
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		    .antMatchers("/admin","/admin/villes", "admin/delegues").hasAnyRole("DELEGUE","ADMIN");
		  
	   http.formLogin().loginPage("/login").defaultSuccessUrl("/");
		
	   http.logout().logoutUrl("/logout")
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutSuccessUrl("/login");
		
	}
	
	

}
