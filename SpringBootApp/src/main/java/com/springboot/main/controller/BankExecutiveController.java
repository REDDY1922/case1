package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.enums.RoleType;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.AccountHolder;
import com.springboot.main.model.BankExecutive;
import com.springboot.main.model.User;
import com.springboot.main.service.BankExecutiveService;
import com.springboot.main.service.UserService;
@RequestMapping("/bankexecutive")
@RestController
public class BankExecutiveController {

		
		@Autowired
		private BankExecutiveService bankExecutiveService;
		
		@Autowired
		private UserService userService;
		
		@PostMapping("/add")
		public BankExecutive addBankExecutive(@RequestBody BankExecutive bankExecutive) {
			User user=bankExecutive.getUser();
			
			user.setRole(RoleType.EXECUTIVE);
			user = userService.insert(user);
			bankExecutive.setUser(user);
			return bankExecutiveService.add(bankExecutive);
			
		}
		@GetMapping("/one/{bid}")
	    public ResponseEntity<?> getBankExecutive(@PathVariable("hid") int hid) {
	    	//Account account=accountService.getAccount(id);
	    	try {
				BankExecutive bankExecutive = bankExecutiveService.getBankExecutive(hid);
				return ResponseEntity.ok().body(bankExecutive);
			} catch (InvalidIdException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	    }


}
