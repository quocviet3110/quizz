package com.thitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.LevelEntity;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Integer> {
	LevelEntity findOneByName(String name);

}
