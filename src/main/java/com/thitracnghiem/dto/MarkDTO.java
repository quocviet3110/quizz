package com.thitracnghiem.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MarkDTO extends AbstractDTO<MarkDTO> {
	
	private double mark;
	
	private StudentDTO studentMark;
	
	private ExamDTO examMark;
	
	private Date date;
	
	private String nameSubject;
	

	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
	private String convertDate;
	
	
	public String getConvertDate() {
		this.convertDate= dt.format(date);
		return convertDate;
	}
	public void setConvertDate(String convertDate) {
		this.convertDate = convertDate;
	}
	public String getNameSubject() {
		return nameSubject;
	}
	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public StudentDTO getStudentMark() {
		return studentMark;
	}
	public void setStudentMark(StudentDTO studentMark) {
		this.studentMark = studentMark;
	}
	public ExamDTO getExamMark() {
		return examMark;
	}
	public void setExamMark(ExamDTO examMark) {
		this.examMark = examMark;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
