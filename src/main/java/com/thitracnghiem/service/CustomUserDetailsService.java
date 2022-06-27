package com.thitracnghiem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thitracnghiem.constant.SystemContants;
import com.thitracnghiem.dto.MyUser;
import com.thitracnghiem.entity.AccountEntity;
import com.thitracnghiem.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity accountEntity = accountRepository.findOneByUsernameAndStatus(username,
				SystemContants.ACTIVE_STATUS);
		if (accountEntity == null) {
			throw new UsernameNotFoundException("Account not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(accountEntity.getRoles().getJobCode()));
		MyUser myUser = new MyUser(accountEntity.getUsername(), accountEntity.getPassword(), true, true, true, true,
				authorities);
		myUser.setFullname(username);
		return myUser;
	}

}
