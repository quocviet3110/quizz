package com.thitracnghiem.dto;

public class QuestionDTO extends AbstractDTO<QuestionDTO> {
	
	private String ques_content;
	
	private String a_content;
	
	private String b_content;
	
	private String c_content;
	
	private String d_content;
	
	private String answer;
	
	private String lever;
	
	
	private String nameTeacher;
	
	private int idSubject;
	
	private String nameSubject;
	
	
	public String getQues_content() {
		return ques_content;
	}

	public String getNameTeacher() {
		return nameTeacher;
	}

	public void setNameTeacher(String nameTeacher) {
		this.nameTeacher = nameTeacher;
	}

	public String getNameSubject() {
		return nameSubject;
	}

	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
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

	public int getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}

	
	
	
}	
