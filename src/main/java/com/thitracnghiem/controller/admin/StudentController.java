package com.thitracnghiem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thitracnghiem.dto.StudentDTO;
import com.thitracnghiem.service.IStudentService;

@Controller(value = "StudentControllerOfAdmin")
public class StudentController {
	@Autowired
	private IStudentService iStudentService;

	@RequestMapping(value = "/admin/student/list", method = RequestMethod.GET)
	public ModelAndView showList(@ModelAttribute("model") StudentDTO studentDTO, @RequestParam("page") int page,
			@RequestParam("limit") int limit, @RequestParam(value = "keyword", required = false) String keyword) {
		ModelAndView mav = new ModelAndView("/admin/student/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		mav.addObject("keyword", keyword);
		studentDTO.setPage(page);
		studentDTO.setLimit(limit);
		if (keyword != null) {
			studentDTO.setTotalItem(iStudentService.getTotalItem(keyword));
			studentDTO.setTotalPage((int) Math.ceil((double) studentDTO.getTotalItem() / studentDTO.getLimit()));
			studentDTO.setListResult(iStudentService.search(keyword, pageable));
		} else {
			studentDTO.setTotalItem(iStudentService.getTotalItem());
			studentDTO.setTotalPage((int) Math.ceil((double) studentDTO.getTotalItem() / studentDTO.getLimit()));
			studentDTO.setListResult(iStudentService.findAll(pageable));

		}
		mav.addObject("model", studentDTO);
		return mav;
	}
}
