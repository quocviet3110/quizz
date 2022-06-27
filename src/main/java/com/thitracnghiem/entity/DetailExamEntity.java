package com.thitracnghiem.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "dethi_chitiet")
public class DetailExamEntity {

	@EmbeddedId
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	private ExamDetailsKey idKeys;

	@ManyToOne
	@MapsId("id_exam")
	@JoinColumn(name = "ID_DETHI")
	private ExamEntity exams;

	@ManyToOne
	@MapsId("id_question")
	@JoinColumn(name = "ID_CAUHOI")
	private QuestionEntity questions;

	@MapsId("id")
	@JoinColumn(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public ExamDetailsKey getIdKeys() {
		return idKeys;
	}

	public void setIdKeys(ExamDetailsKey idKeys) {
		this.idKeys = idKeys;
	}

	public ExamEntity getExams() {
		return exams;
	}

	public void setExams(ExamEntity exams) {
		this.exams = exams;
	}

	public QuestionEntity getQuestions() {
		return questions;
	}

	public void setQuestions(QuestionEntity questions) {
		this.questions = questions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
