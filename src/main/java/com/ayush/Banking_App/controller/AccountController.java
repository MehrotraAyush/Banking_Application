package com.ayush.Banking_App.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.Banking_App.dto.AccountDto;
import com.ayush.Banking_App.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	//Add Account Rest API
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountdto){
		return new ResponseEntity<>(accountService.createAccount(accountdto),HttpStatus.CREATED);
	}
 
	//Get Account Rest API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountByID(@PathVariable Long id){
		AccountDto accountdto=accountService.getAccountByID(id);
		return ResponseEntity.ok(accountdto);
	}
	
	// Deposite Rest API
	
	@PutMapping("/{id}/deposite")
	public ResponseEntity<AccountDto> deposie(@PathVariable Long id,@RequestBody Map<String,Double> requested){
		
		double amount=requested.get("amount");
		AccountDto accountDto = accountService.deposite(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	// Withdraw Rest API
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw (@PathVariable Long id,@RequestBody Map<String,Double> requested){
		
		double amount=requested.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//Get All Account Rest API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	// Delete Account REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is Deleted Successfully !");
	}
	
}
