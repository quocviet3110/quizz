package com.thitracnghiem.controller.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.thitracnghiem.dto.StudentDTO;
import com.thitracnghiem.util.MessageUtil;

@Controller
public class SingupController {
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
	public ModelAndView signup(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login/signup");
		StudentDTO studentDTO = new StudentDTO();

		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}

		mav.addObject("student", studentDTO);
		return mav;
	}
}
