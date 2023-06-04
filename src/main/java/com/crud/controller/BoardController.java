package com.crud.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.dto.BoardDTO;
import com.crud.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;

	@GetMapping("/save")
	public String saveForm() {
		return "save";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute BoardDTO boardDTO) {
		System.out.println("boardDTO = " + boardDTO);
		boardService.save(boardDTO);

		return "index";
	}

	@GetMapping("/")
	public String findAll(Model model) {
		// DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
		List<BoardDTO> boardDTOList = boardService.findAll();

		model.addAttribute("boardList", boardDTOList);
		
		return "list";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Long id, Model model) {
		/*
		 *  해당 게시글의 조회수를 하나 올리고
		 *  게시글 데이터를 가져와서 detail.html에 출력
		 *  */
		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "detail";
	}
	
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("boardUpdate", boardDTO);
		return "update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		BoardDTO board = boardService.update(boardDTO);
		model.addAttribute("board", board);
		return "detail";
//		return "redirect:/board/" + boardDTO.getId();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		boardService.delete(id);
		return "redirect:/board/";
	}
	
	
	// /board/paging?page=1
	@GetMapping("/paging")
	public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//		pageable.getPageNumber();
		Page<BoardDTO> boardList = boardService.paging(pageable);
		int blockLimit = 3;
		
		// page 갯수 20개 
		// 현재 사용자가 3페이지
		// 1 2 3 4 5 
		// 현재 사용자가 7페이지
		// 7 8 9 
		// 보여지는 페이지 갯수 3개
		
		
	}
}
