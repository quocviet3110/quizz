package com.thitracnghiem.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thitracnghiem.dto.DetailExamDTO;
import com.thitracnghiem.service.IDetailExamService;
@CrossOrigin
@RestController(value = "detailExamAPIofAdmin")
public class detailExamAPI {
	@Autowired
	private IDetailExamService iDetailExamService;
	
	@PostMapping("/api/detailExam")
	public void createdDetailExam(@RequestBody DetailExamDTO detailExamDTO) {	
		iDetailExamService.save(detailExamDTO);
	}
	@PutMapping("/api/detailExam")
	public void updateDetailExam(@RequestBody DetailExamDTO detailExamDTO) {	
		iDetailExamService.save(detailExamDTO);	
	}
	
}
