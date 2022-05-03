package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

// BoardService 인터페이스 구현

@Service // 빈 컨테이너에 등록(root-context.xml에서 컴포넌트 스캔까지 완료해야 등록됨)
public class BoardServiceImpl implements BoardService {

	// 서비스가 DAO(Mapper.java)를 호출한다면 선언을 하고 의존성 주입을 해야합니다.
	// 해당 코드를 작성하기 (BoardController.java를 참조)
	
	@Autowired
	private BoardMapper boardMapper;
	
	// 글 삭제시 해당 글의 전체 댓글을 삭제하기 위해 reply_tbl 생성
	@Autowired
	private ReplyMapper replyMapper;
	
	
	// 리턴자료형이 없는 insert,delete,update 구문은 사용자 행동 기준으로 메서드를 나눕니다.
	// 리턴자료형이 있는 select구문은 하나의 메서드가 하나의 쿼리문을 담당합니다.
	
	@Override
	public List<BoardVO> getList(SearchCriteria cri){
		return boardMapper.getList(cri);
	}
	@Override
	public int countPageNum(SearchCriteria cri) {
		return boardMapper.CountPageNum(cri);
	}
	
	@Override
	public BoardVO select(long bno) {
		return boardMapper.select(bno);
	}
	
	@Transactional
	@Override
	public void delete(long bno) {
		// 댓글 먼저 삭제 후
		replyMapper.deleteAllReplies(bno);
		// 글 마저 삭제(댓글이 없는 글만 삭제가 가능하므로 위의 순서대로 작성)
		boardMapper.delete(bno);
	}
	@Override
	public void update(BoardVO vo) {
		
		boardMapper.update(vo);
	}
	@Override
	public void insert(BoardVO vo) {
		boardMapper.insert(vo);
	}
	
	
}
