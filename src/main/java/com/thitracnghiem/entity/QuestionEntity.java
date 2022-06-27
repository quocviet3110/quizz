package com.thitracnghiem.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cauhoi")
public class QuestionEntity extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -1096366787982902750L;

	@Column(name="CauHoi")
	private String ques_content;
	
	@Column(name="A")
	private String a_content;
	
	@Column(name="B")
	private String b_content;
	
	@Column(name="C")
	private String c_content;
	
	@Column(name="D")
	private String d_content;
	
	@Column(name="DAPAN")
	private String answer;
	
	@Column(name="MUCDO")
	private String lever;
	
	@OneToMany(mappedBy = "questions",fetch = FetchType.EAGER)
	private Collection<DetailExamEntity> detailExam;
	
	@ManyToOne
	@JoinColumn(name="ID_MONHOC")
	private SubjectsEntity subjects;
	
	@ManyToOne
	@JoinColumn(name="ID_GIAOVIEN")
	private TeacherEntity teacherQuestion;
	
	public String getQues_content() {
		return ques_content;
	}

	public void setQues_content(String ques_content) {
		this.ques_content = ques_content;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		this.a_content = a_content;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getD_content() {
		return d_content;
	}

	public void setD_content(String d_content) {
		this.d_content = d_content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getLever() {
		return lever;
	}

	public void setLever(String lever) {
		this.lever = lever;
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

	public TeacherEntity getTeacherQuestion() {
		return teacherQuestion;
	}

	public void setTeacherQuestion(TeacherEntity teacherQuestion) {
		this.teacherQuestion = teacherQuestion;
	}

}
