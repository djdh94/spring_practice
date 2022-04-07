package com.ict.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ict.domain.BoardVO;
import com.ict.mapper.BoardMapper;


// 컨트롤러가 컨트롤러 기능을 할 수 있도록 처리하기
@Controller
public class BoardController {

	// 전체 회원을 보려면, 회원목록을 들고오는 메서드를 실행해야 하고
	// 그 메서드를 보유하고 있는 클래스를 선언하고 주입해줘야함
	// db접근시 사용하는 BoardMapper를 선언하고 주입해야함
	// 참고) BoardMapperTests.java
	@Autowired
	private BoardMapper boardMapper;
	
	// 전체 글 목록을 볼수있는 페이지인 boardList.jsp로 연결되는
	// /boardList 주소를 get방식으로 선언하기
	// 메서드 내부에서는 boardMapper의 .getAllList를 호출해 그결과를 바인딩하기
	
	@GetMapping("/boardList")
	public String getboardList(Model model) {
		// model.addAttribute("바인딩이름",바인딩자료);
		List<BoardVO> List =boardMapper.getList();
		model.addAttribute("List",List);
		return "boardList";
	}
	
	// 글 하나만 조회할 수 있는 디테일 페이지인 boardDetail.jsp로 연결되는
	// boardDetail 주소를 get 방식으로 선언
	// 주소뒤에 ?bno=번호 형식으로 적힌 번호 글만 조회합니다. 넣어야되네?
	@GetMapping("/boardDetail/{List.bno}")  
	public String boardDetail(@PathVariable("List.bno") long bno,Model model) {
		BoardVO board = boardMapper.select(bno);
		model.addAttribute("board",board);
		return "boardDetail";
	}
}
