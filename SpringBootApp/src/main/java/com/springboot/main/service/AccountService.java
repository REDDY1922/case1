package com.springboot.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Account;
import com.springboot.main.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public Account postAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}

	public Account getAccount(int aid) throws InvalidIdException {
		Optional<Account> optional =  accountRepository.findById(aid);
		if(!optional.isPresent()){
			throw new InvalidIdException("Account ID Invalid");
		}
		return optional.get();
	

}
}
