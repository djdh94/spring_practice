package com.ict.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.TestService;

@RestController
public class TestRestController {
	//	이건 RestController 인데 주로 Ajax 나 클라이언트에서 헤더를 가진 요청이 들어올때 사용합니다.
	
	@Autowired TestService testService; 
	
	//	이렇게 주로 사용합니다! try 안에는 성공했을때 catch 안에는 실패, 오류 발생시 처리
	
	@PostMapping("/test")
	public ResponseEntity<?> testPost(@RequestBody Map<String, Object> map){
		ResponseEntity<?> entity = null;
		try {
			Map<String, Object> result = testService.testOneData(map);
			entity = new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
