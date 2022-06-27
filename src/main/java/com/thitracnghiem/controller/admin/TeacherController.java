package com.thitracnghiem.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.thitracnghiem.dto.MyUser;
import com.thitracnghiem.dto.TeacherDTO;
import com.thitracnghiem.service.ITeacherService;
import com.thitracnghiem.util.MessageUtil;
import com.thitracnghiem.util.SecurityUtils;

@Controller
public class TeacherController {
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private ITeacherService iTeacherService;
	
	@RequestMapping(value = "/admin-inforTeacher", method = RequestMethod.GET)
	public ModelAndView editNew( HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/infor");
		MyUser myUser = SecurityUtils.getPrincipal();
		TeacherDTO dto = iTeacherService.findOneByUsername(myUser.getUsername());
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", dto);
		return mav;
	}
}
