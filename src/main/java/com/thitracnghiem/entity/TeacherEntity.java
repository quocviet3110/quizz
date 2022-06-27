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
@Table(name="giaovien")
public class TeacherEntity extends BaseEntity {
	@Column(name="HOTEN")
	private String name;
	
	@Column(name="DIACHI")
	private String address;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="SODT")
	private String phone;
	
	@Column(name="IMAGE")
	private String image;
	
	@Column(name="HOCVI")
	private String degree;
	
	@OneToMany(mappedBy = "teacherQuestion" ,
			fetch = FetchType.EAGER)
	private Collection<QuestionEntity> questions;
	
	@OneToMany(mappedBy = "teacherExam" ,fetch = FetchType.LAZY)
	private Collection<ExamEntity> exams;
	
	@ManyToOne
	@JoinColumn(name="TAIKHOAN")
	private AccountEntity accountTeacher;

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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Collection<QuestionEntity> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<QuestionEntity> questions) {
		this.questions = questions;
	}

	public Collection<ExamEntity> getExams() {
		return exams;
	}

	public void setExams(Collection<ExamEntity> exams) {
		this.exams = exams;
	}

	public AccountEntity getAccountTeacher() {
		return accountTeacher;
	}

	public void setAccountTeacher(AccountEntity accountTeacher) {
		this.accountTeacher = accountTeacher;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
