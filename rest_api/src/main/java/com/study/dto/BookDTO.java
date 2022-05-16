package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//spring이 관리 하지 않음. 필요할때마다 생성

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class BookDTO {
	private int code;
	private String title;
	private String writer;
	private int price;

}
