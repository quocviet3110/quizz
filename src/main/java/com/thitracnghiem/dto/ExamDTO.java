package com.thitracnghiem.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamDTO extends AbstractDTO<ExamDTO>{
	private Date date;
	private int time;
	private int number;
	private int idSubject;
	private String nameSubject;
	private String username;
	private String idTeacher;
	private int status;
	private String codeExam;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
	private String convertDate;
	
	
	public String getConvertDate() {
		this.convertDate= dt.format(date);
		return convertDate;
	}
	public void setConvertDate(String convertDate) {
		this.convertDate = convertDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getCodeExam() {
		return codeExam;
	}
	public void setCodeExam(String codeExam) {
		this.codeExam = codeExam;
	}
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
	public int getIdSubject() {
		return idSubject;
	}
	public void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}
	public String getNameSubject() {
		return nameSubject;
	}
	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdTeacher() {
		return idTeacher;
	}
	public void setIdTeacher(String idTeacher) {
		this.idTeacher = idTeacher;
	}
	
	
}
