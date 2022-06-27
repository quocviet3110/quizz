package com.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{
	 @Query("SELECT s FROM StudentEntity s INNER JOIN s.accountStudent ac WHERE ac.username= ?1")
	 StudentEntity findOneByUsername(String username);
	 
	 @Query("SELECT s FROM StudentEntity s WHERE s.name LIKE %?1%" + " OR s.address LIKE %?1%"
				+ " OR s.email LIKE %?1%" + " OR s.phone LIKE %?1%" + " OR s.gender LIKE %?1%")
		public List<StudentEntity> search(String keyword, Pageable pageable);

		@Query("SELECT count(s) FROM StudentEntity s WHERE s.name LIKE %?1%" + " OR s.address LIKE %?1%"
				+ " OR s.email LIKE %?1%" + " OR s.phone LIKE %?1%" + " OR s.gender LIKE %?1%")
		public int count(String keyword);
}
