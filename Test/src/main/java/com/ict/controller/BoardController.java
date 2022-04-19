package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ict.domain.BoardVO;
import com.ict.mapper.BoardMapper;

@Controller
public class BoardController {

	@Autowired 
	private BoardMapper boardMapper;
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
	List<BoardVO> board= boardMapper.getList();
	model.addAttribute("board",board);
		return "boardList";
	}
}
