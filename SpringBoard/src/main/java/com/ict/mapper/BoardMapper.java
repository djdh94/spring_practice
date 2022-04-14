package com.ict.mapper;

import java.util.List;

import com.ict.domain.BoardVO;
import com.ict.domain.Criteria;
import com.ict.domain.SearchCriteria;

public interface BoardMapper {

	// 버튼 추가를 위해 pageNum 대신 Criteria를 활용합니다.
	public List<BoardVO> getList(SearchCriteria cri);
	
	public void insert(BoardVO vo);
	
	// select 구문은 글 번호를 입력받아 해당 글 "하나"에 대한 정보만 얻어올 예정이므로
	// 리턴자료형은 글 하나를 담당할 수 있는 BoardVO로 해야함.
	public BoardVO select(long bno);
	
	public void delete(long bno);
	
	// 전달변수가 title,content,bno이므로 단일자료가 아닌 묶음으로 전달
	public void update(BoardVO vo);
	
	// 전체 글 개수를 얻어오는 getPageNum를 선언합니다.
	// 파라미터는 필요없습니다.
	// 글개수=>정수 값을 조회하기때문에 int리턴입니다.
	public int CountPageNum(SearchCriteria cri);
		
	
	
}
