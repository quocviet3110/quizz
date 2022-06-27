package com.thitracnghiem.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sinhvien")
public class StudentEntity extends BaseEntity {
	
	@Column(name="HOTEN")
	private String name;
	
	@Column(name="DIACHI")
	private String address;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="SODT")
	private String phone;
	
	@Column(name="GIOITINH")
	private String gender;
	
	@OneToMany(mappedBy = "students" ,fetch = FetchType.EAGER)
	private Collection<ResultEntity> result;
	
	@ManyToOne
	@JoinColumn(name="TAIKHOAN")
	private AccountEntity accountStudent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Collection<ResultEntity> getResult() {
		return result;
	}

	public void setResult(Collection<ResultEntity> result) {
		this.result = result;
	}

	public AccountEntity getAccountStudent() {
		return accountStudent;
	}

	public void setAccountStudent(AccountEntity accountStudent) {
		this.accountStudent = accountStudent;
	}
	
	
}
