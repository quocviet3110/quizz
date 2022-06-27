package com.thitracnghiem.api.admin;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.thitracnghiem.dto.TeacherDTO;
import com.thitracnghiem.service.ITeacherService;
@CrossOrigin
@RestController(value = "teacherAPIofAdmin")
public class TeacherAPI {
	@Autowired
	private ITeacherService iTeacherService;
	@Autowired
	private Cloudinary cloudinary;

	@PostMapping("/api/teacher")
	public TeacherDTO createdTeacher(@RequestBody TeacherDTO teacherDTO) {

		try {
			Map r = this.cloudinary.uploader().upload(teacherDTO.getAvatar().getBytes(),
					ObjectUtils.asMap("resource_type", "auto"));
			teacherDTO.setImage((String) r.get("secure_url"));
		} catch (IOException e) {
		}

		return iTeacherService.save(teacherDTO);
	}

	@PutMapping("/api/teacher")
	public TeacherDTO updateTeacher(@RequestBody TeacherDTO teacherDTO) {
		/*
		 * try { Map r =
		 * this.cloudinary.uploader().upload(teacherDTO.getAvatar().getBytes(),
		 * ObjectUtils.asMap( "resource_type","auto")); teacherDTO.setImage((String)
		 * r.get("secure_url")); System.out.print("Thành công"+ teacherDTO.getImage());
		 * } catch (IOException e) { System.out.print("Không tìm thấy file"); }
		 */
		return iTeacherService.save(teacherDTO);
	}
}
