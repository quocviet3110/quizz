package com.thitracnghiem.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thitracnghiem.dto.ExamDTO;
import com.thitracnghiem.dto.QuestionDTO;
import com.thitracnghiem.service.IExamService;
import com.thitracnghiem.service.IQuestionService;

@Controller(value = "examControllerofWeb")
public class ExamController {
	@Autowired
	private IQuestionService iQuestionService;
	
	@Autowired
	private IExamService iExamService;
	@RequestMapping(value = "/web/exam/listQuestion", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam(value = "id") Integer id) {
		ModelAndView mav = new ModelAndView("web/exam/exam");
		QuestionDTO questionDTO = new QuestionDTO();
		ExamDTO examDTO = iExamService.findById(id);
		questionDTO.setListResult(iQuestionService.findByExam(id));
		List<String> trueAnswer = new ArrayList<String>();
		for(QuestionDTO dto:questionDTO.getListResult()) {
			trueAnswer.add("'"+dto.getAnswer()+"'");
		}
		mav.addObject("questionDTO", questionDTO);
		mav.addObject("exam", examDTO);
		mav.addObject("trueAnswer", trueAnswer);
		return mav;
	}
}
