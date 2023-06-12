package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long>{
	

}
