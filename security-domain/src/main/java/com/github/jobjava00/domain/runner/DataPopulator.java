package com.github.jobjava00.domain.runner;

import com.github.jobjava00.domain.account.Account;
import com.github.jobjava00.domain.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author jobjava00
 */
@Component
public class DataPopulator implements ApplicationRunner {
	@Autowired
	AccountRepository accountRepository;

	@Override public void run(ApplicationArguments args) throws Exception {
		//초기 데이터 생성
		Account account = new Account();
		account.setPassword(new BCryptPasswordEncoder().encode("password"));
		account.setEmail("jobjava00@gmail.com");
		account.setRole("ROLE_ADMIN");
		account.setJoined(new Date());
		accountRepository.save(account);
	}
}
