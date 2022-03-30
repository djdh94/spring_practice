package com.ict.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/getData" ,method = RequestMethod.POST)
					// /getData?data1=데이터1&data2=데이터2에 해당하는 요소를 받아옵니다.
	public String getData(String data1,int data2,Model model) {
	
		System.out.println("data1에 든값 : "+data1);
		System.out.println("data2에 든값 : "+data2);
		System.out.println("data2가 정수임을 증명 : "+(data2+100));
		model.addAttribute("data1",data1);
		model.addAttribute("data2",data2);
		return "getResult";
	}
	
	// 외부에서 전송하는 데이터를 /getMoney 주소로 받아오겠습니다
	// 이 주소는 int won 이란느 형식으로 금액을 받아서
	// 환율에 따른 환전금액을 콘솔에 찎어줍니다.
	// 결과페이지는 exchange.jsp 로 하겠습니다.
	// 메서드명은 임의로 만들어주세요.
	@RequestMapping(value = "/getMoney",method = RequestMethod.POST)//post방식으로만 받도록 처리
	// 포워딩시 바인딩을 하고 싶다면 Model을 선언합니다
	public String test(int won,Model model) {
		System.out.println("입력한 금액" + won);
		System.out.println("현재 한율은 36.16원당 1바트 입니다");
		System.out.println("입력한 금액에 따른 환전 금액은"+won/36.16);
		double result=(won/36.16);
		// model.addAttribute("보낼 이름",보낼자료);
		// 넘어간 데이터는 .jsp파일에서 el을 이용해 출력합니다.
		// ex->model.addAttribute("test",자료); 로 바인딩한 경우
		// ${test} 로 .jsp에서 출력 가능
		model.addAttribute("result",result);
		model.addAttribute("moneymoney",won);
		return "exchange";
	}
	
	
	// form 페이지와 결과페이지를 분리해야 합니다.
		// 다만 목적지 주소가 .jsp 기준이 아닌, @RequestMapping 상의 주소기준으로 갑니다.
		// 주소 moneyForm으로 연결되도록 아래에 어노테이션 + 메서드를 구성해주세요.
		// moneyForm.jsp로 연결됩니다
		// moneyForm.jsp 에는 목적지를 # 으로 하고
		// name=won 인 폼을 추가로 만들어주세요.
		
		// 1. @RequestMapping에 어떤 주소로 접속해야 하는지 적는다
		@RequestMapping(value = "/moneyForm")
		// 2. public String 메서드() 를 만든다.
		public String moneyForm() {
		// 3. return구문 뒤에 연결할 .jsp파일의 이름을 적는다(확장자x)
			
			return "moneyForm";
		}
		
		// 상단 /getData 주소를 타겟으로 하는 /dataForm을 만들어주세요
		// data1,data2를 자료형에 맞게 폼으로 입력받아 전송버튼을 누르면
		// 해당 데이터가 결과 페이지에 나올 수 있도록 .jsp파일부터 시작해서
		// form 태그나 세부 로직까지 완성하기
		// 1. 주소 및 연결 메서드 완성 후 보내기
		// 2. form 태그 완성후 보내기
		
		@GetMapping(value = "/dataForm")
		public String test3() {
			
			return "dataForm";
		}
		
		// 스프링 5버전부터 허용
		// @요청메서드Mapping 은 해당 메서드만 허용하는 어노테이션입니다.
		@GetMapping(value = "/onleyGet")
		public String onlyGet() {
			return "onleyGet";
		}
		
		// 성적 입력 폼 접근로직
		@GetMapping(value = "/score")
		public String test4() {
			
			return "scoreForm";
		}
		// 성적 결과 페이지 접근 로직
		@PostMapping(value="/score")
		public String test5(int a,int b, int c ,int d ,int e,Model model) {
			int total = a+b+c+d+e;
			double avg = total/5.0;
			model.addAttribute("total",total);
			model.addAttribute("avg",avg);
			model.addAttribute("a",a);
			model.addAttribute("b",b);
			model.addAttribute("c",c);
			model.addAttribute("d",d);
			model.addAttribute("e",e);
			return "scoreResult";
		}
}