package com.thitracnghiem.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.AccountDTO;
import com.thitracnghiem.entity.AccountEntity;
import com.thitracnghiem.entity.RoleEntity;
import com.thitracnghiem.repository.AccountRepository;
import com.thitracnghiem.repository.RoleRepository;
import com.thitracnghiem.service.IAccountService;
@Service
public class AccountService implements IAccountService{
	@Autowired
	private AccountRepository accountRepostory;	 
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	ModelMapper mapper;
	public AccountService(AccountRepository accountRepostory, ModelMapper mapper) {
		super();
		this.accountRepostory = accountRepostory;
		this.mapper = mapper;
	}
	@Override
	public AccountDTO findOneByUsername(String username) {
		AccountEntity accountEntity = accountRepostory.findOneByUsername(username);
		return mapToDto(accountEntity);
	}
	private AccountDTO mapToDto(AccountEntity accountEntity) {
		AccountDTO accountDTO = mapper.map(accountEntity,AccountDTO.class);	
		accountDTO.setIdRole(accountEntity.getRoles().getId());
		return accountDTO;
	}
	
	@SuppressWarnings("unused")
	private AccountEntity mapToEntity(AccountDTO accountDTO) {
		AccountEntity accountEntity = mapper.map(accountDTO,AccountEntity.class);
		return accountEntity;
	}
	private AccountEntity mapToEntity(AccountEntity accountEntity,AccountDTO accountDTO) {
		accountEntity = mapper.map(accountDTO,AccountEntity.class);
		return accountEntity;
	}
	@Override
	@Transactional
	public AccountDTO save(AccountDTO accountDTO) {
		RoleEntity roleEntity = roleRepository.findOne(accountDTO.getIdRole());
		AccountEntity accountEntity = new AccountEntity();
		if (accountDTO.getId() != null) {
			AccountEntity oldAccount = accountRepostory.findOne(accountDTO.getId());
			accountEntity = this.mapToEntity(oldAccount,accountDTO);
			accountEntity.setPassword(bcryptEncoder.encode(accountDTO.getPassword()));
			accountEntity.setRoles(roleEntity);

		} else {
			accountEntity = this.mapToEntity(accountDTO);
			accountEntity.setPassword(bcryptEncoder.encode(accountDTO.getPassword()));
			accountEntity.setRoles(roleEntity);
		}
		return this.mapToDto(accountRepostory.save(accountEntity));	
	}
}
