package com.ict.persistence;


import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

	//커넥션풀 연결은 @RunWith어노테이션과
	// 빈 컨테이너 내부에 생성된 요소를 클래스로 가져오는 @ContextConfiguration("경로")가 같이 적혀야 합니다.
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
	@Log4j
	public class OracleConnectionPoolTest {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	//테스트 수행시 @Test가 붙은 메서드 하나하나를 전부 실행함
	// 그래서 보통 하나의 테스트가 끝나면 주석처리해서 실행은 가능하지만
	// 현재는 실행이 안되게 조치함
	
	//@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()){
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testmyBatis() {
		try (SqlSession session = sqlSessionFactory.openSession();
				Connection con = session.getConnection();){
			log.info("마이바티스 연결시작");
			log.info(session);
			log.info(con);
		}catch (Exception e) {
			
			fail(e.getMessage());
		}
	}
}
