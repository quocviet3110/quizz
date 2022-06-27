package com.thitracnghiem.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thitracnghiem.dto.ExamDTO;
import com.thitracnghiem.service.IExamService;
@CrossOrigin
@RestController(value = "examAPIofAdmin")
public class ExamAPI {
	@Autowired
	private IExamService iExamService;
	
	@PostMapping("/api/exam")
	public ExamDTO createdExam(@RequestBody ExamDTO examDTO ) {	
		return iExamService.save(examDTO);
	}
	@PutMapping("/api/exam")
	public ExamDTO updateExam(@RequestBody ExamDTO examDTO) {	
		return iExamService.save(examDTO);	
	}
	@DeleteMapping("/api/exam")
	public void deleteExam(@RequestBody int[] ids ) {	
		iExamService.delete(ids);
	}
}
