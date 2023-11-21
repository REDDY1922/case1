package com.springboot.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.AccountHolder;
import com.springboot.main.model.BankExecutive;
import com.springboot.main.repository.BankExecutiveRepository;

@Service
public class BankExecutiveService {
	@Autowired
	private BankExecutiveRepository bankExecutiveRepository;

	

	public BankExecutive add(BankExecutive bankExecutive) {
		// TODO Auto-generated method stub
		return bankExecutiveRepository.save(bankExecutive);
	}



	public BankExecutive getBankExecutive(int hid) throws InvalidIdException {
		Optional<BankExecutive> optional =  bankExecutiveRepository.findById(hid);
		if(!optional.isPresent()){
			throw new InvalidIdException("Executive ID Invalid");
		}
		return optional.get();


	}










}

