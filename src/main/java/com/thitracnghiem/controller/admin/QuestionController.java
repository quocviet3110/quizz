package com.thitracnghiem.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thitracnghiem.dto.MyUser;
import com.thitracnghiem.dto.QuestionDTO;
import com.thitracnghiem.service.IQuestionService;
import com.thitracnghiem.service.ISubjectsService;
import com.thitracnghiem.util.MessageUtil;
import com.thitracnghiem.util.SecurityUtils;

@Controller
public class QuestionController {
	@Autowired
	private IQuestionService iQuestionService;

	@Autowired
	private ISubjectsService iSubjectsService;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/admin/question/list", method = RequestMethod.GET)
	public ModelAndView showList(@ModelAttribute("model") QuestionDTO questionDTO, @RequestParam("page") int page,
			@RequestParam("limit") int limit, @RequestParam(value = "keyword", required = false) String keyword) {
		ModelAndView mav = new ModelAndView("/admin/question/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		
		questionDTO.setPage(page);
		questionDTO.setLimit(limit);
		if (keyword != "") {
			mav.addObject("keyword", keyword);
			questionDTO.setTotalItem(iQuestionService.getTotalItem(keyword));
			questionDTO.setTotalPage((int) Math.ceil((double) questionDTO.getTotalItem() / questionDTO.getLimit()));
			questionDTO.setListResult(iQuestionService.search(keyword, pageable));
		} else {
			questionDTO.setTotalItem(iQuestionService.getTotalItem());
			questionDTO.setTotalPage((int) Math.ceil((double) questionDTO.getTotalItem() / questionDTO.getLimit()));
			questionDTO.setListResult(iQuestionService.findAll(pageable));

		}
		mav.addObject("model", questionDTO);
		return mav;
	}

	@RequestMapping(value = "/admin/questions/update", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/question/edit");
		QuestionDTO model = new QuestionDTO();
		if (id != null) {
			model = iQuestionService.findById(id);
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("subjects", iSubjectsService.findAll());
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value = "/admin/myQuestion/list", method = RequestMethod.GET)
	public ModelAndView showMyList(@ModelAttribute("model") QuestionDTO questionDTO, @RequestParam("page") int page,
			@RequestParam("limit") int limit, @RequestParam(value = "keyword", required = false) String keyword,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/question/personal");
		MyUser myUser = SecurityUtils.getPrincipal();
		Pageable pageable = new PageRequest(page - 1, limit);
		questionDTO.setPage(page);
		questionDTO.setLimit(limit);
		mav.addObject("keyword", keyword);
		if (keyword != null) {
			questionDTO.setTotalItem(iQuestionService.getTotalItemByTeacher(keyword, myUser.getUsername()));
			questionDTO.setTotalPage((int) Math.ceil((double) questionDTO.getTotalItem() / questionDTO.getLimit()));
			questionDTO.setListResult(iQuestionService.searchByTeacher(keyword, myUser.getUsername(), pageable));
		} else {
			questionDTO.setTotalItem(iQuestionService.getTotalItemFindByTeacher(myUser.getUsername()));
			questionDTO.setTotalPage((int) Math.ceil((double) questionDTO.getTotalItem() / questionDTO.getLimit()));
			questionDTO.setListResult(iQuestionService.findByTeacher(myUser.getUsername(), pageable));

		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}

		mav.addObject("model", questionDTO);
		return mav;
	}
}
