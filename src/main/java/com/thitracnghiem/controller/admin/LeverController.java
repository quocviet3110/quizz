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

import com.thitracnghiem.dto.LevelDTO;
import com.thitracnghiem.service.ILevelService;
import com.thitracnghiem.util.MessageUtil;
@Controller
public class LeverController {
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private ILevelService iLevelService;
	@RequestMapping(value = "/admin/level/list", method = RequestMethod.GET)
	public ModelAndView showList(@ModelAttribute("model") LevelDTO levelDTO, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		ModelAndView mav = new ModelAndView("admin/level/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		levelDTO.setPage(page);
		levelDTO.setLimit(limit);
		levelDTO.setTotalItem(iLevelService.getTotalItem());
		levelDTO.setTotalPage((int) Math.ceil((double) iLevelService.getTotalItem() / levelDTO.getLimit()));
		levelDTO.setListResult(iLevelService.findAll(pageable));
		mav.addObject("model", levelDTO);
		return mav;
	}

	@RequestMapping(value = "/admin/level/update", method = RequestMethod.GET)
	public ModelAndView editSubject(@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/level/edit");
		LevelDTO model = new LevelDTO();
		if (id != null) {
			model = iLevelService.findById(id);
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message =messageUtil.getMessage(request.getParameter("message"));
			 mav.addObject("message", message.get("message"));
			 mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}

}
