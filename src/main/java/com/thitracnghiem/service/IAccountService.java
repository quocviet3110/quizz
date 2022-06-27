package com.thitracnghiem.service;

import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.AccountDTO;

@Service
public interface IAccountService {
	AccountDTO findOneByUsername(String username);
	AccountDTO save(AccountDTO accountDTO);
}
