package com.github.jobjava00.web.config;

import com.github.jobjava00.web.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author jobjava00
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		/**
		 * static resources 들은 아래와 같이 처리
		 * permitAll 과 다른점
		 * - WebSecurity은 로그인 정보 가져오지 않음
		 * - HttpSecurity 세션객체로 로그인 정보 가져와서 사용
		 */
		web.ignoring()
				.antMatchers("/css/**", "/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//h2-console 제외하고 csrf 사용
		http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("!/h2-console/**"));
		http.headers().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'")).frameOptions().disable();
		http.httpBasic();
		http
				.authorizeRequests()
				.antMatchers("/", "/home", "/h2-console/*").permitAll()
				.antMatchers("/hello/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/home")
				.permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(accountService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
