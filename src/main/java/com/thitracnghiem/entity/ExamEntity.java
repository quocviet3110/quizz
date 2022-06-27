package com.thitracnghiem.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="dethi")
public class ExamEntity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 5840410197583044181L;

	@Column(name="NGAYTHI")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date date;
	
	@Column(name="THOIGIANTHI")
	private int time;
	
	@Column(name="MADE")
	private String codeExam;
	
	@Column(name="TRANGTHAI")
	private int status;
	
	@Column(name="SOLUONGCAUHOI")
	private int number;
	
	@OneToMany(mappedBy = "exams",fetch = FetchType.EAGER)
	private Collection<DetailExamEntity> detailExam;
	
	@OneToMany(mappedBy = "examMark",fetch = FetchType.EAGER)
	private Collection<MarkEntity> markEntities;
	
	public Collection<MarkEntity> getMarkEntities() {
		return markEntities;
	}

	public void setMarkEntities(Collection<MarkEntity> markEntities) {
		this.markEntities = markEntities;
	}

	@ManyToOne
	@JoinColumn(name="ID_MONHOC")
	private SubjectsEntity subjects;
	
	@ManyToOne
	@JoinColumn(name="ID_GIAOVIEN")
	private TeacherEntity teacherExam;

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Collection<DetailExamEntity> getDetailExam() {
		return detailExam;
	}

	public void setDetailExam(Collection<DetailExamEntity> detailExam) {
		this.detailExam = detailExam;
	}

	public SubjectsEntity getSubjects() {
		return subjects;
	}

	public void setSubjects(SubjectsEntity subjects) {
		this.subjects = subjects;
	}

	public TeacherEntity getTeacherExam() {
		return teacherExam;
	}

	public void setTeacherExam(TeacherEntity teacherExam) {
		this.teacherExam = teacherExam;
	}

	

	public String getCodeExam() {
		return codeExam;
	}

	public void setCodeExam(String codeExam) {
		this.codeExam = codeExam;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
