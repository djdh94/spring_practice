package service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.dao.TestDAO;

@Service
public class TestService {
	@Autowired TestDAO testDAO;
	
	public <T> T testOneData(Map<String, Object> map) throws Exception{
		//	여기에 이제 주 기능을 작성해줍니다
		//	뭐 예를 들어서
		String[] test_cd_arr = {"123", "456"};
		
		//	여러개의 값을 한번에 받은경우 이런 식으로 처리합니다.
		//	이건 주로 insert, delete, update
		//	select 는 그냥 거의 이런거 안쓰고 밑ㄴ에 리턴으로 끝내는 경우가 많아요!
		for (String code : test_cd_arr) {
			map.put("test_cd", code);
		}
		
		return testDAO.testOneData(map);
	}
}
