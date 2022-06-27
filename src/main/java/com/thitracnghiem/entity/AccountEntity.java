package com.thitracnghiem.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class AccountEntity{	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	
	@Id
	@Column(name = "TAIKHOAN")
	private String username;
	
	@Column(name="MATKHAU")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="ID_QUYEN")
	private RoleEntity roles;
	
	@Column(name="TRANGTHAI")
	private int status;
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	@OneToMany(mappedBy = "accountTeacher" ,fetch = FetchType.EAGER)
	private Collection<TeacherEntity> teacher;
	
	@OneToMany(mappedBy = "accountStudent" ,fetch = FetchType.EAGER)
	private Collection<StudentEntity> student;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleEntity getRoles() {
		return roles;
	}

	public void setRoles(RoleEntity roles) {
		this.roles = roles;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Collection<TeacherEntity> getTeacher() {
		return teacher;
	}

	public void setTeacher(Collection<TeacherEntity> teacher) {
		this.teacher = teacher;
	}

	public Collection<StudentEntity> getStudent() {
		return student;
	}

	public void setStudent(Collection<StudentEntity> student) {
		this.student = student;
	}
	
}
