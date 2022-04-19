package com.ict.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO{
	
	@Autowired private SqlSessionTemplate sqlSession;

	//	이렇게 하면 그냥 맵퍼.xml 파일 에서 해당하는 아이디 찾아서 바로 쿼리 실행해줍니다.
	public <T> T testOneData(Map<String, Object> map) throws Exception{
		return sqlSession.selectOne("매퍼 아이디", map);
	}
	
	
	
}
