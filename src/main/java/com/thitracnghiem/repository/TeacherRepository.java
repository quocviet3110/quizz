package com.thitracnghiem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.TeacherEntity;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
	
	 @Query("SELECT t FROM TeacherEntity t INNER JOIN t.accountTeacher ac WHERE ac.username= ?1")
	 TeacherEntity findOneByUsername(String username);
}
