package com.thitracnghiem.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thitracnghiem.dto.LevelDTO;
import com.thitracnghiem.service.ILevelService;
@CrossOrigin
@RestController(value = "levelAPIofAdmin")
public class LevelAPI {
	@Autowired
	 private ILevelService iLevelService;
	
	@PostMapping("/api/level")
	public LevelDTO createdLevel(@RequestBody LevelDTO levelDTO ) {	
		return iLevelService.save(levelDTO);
	}
	@PutMapping("/api/level")
	public LevelDTO updateLevel(@RequestBody LevelDTO levelDTO ) {	
		return iLevelService.save(levelDTO);	
	}
	@DeleteMapping("/api/level")
	public void deleteLevel(@RequestBody int[] ids ) {	
		iLevelService.delete(ids);
	}
}
