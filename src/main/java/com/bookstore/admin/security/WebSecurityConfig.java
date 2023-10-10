package com.bookstore.admin.security;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bookstore.admin.security.oauth.CustomerOAuth2UserService;
import com.bookstore.admin.security.oauth.OAuth2LoginSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired private CustomerOAuth2UserService oAuth2UserService;
	@Autowired private OAuth2LoginSuccessHandler oauth2LoginHandler;
	@Autowired private DatabaseLoginSuccessHandler databaseLoginHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (getRemoteAddress() != null && getRemoteAddress().equals("127.0.0.1")) {
			http.csrf().disable().authorizeRequests().anyRequest().permitAll();
			return;
		}

		http.authorizeRequests()
				.antMatchers("/admin", "/stock", "/book/delete").hasRole("ADMIN")
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.successHandler(databaseLoginHandler)
				.permitAll()
				.and()
				.oauth2Login()
				.loginPage("/login")
				.userInfoEndpoint()
				.userService(oAuth2UserService)
				.and()
				.successHandler(oauth2LoginHandler)
				.and()
				.logout().permitAll()
				.and()
				.rememberMe()
				.key("1234567890_aBcDeFgHiJkLmNoPqRsTuVwXyZ")
				.tokenValiditySeconds(14 * 24 * 60 * 60)
//		.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
		;

	}
	private String getRemoteAddress() {
		RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
		if (attribs instanceof NativeWebRequest) {
			HttpServletRequest request = (HttpServletRequest) ((NativeWebRequest) attribs).getNativeRequest();
			return request.getRemoteAddr();
		}
		return null;
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**", "/error/**");
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomerUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}
