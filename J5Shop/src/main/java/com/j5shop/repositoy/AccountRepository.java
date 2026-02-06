package com.j5shop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j5shop.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Account findByEmailAndActive(String email, boolean active);
}
