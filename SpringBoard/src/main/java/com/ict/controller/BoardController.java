package com.ict.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.domain.BoardVO;

import com.ict.domain.PageMaker;
import com.ict.domain.SearchCriteria;

import com.ict.service.BoardService;

import lombok.extern.log4j.Log4j;



// 컨트롤러가 컨트롤러 기능을 할 수 있도록 처리하기
@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

	// 컨트롤러는 Service만 호출하도록 구조를 바꿉니다.
	// Service를 BoardController 내부에서 쓸 수 있도록 선언/주입하기
	
	@Autowired
	private BoardService service;
	
	
	// 전체 회원을 보려면, 회원목록을 들고오는 메서드를 실행해야 하고
	// 그 메서드를 보유하고 있는 클래스를 선언하고 주입해줘야함
	// db접근시 사용하는 BoardMapper를 선언하고 주입해야함
	// 참고) BoardMapperTests.java
	
	
	// 전체 글 목록을 볼수있는 페이지인 boardList.jsp로 연결되는
	// /boardList 주소를 get방식으로 선언하기
	// 메서드 내부에서는 boardMapper의 .getAllList를 호출해 그결과를 바인딩하기
	
	@GetMapping("/boardList")
	// @RequestParam(name="사용할변수명",defaultValue="지정하고싶은기본값") 변수 왼쪽에 저렇게 붙여주면 처리완료.
	// @PathVariable의 경우 defaultValue를 직접 줄 수 없으나 ,required_false를 이용해 필수입력을 안받게 처리한 후
	// 컨트롤러 내부에서 디폴트값을 입력해줄 수 있다.
	// 기본형 자료는 null을 저장할 수 없기 때문에 wrapper class를 이용해 Long을 선언합니다.
	public String getboardList(SearchCriteria cri,Model model) {
		//if(pageNum==null) {
		//	pageNum=1L; // Long형은 숫자 뒤에 L을 붙여야 대입됩니다
		//}
		// model.addAttribute("바인딩이름",바인딩자료);
		List<BoardVO> List =service.getList(cri);
		model.addAttribute("List",List);
		
		//버튼 처리를 위해 추가로 페이지메이커 생성 및 세팅
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri); // cri 입력
		int countPage=service.countPageNum(cri); //131대신 실제로 db내 글 개수를 받아옴
		pageMaker.setTotalBoard(countPage); // calcData()호출도 되면서 순식간에 prev,next,startPage,endPage 세팅
		model.addAttribute("pageMaker",pageMaker);
		
		return "/board/boardList";
	}
	
	// 글 하나만 조회할 수 있는 디테일 페이지인 boardDetail.jsp로 연결되는
	// boardDetail 주소를 get 방식으로 선언
	// 주소뒤에 ?bno=번호 형식으로 적힌 번호 글만 조회합니다.
	@GetMapping("/boardDetail/{bno}")  
	public String boardDetail(@PathVariable long bno,Model model) {
		BoardVO board = service.select(bno);
		model.addAttribute("board",board);
		return "/board/boardDetail";
	}
	
	// insert 페이지를 위한 폼으로 연결되는 컨트롤러를 만들기
	// @get방식으로 boardInsert 주소로 접속시 폼페이지로 연결됩니다.
	// 폼페이지의 이름은 boardForm.jsp입니다.
	
	@GetMapping("/boardInsert")
	public String getInsert(){
		return "/board/boardForm";
	}
	
	// /boardInsert인데 post방식을 처리하는 메서드를 새로 만들어주세요.
	// BoardVO를 입력받도록 해 주시면 실제로는 BoardVO의 멤버변수명으로 들어오는 자료를 입력받습니다.
	// 입력받은 BoardVO를 토대로 mapper쪽의 insert 메서드를 실행하고
	// 리다이렉트는 return "redirect:/목적지주소" 형식으로 리턴구문을 작성하면 됩니다.
	// boardList로 돌려보내기
	@PostMapping("/boardInsert")
	public String boardInsert(BoardVO board) {
		service.insert(board);
		return "redirect:/board/boardList";
	}
	
	// 글삭제 로직은 Post방식으로 진행합니다.
	// /boardDelete 주소로 처리하고
	// bno를 받아서 해당 글을 삭제합니다
	// 글 삭제 버튼은 detail페이지 하단에 form으로 만들어서 bno를 hidden으로 전달하는
	// submit 버튼을 생성해서 처리하게 해주세요
	// 삭제 수행 후 boardList로 리다이렉트하기
	
	@PostMapping("/boardDelete")
	public String boardDelete(long bno,SearchCriteria cri,RedirectAttributes rttr) {
		// 삭제 로직 실행
		service.delete(bno);
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		// 리턴으로 리스트페이지 복귀
		return "redirect:/board/boardList";
	}
	
	
	
	
	// /boardUpdate 를 get방식으로 접속하는 form 연결 메서드를 만들기
	// update 로직은 이미 데이터가 입력이 되어 있어야 합니다.
	// 따라서 내가 수정하고자 하는 글의 정보를 VO로 받아온 다음
	// 폼 페이지에 포워딩해서 기입해놔야함
	// 폼페이지 이름은 BoardUpdateForm.jsp입니다.
	
	@PostMapping("/boardUpdateForm")
	public String boardUpdate(long bno,Model model) {
		 
		model.addAttribute("board",service.select(bno));
		return "/board/BoardUpdateForm";
	}
	
	
	@PostMapping("/boardUpdate")			   // keyword,searchType,pageNum을 받기위해 선언
	public String boardUpdate(BoardVO board , SearchCriteria cri,RedirectAttributes rttr) {
		// SearchCriteria 가 제대로 받아오는지 체크
		log.info(board);
		log.info(cri.getKeyword());
		log.info(cri.getPageNum());
		log.info(cri.getSearchType());
		// update 호출
		service.update(board);
		
		// 리다이렉트시 주소창 뒤에 파라미터 쿼리스트링 형식으로 붙이기
		// rttr.addAttribute("파라미터명","전달자료");
		// 는 호출되면 redirect 주소 뒤에 파라미터를 붙여줍니다.
		// rttr.addFlashAttribute()는 넘어간 페이지에서 파라미터를
		// 쓸 수 있도록 전달하는것으로 둘의 역할이 다르니 주의
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
	
		return "redirect:/board/boardDetail/"+board.getBno();
	}
	
	
	
	
}
