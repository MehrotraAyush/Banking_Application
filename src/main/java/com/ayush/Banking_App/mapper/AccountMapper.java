package com.ayush.Banking_App.mapper;

import com.ayush.Banking_App.dto.AccountDto;
import com.ayush.Banking_App.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto accountDto) {
		Account account=new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				);
		return account;
	}
	
	
	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountdto=new AccountDto(
		
				account.getId(),
				account.getAccountHolderName(),
				account.getBalence()
				);
		return accountdto;
		
	}
}
