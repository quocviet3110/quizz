package com.thitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.AccountEntity;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{
	AccountEntity findOneByUsernameAndStatus(String username,int stutus);
	AccountEntity findOneByUsername(String username);
}
