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

import com.thitracnghiem.dto.SubjectsDTO;
import com.thitracnghiem.service.ILevelService;
import com.thitracnghiem.service.ISubjectsService;
import com.thitracnghiem.util.MessageUtil;

@Controller
public class SubjectController {
	@Autowired
	private ISubjectsService iSubjectsService;

	@Autowired
	private ILevelService iLevelService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/admin/subject/list", method = RequestMethod.GET)
	public ModelAndView showList(@ModelAttribute("model") SubjectsDTO subjectsDTO, @RequestParam("page") int page,
			@RequestParam("limit") int limit,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/subject/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		subjectsDTO.setPage(page);
		subjectsDTO.setLimit(limit);
		subjectsDTO.setTotalItem(iSubjectsService.getTotalItem());
		subjectsDTO.setTotalPage((int) Math.ceil((double) iSubjectsService.getTotalItem() / subjectsDTO.getLimit()));
		subjectsDTO.setListResult(iSubjectsService.findAll(pageable));
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", subjectsDTO);
		
		return mav;
	}

	@RequestMapping(value = "/admin/subject/update", method = RequestMethod.GET)
	public ModelAndView editSubject(@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/subject/edit");
		SubjectsDTO model = new SubjectsDTO();
		if (id != null) {
			model = iSubjectsService.findById(id);
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message =messageUtil.getMessage(request.getParameter("message"));
			 mav.addObject("message", message.get("message"));
			 mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("levels", iLevelService.findAll());
		mav.addObject("model", model);
		return mav;
	}

}
