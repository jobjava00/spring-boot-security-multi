package com.github.jobjava00.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author jobjava00
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByEmail(String email);
}
