package com.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void updateHits(Long id) {
		// TODO Auto-generated method stub
		boardRepository.updateHits(id);
	}

	public BoardDTO findById(Long id) {
		// TODO Auto-generated method stub
		Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
		if(optionalBoardEntity.isPresent()) {
			BoardEntity boardEntity = optionalBoardEntity.get();
			BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
			return boardDTO;
		} else {
			return null;
		}
		
	}

	public BoardDTO update(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
		boardRepository.save(boardEntity);
		return findById(boardDTO.getId());
	}

}
