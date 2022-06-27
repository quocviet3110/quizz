 package com.thitracnghiem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExamDetailsKey implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5037030579382921382L;

	@Column(name="ID_DETHI")
	private Integer id_exam;
	
	@Column(name="ID_CAUHOI")
	private Integer id_question;

	public Integer getId_exam() {
		return id_exam;
	}

	public void setId_exam(Integer id_exam) {
		this.id_exam = id_exam;
	}

	public Integer getId_question() {
		return id_question;
	}

	public void setId_question(Integer id_question) {
		this.id_question = id_question;
	}

			
}
