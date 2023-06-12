package com.crud.entity;

import java.util.ArrayList;
import java.util.List;

import com.crud.dto.BoardDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	// 1or 0 파일 있으면 1, 아니면 0
	@Column
	private int fileAttached;
	
	@OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();
	
	
	
	

	public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setBoardWriter(boardDTO.getBoardWriter());
		boardEntity.setBoardPass(boardDTO.getBoardPass());
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContents(boardDTO.getBoardContents());
		boardEntity.setBoardHits(0);
		// 파일 없음.
		boardEntity.setFileAttached(0);
		return boardEntity;
	}

	public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setId(boardDTO.getId());
		boardEntity.setBoardWriter(boardDTO.getBoardWriter());
		boardEntity.setBoardPass(boardDTO.getBoardPass());
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContents(boardDTO.getBoardContents());
		boardEntity.setBoardHits(boardDTO.getBoardHits());
		return boardEntity;
	}

	public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setBoardWriter(boardDTO.getBoardWriter());
		boardEntity.setBoardPass(boardDTO.getBoardPass());
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContents(boardDTO.getBoardContents());
		boardEntity.setBoardHits(0);
		// 파일 있음.
		boardEntity.setFileAttached(1);
		return boardEntity;
	}

}
