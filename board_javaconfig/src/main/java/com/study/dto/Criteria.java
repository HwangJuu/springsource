package com.study.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum; //사용자가 선택한 페이지 번호
	private int amount;  // 한 페이지당 보여줄 게시물 수
	
	private String type; // 검색조건 : T or C or W or TC or TW or TCW
	private String keyword; //검색어
	
	public Criteria() { //default 생성자.
		this(1,10); //다른 생성자 호출
	}
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}

	//검색 조건을 String[]로 변경
	public String[] getTypeArr() {
		//타입이 null 이면 비어있는 배열
		//타입이 T=> {"T"}, W=>{"W"},C=>{"C"}
		// TC=>{"T","C"}, TW=>{"T","W"}, TCW=>{"T","C","W"}
		return type == null? new String[] {}:type.split("");
	}
	
}
