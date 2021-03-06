package com.thitracnghiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.ResultEntity;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Integer>{

}
