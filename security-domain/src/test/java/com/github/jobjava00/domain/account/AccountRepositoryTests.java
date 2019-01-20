package com.github.jobjava00.domain.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * @author jobjava00
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests {
	@Autowired
	private AccountRepository accountRepository;

	private static String EMAIL = "jobjava00@gmail.com";

	@Before
	public void setUp() {
		Account account = new Account();
		account.setEmail(EMAIL);
		account.setPassword("1234");
		account.setRole("ROLE_ADMIN");
		account.setJoined(new Date());
		accountRepository.save(account);
	}

	@Test
	public void testFindByEmail() {
		Account result = accountRepository.findByEmail(EMAIL);
		assertNotNull(result);
	}
}
