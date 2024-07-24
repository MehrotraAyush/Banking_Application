package com.ayush.Banking_App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.Banking_App.entity.Account;

public interface AccountRepository extends JpaRepository< Account ,Long>{

}
