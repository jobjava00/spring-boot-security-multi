package com.github.jobjava00.ldap.config;

import com.github.jobjava00.domain.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jobjava00
 */
@Component
public class UserServiceLdapAuthoritiesPopulater implements LdapAuthoritiesPopulator {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		return accountRepository.findByEmail(username)
				.map(account -> {
							if (account.isAdmin()) {
								authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
							} else {
								authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
							}
							return authorities;
						}
				)
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}
}
