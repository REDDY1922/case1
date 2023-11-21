package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Account;
import com.springboot.main.service.AccountService;

@RequestMapping("/account")
@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/post")
	public Account postAccount(@RequestBody Account account) {
		return accountService.postAccount(account);
	}

	@GetMapping("/one/{aid}")
	public ResponseEntity<?> getAccount(@PathVariable("aid") int aid) {
		// Account account=accountService.getAccount(id);
		try {
			Account account = accountService.getAccount(aid);
			return ResponseEntity.ok().body(account);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update/{aid}")
    public ResponseEntity<?> updateAccount(@PathVariable("aid") int aid,@RequestBody Account newAccount) {
				try {
				
				Account oldAccount= accountService.getAccount(aid);
				if(newAccount.getBalance() != null)
				oldAccount.setBalance(newAccount.getBalance());
				
				
				oldAccount = accountService.postAccount(oldAccount); 
				return ResponseEntity.ok().body(oldAccount);
				
				} catch (InvalidIdException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
				}
}
}
