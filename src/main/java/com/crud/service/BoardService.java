package com.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crud.dto.BoardDTO;
import com.crud.entity.BoardEntity;
import com.crud.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

// DTO -> Entity ( Entity Class)
// Entity -> DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
		boardRepository.save(boardEntity);

	}

	public List<BoardDTO> findAll() {
		List<BoardEntity> boardEntityList = boardRepository.findAll();
		List<BoardDTO> boardDTOList = new ArrayList<>();
		for (BoardEntity boardEntity : boardEntityList) {
			boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));

		}
		return boardDTOList;
	}

}
