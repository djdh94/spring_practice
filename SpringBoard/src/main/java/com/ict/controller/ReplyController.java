package com.ict.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.ReplyVO;
import com.ict.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	// consumes는 이 메서드의 파라미터를 넘겨줄때 어떤 형식으로 넘겨줄지
	// 를 설정하는데 기본적으로 rest방식에서는 json을 사용합니다.
	// produeces는 입력받은 데이터를 토대로 로직을 실행한 다음
	// 사용자에게 결과를 보여줄 데이터의 형식을 나타냅니다.
	// jackson-databind 추가해야 작동
	@PostMapping(value="", consumes ="application/json",produces = {MediaType.TEXT_PLAIN_VALUE})
	// produces에 TEXT_PLAIN_VALUE를 줬으므로 결과코드와 문자열을 넘김
	public ResponseEntity<String> register(
			// rest 컨트롤러에서 받는 파라미터 앞에
			// @RequestBody 어노테이션이 붙어야
			// consumes와 연결됨
			@RequestBody ReplyVO vo){
		//깡통 ENTITY를 먼저 생성
		ResponseEntity<String> entity = null;
		try {
			service.addReply(vo);
			entity = new ResponseEntity<String>(
					"SUCCESS",HttpStatus.OK);
		}catch(Exception e) {
			// catch로 넘어왔다라는건 글쓰기 로직에 문제가 생긴 상황
			entity=new ResponseEntity<String>(
					e.getMessage(),HttpStatus.BAD_REQUEST);					
		}
		//위의 try블럭이나 catch블럭에서 얻어온 entity변수 리턴
		return entity;
	}
		@GetMapping(value="/all/{bno}", 
			
			// 단일 숫자데이터 bno만 넣기도하고
			// PathVariable 어노테이션으로 이미 입력데이터가
			// 명시되었으므로 consumes는 따로 주지 않아도 됩니다.
			// produces는 댓글 목록이 XML로도, JSON으로도 표현될수있또록
			// 아래와 같이 2개를 모두 얹습니다.
			// jsckson-dataformat-xml을 추가해야 xml도 작동합니다.
			produces= {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	
		public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Long bno){
			
				ResponseEntity<List<ReplyVO>> entity = null;
			
			try {
				entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
			}catch(Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return entity;
		}
	
	
}