package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Account;
import com.springboot.main.model.AccountHolder;
import com.springboot.main.service.AccountHolderService;
@RequestMapping("/accountholder")
@RestController
public class AccountHolderController {
	@Autowired
	private AccountHolderService accountHolderService ;
	 @PostMapping("/post")
	public AccountHolder postAccountHolder(@RequestBody AccountHolder accountHolder){
		 return accountHolderService.postAccountHolder(accountHolder);	
	}
	 @GetMapping("/one/{hid}")
	    public ResponseEntity<?> getAccountHolder(@PathVariable("hid") int hid) {
	    	//Account account=accountService.getAccount(id);
	    	try {
				AccountHolder accountHolder = accountHolderService.getAccountHolder(hid);
				return ResponseEntity.ok().body(accountHolder);
			} catch (InvalidIdException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	    }

}
