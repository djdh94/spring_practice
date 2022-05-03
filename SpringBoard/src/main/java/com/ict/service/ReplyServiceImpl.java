package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.ReplyVO;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	// 서비스가 매퍼를 호출하므로 매퍼를 위해 선언해야합니다.
	@Autowired
	private ReplyMapper replyMapper;
	
	// 댓글쓰기시 Board_tbl 쪽에도 관여해야 하므로 board테이블을 수정하는 Mapper를 추가선언합니다.
	@Autowired
	private BoardMapper boardMapper; 
	
	@Override
	public List<ReplyVO> listReply(Long bno) {
		return replyMapper.getList(bno);
	}

	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		replyMapper.create(vo);	
		// 댓글 번호는 replyvo에 들어있으므로 getter를 활용
		boardMapper.updateReplyCount(vo.getBno(), 1);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		replyMapper.update(vo);
	}
	
	@Transactional
	@Override
	public void removeReply(Long rno) {
		Long bno = replyMapper.getBno(rno);
		replyMapper.delete(rno); // 댓글삭제
		boardMapper.updateReplyCount(bno, -1);//  그 글번호에서 댓글갯수 줄이기
	}
	

}