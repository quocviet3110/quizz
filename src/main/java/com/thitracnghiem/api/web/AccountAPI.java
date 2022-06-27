package com.thitracnghiem.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thitracnghiem.dto.AccountDTO;
import com.thitracnghiem.service.IAccountService;
@CrossOrigin
@RestController(value = "accountAPIofWeb")
public class AccountAPI {
	@Autowired
	 private IAccountService iAccountService;
	
	
	@PostMapping("/api/web/account")
	public AccountDTO created(@RequestBody AccountDTO accountDTO ) {	
		return iAccountService.save(accountDTO);
	}
	@PutMapping("/api/web/question")
	public AccountDTO update(@RequestBody AccountDTO accountDTO ) {	
		return iAccountService.save(accountDTO);
	}
	
}
