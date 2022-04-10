package com.ict.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	// 주소뒤에 ?bno=번호 형식으로 적힌 번호 글만 조회합니다.
	@GetMapping("/boardDetail/{bno}")  
	public String boardDetail(@PathVariable long bno,Model model) {
		BoardVO board = boardMapper.select(bno);
		model.addAttribute("board",board);
		return "boardDetail";
	}
	
	// insert 페이지를 위한 폼으로 연결되는 컨트롤러를 만들기
	// @get방식으로 boardInsert 주소로 접속시 폼페이지로 연결됩니다.
	// 폼페이지의 이름은 boardForm.jsp입니다.
	
	@GetMapping("/boardInsert")
	public String getInsert () {
		return "boardForm";
	}
	
	// /boardInsert인데 post방식을 처리하는 메서드를 새로 만들어주세요.
	// BoardVO를 입력받도록 해 주시면 실제로는 BoardVO의 멤버변수명으로 들어오는 자료를 입력받습니다.
	// 입력받은 BoardVO를 토대로 mapper쪽의 insert 메서드를 실행하고
	// 리다이렉트는 return "redirect:/목적지주소" 형식으로 리턴구문을 작성하면 됩니다.
	// boardList로 돌려보내기
	@PostMapping("/boardInsert")
	public String boardInsert(BoardVO board) {
		boardMapper.insert(board);
		return "redirect:/boardList";
	}
	
	// 글삭제 로직은 Post방식으로 진행합니다.
	// /boardDelete 주소로 처리하고
	// bno를 받아서 해당 글을 삭제합니다
	// 글 삭제 버튼은 detail페이지 하단에 form으로 만들어서 bno를 hidden으로 전달하는
	// submit 버튼을 생성해서 처리하게 해주세요
	// 삭제 수행 후 boardList로 리다이렉트하기
	
	@PostMapping("/boardDelete")
	public String boardDelete(long bno) {
		// 삭제 로직 실행
		boardMapper.delete(bno);
		// 리턴으로 리스트페이지 복귀
		return "redirect:/boardList";
	}
	
	// /boardUpdate 를 get방식으로 접속하는 form 연결 메서드를 만들기
	// update 로직은 이미 데이터가 입력이 되어 있어야 합니다.
	// 따라서 내가 수정하고자 하는 글의 정보를 VO로 받아온 다음
	// 폼 페이지에 포워딩해서 기입해놔야함
	// 폼페이지 이름은 BoardUpdateForm.jsp입니다.
	
	@PostMapping("/boardUpdateForm")
	public String boardUpdate(long bno,Model model) {
		BoardVO board = boardMapper.select(bno);
		model.addAttribute("board",board);
		return "BoardUpdateForm";
	}
	@PostMapping("/boardUpdate")
	public String boardUpdate() {
		return "redirect:/boardList";
	}
	
	
}