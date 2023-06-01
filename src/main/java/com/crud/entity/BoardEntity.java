package com.crud.entity;

import com.crud.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {

	@Id // pk 컬럼 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;

	// IS NOT NULL, 크기 20
	@Column(length = 20, nullable = false)
	private String boardWriter;

	// 크기 255, null 가능
	@Column
	private String boardPass;

	@Column
	private String boardTitle;

	@Column(length = 500)
	private String boardContents;

	@Column
	private int boardHits;

	public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setBoardWriter(boardDTO.getBoardWriter());
		boardEntity.setBoardPass(boardDTO.getBoardPass());
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContents(boardDTO.getBoardContents());
		boardEntity.setBoardHits(0);
		return boardEntity;

	}

}