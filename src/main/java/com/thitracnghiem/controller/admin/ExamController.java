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

import com.thitracnghiem.dto.ExamDTO;
import com.thitracnghiem.dto.MyUser;
import com.thitracnghiem.service.IExamService;
import com.thitracnghiem.service.ISubjectsService;
import com.thitracnghiem.util.MessageUtil;
import com.thitracnghiem.util.SecurityUtils;

@Controller(value = "examControllerofAdmin")
public class ExamController {
	@Autowired
	private IExamService iExamService;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private ISubjectsService iSubjectsService;

	@RequestMapping(value = "/admin/exam/update", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/exam/edit");
		ExamDTO model = new ExamDTO();
		if (id != null) {
			 model = iExamService.findById(id);
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

	@RequestMapping(value = "/admin/exam/list", method = RequestMethod.GET)
	public ModelAndView showList(@ModelAttribute("model") ExamDTO examDTO, @RequestParam("page") int page,
			@RequestParam("limit") int limit, @RequestParam(value = "keyword", required = false) String keyword,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/exam/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		MyUser myUser = SecurityUtils.getPrincipal();
		/* mav.addObject("keyword", keyword); */
		examDTO.setPage(page);
		examDTO.setLimit(limit);
		if (keyword != null) {
			/*
			 * examDTO.setTotalItem(iQuestionService.getTotalItem(keyword));
			 * examDTO.setTotalPage((int) Math.ceil((double) questionDTO.getTotalItem() /
			 * questionDTO.getLimit()));
			 * examDTO.setListResult(iQuestionService.search(keyword, pageable));
			 */
		} else {
			examDTO.setTotalItem(iExamService.getTotalItemFindByTeacher(myUser.getUsername()));
			examDTO.setTotalPage((int) Math.ceil((double) examDTO.getTotalItem() / examDTO.getLimit()));
			examDTO.setListResult(iExamService.findByTeacher(myUser.getUsername(), pageable));

		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", examDTO);
		return mav;
	}

	@RequestMapping(value = "/admin/examAll/list", method = RequestMethod.GET)
	public ModelAndView showAll(@ModelAttribute("model") ExamDTO examDTO, @RequestParam("page") int page,
			@RequestParam("limit") int limit, @RequestParam(value = "keyword", required = false) String keyword) {
		ModelAndView mav = new ModelAndView("/admin/exam/listAll");
		Pageable pageable = new PageRequest(page - 1, limit);
		mav.addObject("keyword", keyword);
		examDTO.setPage(page);
		examDTO.setLimit(limit);
		if (keyword != null) {
			examDTO.setTotalItem(iExamService.getTotalItem(keyword));
			examDTO.setTotalPage((int) Math.ceil((double) examDTO.getTotalItem() / examDTO.getLimit()));
			examDTO.setListResult(iExamService.search(keyword, pageable));

		} else {
			examDTO.setTotalItem(iExamService.getTotalItem());
			examDTO.setTotalPage((int) Math.ceil((double) examDTO.getTotalItem() / examDTO.getLimit()));
			examDTO.setListResult(iExamService.findAll(pageable));

		}
		
		mav.addObject("model", examDTO);
		return mav;
	}

}
