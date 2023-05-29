package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

	
	
}
