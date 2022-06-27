package com.thitracnghiem.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thitracnghiem.dto.ExamDTO;
import com.thitracnghiem.dto.SubjectsDTO;
import com.thitracnghiem.service.IExamService;
import com.thitracnghiem.service.ISubjectsService;
@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private IExamService iExamService;
	@Autowired
	private ISubjectsService iSubjectsService;
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}
	@RequestMapping(value = "/web/listExam", method = RequestMethod.GET)
	public ModelAndView loadExam(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView("web/exam/list");
		SubjectsDTO subjectsDTO = iSubjectsService.findById(id);
		ExamDTO dto = new ExamDTO();
		dto.setListResult(iExamService.findAllBySubject(id));
		mav.addObject("exam", dto);
		mav.addObject("subject", subjectsDTO);
		return mav;
	}
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public String login() {
		return "login/login";
	}
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
	
	
}
