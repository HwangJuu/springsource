package com.study.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class ReplyPageDTO {
	//댓글 전체 개수
	//댓글 목록
	private int replyCnt;
	private List<ReplyDTO> list;

}
