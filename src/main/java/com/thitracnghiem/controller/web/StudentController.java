package com.thitracnghiem.controller.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.thitracnghiem.dto.MarkDTO;
import com.thitracnghiem.dto.MyUser;
import com.thitracnghiem.dto.StudentDTO;
import com.thitracnghiem.service.IMarkService;
import com.thitracnghiem.service.IStudentService;
import com.thitracnghiem.util.MessageUtil;
import com.thitracnghiem.util.SecurityUtils;


@Controller(value = "StudentControllerOfWeb")
public class StudentController {
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private IStudentService iStudentService;
	
	@Autowired
	private IMarkService iMarkService;
	
	@RequestMapping(value = "/web/infor", method = RequestMethod.GET)
	public ModelAndView editNew( HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/infor");
		MyUser myUser = SecurityUtils.getPrincipal();
		StudentDTO dto = iStudentService.findOneByUsername(myUser.getUsername());
		
		  MarkDTO markDTO = new MarkDTO();
		  markDTO.setListResult(iMarkService.findAllByStudent(myUser.getUsername()));
		 
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("student", dto);
		 mav.addObject("mark", markDTO.getListResult());
		return mav;
	}
}
