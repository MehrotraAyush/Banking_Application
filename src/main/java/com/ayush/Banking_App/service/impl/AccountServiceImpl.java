package com.ayush.Banking_App.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayush.Banking_App.dto.AccountDto;
import com.ayush.Banking_App.entity.Account;
import com.ayush.Banking_App.mapper.AccountMapper;
import com.ayush.Banking_App.repository.AccountRepository;
import com.ayush.Banking_App.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}



	@Override
	public AccountDto createAccount(AccountDto accountdto) {
		Account account=AccountMapper.mapToAccount(accountdto);
		Account savedAccount= accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto getAccountByID(Long id) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow( () -> new RuntimeException("Account Does Not Exits"));
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposite(Long id, double amount) {
		
		Account account=accountRepository
				.findById(id)
				.orElseThrow( () -> new RuntimeException("Account Does Not Exits"));
		double total=account.getBalence()+amount;
		account.setBalence(total);
		Account savedAccount =accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account=accountRepository
				.findById(id)
				.orElseThrow( () -> new RuntimeException("Account Does Not Exits"));
		
		if(account.getBalence() < amount) {
			throw new RuntimeException("Insuffient Balence");
		}
		else {
			double total=account.getBalence()-amount;
			account.setBalence(total);
		}
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account) ->AccountMapper.mapToAccountDto(account))
			.collect(Collectors.toList());
	}



	@Override
	public void deleteAccount(Long id) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow( () -> new RuntimeException("Account Does Not Exits"));
		accountRepository.deleteById(id);
	}
	
	
	
}
