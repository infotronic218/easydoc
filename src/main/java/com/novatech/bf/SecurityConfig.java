package com.novatech.bf;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN");
		String roleQuery="SELECT user_email as  principal, roles_name as role   FROM users_roles  WHERE user_email=? ";
		String userQuery ="SELECT email as principal, password as credentials, active  FROM users WHERE email=?";
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(userQuery)
		.authoritiesByUsernameQuery(roleQuery).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		/*http.authorizeRequests().antMatchers(AdminUrls.root).hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(UserUrls.Annonce).hasAnyRole("COSTOMER");
		http.formLogin().loginPage(UserUrls.root+"/login")
		.failureHandler(new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auException)
					throws IOException, ServletException {
			     String message = "?message= Bad Credentials . Check your username and your passwprd .";
				response.sendRedirect(UserUrls.root+"/login"+message);
			}
		})
		.defaultSuccessUrl("/?message= You are now connected to your account");
		http.logout().logoutUrl("/user/logout")
		
		.logoutRequestMatcher(new AntPathRequestMatcher(UserUrls.root+"/logout"))
		.clearAuthentication(true)
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/?message=You have succefully Logout !");
	*/
		
	}
	
	

}
