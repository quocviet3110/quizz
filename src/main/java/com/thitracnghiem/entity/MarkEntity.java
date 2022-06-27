package com.thitracnghiem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="bangdiem")
public class MarkEntity extends BaseEntity {
	
	@Column(name= "DIEM")
	private double mark;
	
	@Column(name="NGAYTHI")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="ID_SINHVIEN")
	private StudentEntity studentMark;
	
	@ManyToOne
	@JoinColumn(name="ID_DETHI")
	private ExamEntity examMark;


	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StudentEntity getStudentMark() {
		return studentMark;
	}

	public void setStudentMark(StudentEntity studentMark) {
		this.studentMark = studentMark;
	}

	public ExamEntity getExamMark() {
		return examMark;
	}

	public void setExamMark(ExamEntity examMark) {
		this.examMark = examMark;
	}
	
}
