package com.thitracnghiem.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.thitracnghiem.util.MessageUtil;

@Controller
public class DetailExamController {
	@Autowired
	private IQuestionService iQuestionService;
	@Autowired
	private IExamService iExamService;
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/admin/detailExam/update", method = RequestMethod.GET)
	public ModelAndView editExam(@RequestParam(value = "id") Integer id, HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword) {
		ModelAndView mav = new ModelAndView("/admin/detailExam/edit");
		ExamDTO examDto = iExamService.findById(id);
		QuestionDTO questionDTO = new QuestionDTO();
		
		if (keyword != null) {
			mav.addObject("keyword", keyword);
			questionDTO.setListResult(iQuestionService.searchBySubject(keyword,examDto.getNameSubject()));
		} else {
			questionDTO.setListResult(iQuestionService.findBySubject(examDto.getNameSubject()));

		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("exam", examDto);
		mav.addObject("model", questionDTO);
		return mav;
	}
}
