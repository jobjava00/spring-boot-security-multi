package com.github.jobjava00.web.account;

import com.github.jobjava00.domain.account.AccountRepository;
import com.github.jobjava00.domain.account.AccountUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author jobjava00
 */
@Service
public class AccountService implements UserDetailsService {
	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository){
		this.accountRepository = accountRepository;
	}

	@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepository.findByEmail(username)
				.map(account -> new AccountUser(account))
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}
}
