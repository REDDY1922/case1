package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Account;
import com.springboot.main.model.AccountDetails;
import com.springboot.main.model.AccountHolder;
import com.springboot.main.model.BankExecutive;
import com.springboot.main.service.AccountDetailsService;
import com.springboot.main.service.AccountHolderService;
import com.springboot.main.service.AccountService;
import com.springboot.main.service.BankExecutiveService;
@RestController
public class AccountDetailsController {
	@Autowired
	private AccountDetailsService accountDetailsService;
	@Autowired
	private AccountHolderService accountHolderService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BankExecutiveService bankExecutiveService;
	@PostMapping("/accountdetails/{hid}/{aid}/{bid")
	public ResponseEntity<?> addAccountDetails(@RequestBody  AccountDetails accountDetails,@PathVariable ("hid")int hid,@PathVariable ("aid")int aid,@PathVariable ("bid")int bid){
		try {
			Account account= accountService.getAccount(aid);
			accountDetails.setAccount(account);
			AccountHolder accountHolder= accountHolderService.getAccountHolder(hid);
			accountDetails.setAccountHolder(accountHolder);
			BankExecutive bankExecutive= bankExecutiveService.getBankExecutive(bid);
			accountDetails.setBankExecutive(bankExecutive);
			
			accountDetails = accountDetailsService.addAccountDetails(accountDetails);
			return ResponseEntity.ok().body(accountDetails);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
