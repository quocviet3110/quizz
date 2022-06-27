package com.thitracnghiem.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thitracnghiem.dto.AccountDTO;
import com.thitracnghiem.dto.MyUser;
import com.thitracnghiem.dto.TeacherDTO;
import com.thitracnghiem.service.IAccountService;
import com.thitracnghiem.service.ITeacherService;
import com.thitracnghiem.util.MessageUtil;
import com.thitracnghiem.util.SecurityUtils;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	@Autowired
	private ITeacherService iTeacherService;
	
	@Autowired
	private IAccountService iAccountService;
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String homePage() {
		return "admin/home";
	}

	@RequestMapping(value = "/admin/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/admin/home");
	}
	@RequestMapping(value = "/dang-nhap/updateAccount", method = RequestMethod.GET)
	public ModelAndView updateAccount(@RequestParam(value = "id") int id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login/update");
		MyUser myUser = SecurityUtils.getPrincipal();
		TeacherDTO dto = iTeacherService.findOneByUsername(myUser.getUsername());
		AccountDTO accountDTO = iAccountService.findOneByUsername(myUser.getUsername());
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("teacher", dto);
		mav.addObject("account", accountDTO);
		return mav;
	}
	
}
