package com.ayush.Banking_App.service;

import java.util.List;

import com.ayush.Banking_App.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountdto);
	
	AccountDto getAccountByID(Long id);
	
	AccountDto deposite(Long id,double amount);
	
	AccountDto withdraw(Long id,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);
	
}
