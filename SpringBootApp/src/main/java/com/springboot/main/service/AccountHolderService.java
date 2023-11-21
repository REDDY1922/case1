package com.springboot.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Account;
import com.springboot.main.model.AccountHolder;
import com.springboot.main.repository.AccountHolderRepository;

@Service
public class AccountHolderService {
	@Autowired
	private AccountHolderRepository accountHolderRepository;

	public AccountHolder postAccountHolder(AccountHolder accountHolder) {
		// TODO Auto-generated method stub
		return accountHolderRepository.save(accountHolder);
	}

	public AccountHolder getAccountHolder(int hid) throws InvalidIdException {
		Optional<AccountHolder> optional =  accountHolderRepository.findById(hid);
		if(!optional.isPresent()){
			throw new InvalidIdException("AccountHolder ID Invalid");
		}
		return optional.get();
	

}

}
