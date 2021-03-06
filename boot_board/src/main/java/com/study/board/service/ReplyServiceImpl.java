package com.study.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.dto.Criteria;
import com.study.board.dto.ReplyDTO;
import com.study.board.dto.ReplyPageDTO;
import com.study.board.mapper.BoardMapper;
import com.study.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	
	@Transactional //둘중에 하나라도 실패면 안됨.
	@Override
	public boolean replyInsert(ReplyDTO insertDto) {
		
		//원본글의 댓글 수 추가
		boardMapper.updateReplyCnt(insertDto.getBno(),1);
		
		return mapper.insert(insertDto)==1?true:false;
	}


	@Override
	public ReplyDTO replyRow(int rno) {
		return mapper.read(rno);
	}


	@Override
	public boolean replyUpdate(ReplyDTO updateDto) {
		return mapper.update(updateDto)==1? true:false;
	}

	@Transactional
	@Override
	public boolean replyDelete(int rno) {
		
		//rno만 넘어오기 때문에 bno 알아내기
		ReplyDTO dto = mapper.read(rno);
		
		//원본글의 댓글 수 감소
		boardMapper.updateReplyCnt(dto.getBno(),-1);
		
		return mapper.delete(rno)==1? true:false;
	}


	@Override
	public ReplyPageDTO getList(Criteria cri, int bno) {
		return new ReplyPageDTO(mapper.getCountBno(bno), mapper.select(cri, bno));
		
	}


	


	


	


	

}
