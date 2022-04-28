package com.study.myapp;

import org.springframework.stereotype.Component;

@Component
public class Product {
	private String company;
	private String pname;
	private String price;
	
	
	public String getCompany() {
		//System.out.println("getCompany 실행");
		return company;
	}
	public void setCompany(String company) {
		//System.out.println("setCompany 실행");
		this.company = company;
	}
	public String getPname() {
		//System.out.println("getPname 실행");
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public void getInfo() throws Exception {
		
		System.out.println("회사명 : " + company);
		System.out.println("상품명 : " + pname);
		System.out.println("가격 : " + price);
		
		throw new Exception("예외사항 발생");
	}
	
	
	
}
