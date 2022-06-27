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
@Table(name= "monhoc")
public class SubjectsEntity extends BaseEntity{
	
	@Column(name="TENMH")
	private String name;
	
	@OneToMany(mappedBy = "subjects" ,
			fetch = FetchType.EAGER)
	private Collection<QuestionEntity> questions;
	
	@OneToMany(mappedBy = "subjects" ,fetch = FetchType.EAGER)
	private Collection<ExamEntity> exams;
	
	@ManyToOne
	@JoinColumn(name="MACD")
	private LevelEntity levels;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public LevelEntity getLevels() {
		return levels;
	}
	public void setLevels(LevelEntity levels) {
		this.levels = levels;
	}
	
	
}
