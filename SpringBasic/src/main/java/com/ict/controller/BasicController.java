package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 어노테이션 네 종류가 있었는데 (@Component, @Repository, @Controller , @Service)
// 컨트롤러를 만드는 경우이니 당연히 @Controller를 씁니다.
@Controller
public class BasicController {
	// RequestMapping의 value는 localhost:8181/어떤주소 로 접속시 해당 로직이 실행될지 결정합니다.
	// 아무것도 안 적으면 기본적으로 get방식을 허용합니다.
	@RequestMapping(value="/goA")
	// 아래에 해당 주소로 접속시 실행하고 싶은 메서드를 작성합니다.
	public String goA() {
	System.out.println("goA 접속이 감지되었습니다.");
	// return "goA"; 라고 적으면 views폴더 내부의 goA.jsp파일을 보여줍니다.
	return "goA";
	}
	
	// /goB로 접속했을때 실행되는 b.jsp
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB 접속이 감지되었습니다");
		return "b";
	}
	
	// 여러분들의 성함 성씨 기준 (강사기준:"/chae")으로 패턴을 잡고
	// 결과페이지는 "xxx의 페이지 입니다"라는 문장이 뜨도록 처리해서 메서드와 어노테이션을 보내기
	@RequestMapping(value="/kim")
	public String goC() {
		return "c";
	}
	// 외부에서 전송하는 데이터는 메서드 선언부에 선언된 변수로 받습니다.
	// 이름만 일치하면 알아서 받아옵니다.
	// 자료형을 신경쓸 필요가 없습니다.
	
	@RequestMapping(value="/getData")
					// /getData?data1=데이터1&data2=데이터2에 해당하는 요소를 받아옵니다.
	public String getData(String data1,int data2) {
		System.out.println("data1에 든값 : "+data1);
		System.out.println("data2에 든값 : "+data2);
		System.out.println("data2가 정수임을 증명 : "+(data2+100));
		return "getResult";
	}
	
	// 외부에서 전송하는 데이터를 /getMoney 주소로 받아오겠습니다
	// 이 주소는 int won 이란느 형식으로 금액을 받아서
	// 환율에 따른 환전금액을 콘솔에 찎어줍니다.
	// 결과페이지는 exchange.jsp 로 하겠습니다.
	// 메서드명은 임의로 만들어주세요.
	@RequestMapping(value = "/getMoney")
	public String test(int won) {
		System.out.println("입력한 금액" + won);
		System.out.println("현재 한율은 36.16원당 1바트 입니다");
		System.out.println("입력한 금액에 따른 환전 금액은"+won/36.16);
		return "exchange";
	}
	
}
