package com.crud.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.crud.entity.BoardEntity;
import com.crud.entity.BoardFileEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO(Data Tranfer Object), VO, Bean,Entity
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
	private Long id;
	private String boardWriter;
	private String boardPass;
	private String boardTitle;
	private String boardContents;
	private int boardHits;
	private LocalDateTime boardCreatedTime;
	private LocalDateTime boardUpdatedTime;

	// save.html -> Controller 파일 담는 용도
	private List<MultipartFile> boardFile;
	// 원본 파일 이름
	private List<String> originalFileName;
	// 서버 저장용 파일 이름
	private List<String> storedFileName;
	// 파일 첨부 여부(첨부 1, 미첨부0)
	private int fileAttached;

	public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setId(boardEntity.getId());
		boardDTO.setBoardWriter(boardEntity.getBoardWriter());
		boardDTO.setBoardPass(boardEntity.getBoardPass());
		boardDTO.setBoardTitle(boardEntity.getBoardTitle());
		boardDTO.setBoardContents(boardEntity.getBoardContents());
		boardDTO.setBoardHits(boardEntity.getBoardHits());
		boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
		boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
		if (boardEntity.getFileAttached() == 0) {
			boardDTO.setFileAttached(boardEntity.getFileAttached()); // 0
		} else {
			List<String> originalFileNameList = new ArrayList<>();
			List<String> storedFileNameList = new ArrayList<>();

			boardDTO.setFileAttached(boardEntity.getFileAttached()); // 1
			// 파일 이름을 가져 가야함.
			// originalFileName, storedFileName : board_file_table(BoardFileEntity)
			// join
			// select * from board_table b, board_file_table bf where b.id=bf.board_id
			// and where b.id=?
			for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
				originalFileNameList.add(boardFileEntity.getOriginalFileName());
				storedFileNameList.add(boardFileEntity.getStoredFileName());
			}
			boardDTO.setOriginalFileName(originalFileNameList);
			boardDTO.setStoredFileName(storedFileNameList);
		}

		return boardDTO;
	}

	public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
		super();
		this.id = id;
		this.boardWriter = boardWriter;
		this.boardTitle = boardTitle;
		this.boardHits = boardHits;
		this.boardCreatedTime = boardCreatedTime;
	}

}
