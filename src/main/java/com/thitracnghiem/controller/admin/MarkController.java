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

import com.thitracnghiem.dto.MarkDTO;
import com.thitracnghiem.dto.MyUser;
import com.thitracnghiem.service.IMarkService;
import com.thitracnghiem.util.SecurityUtils;

@Controller()
public class MarkController {

	@Autowired
	private IMarkService iMarkService;

	@RequestMapping(value = "/admin/result/list", method = RequestMethod.GET)
	public ModelAndView showList(@ModelAttribute("model") MarkDTO markDTO, @RequestParam("page") int page,
			@RequestParam("limit") int limit, @RequestParam(value = "keyword", required = false) String keyword) {
		ModelAndView mav = new ModelAndView("/admin/mark/list");
		MyUser myUser = SecurityUtils.getPrincipal();

		Pageable pageable = new PageRequest(page - 1, limit);
		mav.addObject("keyword", keyword);
		markDTO.setPage(page);
		markDTO.setLimit(limit);

		if (keyword != null) {
			markDTO.setTotalItem(iMarkService.getTotalItem(keyword,myUser.getUsername()));
			markDTO.setTotalPage((int) Math.ceil((double) markDTO.getTotalItem() / markDTO.getLimit()));
			markDTO.setListResult(iMarkService.search(keyword,myUser.getUsername(), pageable));
		} else {

			markDTO.setTotalItem(iMarkService.getTotalItemByTeacher(myUser.getUsername()));
			markDTO.setTotalPage((int) Math.ceil((double) markDTO.getTotalItem() / markDTO.getLimit()));
			markDTO.setListResult(iMarkService.findAllByTeacher(myUser.getUsername(), pageable));
		}
		mav.addObject("model", markDTO);
		return mav;
	}

}
