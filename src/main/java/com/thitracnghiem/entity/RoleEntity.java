package com.thitracnghiem.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="quyen")
public class RoleEntity extends BaseEntity{
	
	@Column(name="TENCV")
	private String job;
	
	@Column(name="MACV")
	private String jobCode;
	
	@OneToMany(mappedBy = "roles" ,fetch = FetchType.EAGER)
	private Collection<AccountEntity> accounts;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public Collection<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	
}
