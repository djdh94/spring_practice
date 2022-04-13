package com.ict.service;

import java.util.List;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;

// 구현 클래스 BoardServiceImpl의 뼈대가 됩니다.
public interface BoardService {

	// 인터페이스 내에 먼저 메서드를 선언하고,impl 클래스에 구현합니다.
	public List<BoardVO> getList(Criteria cri);
	
	public int countPageNum();
	
	// 글 하나만 가져오는 로직 BoardMapper.java에서 복붙
	public BoardVO select(long bno);
	
	public void delete(long bno);
	
	public void update(BoardVO vo);
	
	public void insert(BoardVO vo);
}
