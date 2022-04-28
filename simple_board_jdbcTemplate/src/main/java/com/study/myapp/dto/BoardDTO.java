package com.study.myapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	
}
