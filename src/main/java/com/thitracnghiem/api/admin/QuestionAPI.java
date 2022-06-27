package com.thitracnghiem.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thitracnghiem.dto.QuestionDTO;
import com.thitracnghiem.service.IQuestionService;
@CrossOrigin
@RestController(value = "questionAPIofAdmin")
public class QuestionAPI {
	 @Autowired
	 private IQuestionService iQuestionService;

	@PostMapping("/api/question")
	public QuestionDTO createdQuestion(@RequestBody QuestionDTO questionDTO ) {	
		return iQuestionService.save(questionDTO);
	}
	@PutMapping("/api/question")
	public QuestionDTO updateQuestion(@RequestBody QuestionDTO questionDTO ) {	
		return iQuestionService.save(questionDTO);	
	}
	@DeleteMapping("/api/question")
	public int deleteQuestion(@RequestBody int[] ids ) {	
		int check = iQuestionService.delete(ids);
		if(check==0) {
			return 0;
		}else {
			return 1;
		}
	}
}
