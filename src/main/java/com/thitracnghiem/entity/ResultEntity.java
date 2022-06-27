package com.thitracnghiem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ketqua")
public class ResultEntity extends BaseEntity {
	
	@Column(name = "DAPAN")
	private String answer;

	@Column(name = "DAXOA")
	private int status;


	@ManyToOne
	@JoinColumn(name = "ID_SINHVIEN")
	private StudentEntity students;
	
	@ManyToOne
	@JoinColumn(name = "ID_CAUHOI")
	private QuestionEntity questionResult;
	
	@ManyToOne
	@JoinColumn(name = "ID_DETHI")
	private ExamEntity examResult;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public StudentEntity getStudents() {
		return students;
	}

	public void setStudents(StudentEntity students) {
		this.students = students;
	}

	public QuestionEntity getQuestionResult() {
		return questionResult;
	}

	public void setQuestionResult(QuestionEntity questionResult) {
		this.questionResult = questionResult;
	}

	public ExamEntity getExamResult() {
		return examResult;
	}

	public void setExamResult(ExamEntity examResult) {
		this.examResult = examResult;
	}


}
