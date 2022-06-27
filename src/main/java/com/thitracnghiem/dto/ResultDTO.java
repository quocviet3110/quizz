package com.thitracnghiem.dto;

public class ResultDTO extends AbstractDTO<ResultDTO> {
	
	private int idStudent;	
	private int idExam;
	private int status;
	private String[] answer;
	private String username;
	
	
	public int getIdExam() {
		return idExam;
	}
	public void setIdExam(int idExam) {
		this.idExam = idExam;
	}
	public String[] getAnswer() {
		return answer;
	}
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
