package com.thitracnghiem.dto;

import java.util.ArrayList;
import java.util.List;

public class LevelDTO extends AbstractDTO<LevelDTO>{
	
	private String name;
	
	private List<SubjectsDTO> listSub = new ArrayList();
	
	public List<SubjectsDTO> getListSub() {
		return listSub;
	}

	public void setListSub(List<SubjectsDTO> listSub) {
		this.listSub = listSub;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
