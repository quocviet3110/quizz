package com.thitracnghiem.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thitracnghiem.dto.SubjectsDTO;
import com.thitracnghiem.service.ISubjectsService;
@CrossOrigin
@RestController(value = "subjectAPIofAdmin")
public class SubjectAPI {
	@Autowired
	private ISubjectsService iSubjectsService;
	
	
	@PostMapping("/api/subject")
	public SubjectsDTO createdQuestion(@RequestBody SubjectsDTO subjectsDTO ) {	
		return iSubjectsService.save(subjectsDTO);
	}
	@PutMapping("/api/subject")
	public SubjectsDTO updateQuestion(@RequestBody SubjectsDTO subjectsDTO ) {	
		return iSubjectsService.save(subjectsDTO);	
	}
	@DeleteMapping("/api/subject")
	public void deleteQuestion(@RequestBody int[] ids ) {	
		iSubjectsService.delete(ids);
	}
}
