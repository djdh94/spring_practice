package org.ict.parsistence;




import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.domain.BoardVO;
import com.ict.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Autowired
	private BoardMapper boardMapper;
	//@Test
	public void testGetList() {
		List<BoardVO> result=boardMapper.getList(1);
		log.info(result);
	}
	//@Test
	public void testInsert() {
		
		BoardVO vo =new BoardVO();
		log.info("채워넣기전:"+vo);
		
		vo.setTitle("새로넣는글");
		vo.setContent("본문");
		vo.setWriter("글쓴이");
		
		log.info("채워넣은후:"+vo);
		boardMapper.insert(vo);
	}
	//@Test
	public void getselect() {
		// 가져오기
		BoardVO vo =boardMapper.select(5);
		// 로그찍기
		log.info(vo);
	}
	//@Test
	public void getdelete() {
		boardMapper.delete(5);
	}
	@Test
	public void getAllList() {
		
		BoardVO board = new BoardVO();
		board.setContent("테스트");
		board.setTitle("테스트");
		board.setBno(24);
		log.info(board);
		boardMapper.update(board);
	}
	
}
