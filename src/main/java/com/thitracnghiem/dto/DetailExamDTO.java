package com.thitracnghiem.dto;

public class DetailExamDTO extends AbstractDTO<DetailExamDTO> {
	
	private int idQuestion;
	private int  idExam;
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public int getIdExam() {
		return idExam;
	}
	public void setIdExam(int idExam) {
		this.idExam = idExam;
	}

}
