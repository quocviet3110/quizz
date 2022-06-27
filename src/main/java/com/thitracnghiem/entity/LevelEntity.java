package com.thitracnghiem.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="capdo")
public class LevelEntity extends BaseEntity {
	
	@Column(name="TENCD")
	private String name;
	
	@OneToMany(mappedBy = "levels" ,fetch = FetchType.EAGER)
	private Collection<SubjectsEntity> subjects;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
