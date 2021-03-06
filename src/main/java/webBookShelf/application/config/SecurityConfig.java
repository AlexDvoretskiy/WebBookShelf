package webBookShelf.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import webBookShelf.application.config.handlers.UserAuthenticationSuccessHandler;
import webBookShelf.application.services.dataServices.interfaces.UserService;

import javax.sql.DataSource;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private UserService userService;
	private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/shelf/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.and()
					.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/auth")
					.successHandler(userAuthenticationSuccessHandler)
					.permitAll()
				.and()
					.logout()
					.permitAll()
				    .logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.deleteCookies("JSESSIONID")
				.and()
					.rememberMe()
					.key("uniqueAndSecret")
					.userDetailsService(userService)
					.rememberMeParameter("remember-me")
					.tokenRepository(tokenRepository());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		return jdbcTokenRepositoryImpl;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setUserAuthenticationSuccessHandler(UserAuthenticationSuccessHandler userAuthenticationSuccessHandler) {
		this.userAuthenticationSuccessHandler = userAuthenticationSuccessHandler;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
