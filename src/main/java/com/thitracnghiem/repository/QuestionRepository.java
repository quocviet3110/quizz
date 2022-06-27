package com.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
	@Query("SELECT q FROM QuestionEntity q WHERE q.ques_content LIKE %?1%" + " OR q.a_content LIKE %?1%"
			+ " OR q.b_content LIKE %?1%" + " OR q.c_content LIKE %?1%" + " OR q.d_content LIKE %?1%")
	public List<QuestionEntity> search(String keyword, Pageable pageable);

	@Query("SELECT count(*) FROM QuestionEntity q WHERE q.ques_content LIKE %?1%" + " OR q.a_content LIKE %?1%"
			+ " OR q.b_content LIKE %?1%" + " OR q.c_content LIKE %?1%" + " OR q.d_content LIKE %?1%")
	public int count(String keyword);

	@Query("SELECT q FROM QuestionEntity q INNER JOIN FETCH q.teacherQuestion t WHERE t.id=?1")
	public List<QuestionEntity> findAllByTeacher(int idTeacher, Pageable pageable);

	@Query("SELECT count(q) FROM QuestionEntity q INNER JOIN q.teacherQuestion t WHERE t.id=?1")
	public int count(int idTeacher);

	@Query("SELECT q FROM QuestionEntity q INNER JOIN FETCH q.detailExam d INNER JOIN FETCH d.exams e WHERE e.id=?1")
	public List<QuestionEntity> findAllByExam(int idExam);

	@Query("SELECT q FROM QuestionEntity q INNER JOIN FETCH q.subjects s WHERE s.id=?1")
	public List<QuestionEntity> findAllBySubject(int idSubject);
	
	
	@Query("SELECT q FROM QuestionEntity q INNER JOIN FETCH q.teacherQuestion  t WHERE (q.ques_content LIKE %?1%" + " OR q.a_content LIKE %?1%"
			+" OR q.b_content LIKE %?1%" + " OR q.c_content LIKE %?1%" + " OR q.d_content LIKE %?1%) AND t.id=?2")
	public List<QuestionEntity> findAllByTeacherAndKeyword(String keyword,int idTeacher, Pageable pageable);
	
	@Query("SELECT count(q) FROM QuestionEntity q INNER JOIN  q.teacherQuestion  t WHERE (q.ques_content LIKE %?1%" + " OR q.a_content LIKE %?1%"
			+" OR q.b_content LIKE %?1%" + " OR q.c_content LIKE %?1%" + " OR q.d_content LIKE %?1%) AND t.id=?2")
	public int countByTeacherAndKeyword(String keyword,int idTeacher);
	
	@Query("SELECT q FROM QuestionEntity q INNER JOIN FETCH q.subjects s WHERE (q.ques_content LIKE %?1%" +" OR q.a_content LIKE %?1%"
			+ " OR q.b_content LIKE %?1%" + " OR q.c_content LIKE %?1%" + " OR q.d_content LIKE %?1%) AND s.id=?2")
	public List<QuestionEntity> searchBySubject(String keywword,int idSubject);
}
