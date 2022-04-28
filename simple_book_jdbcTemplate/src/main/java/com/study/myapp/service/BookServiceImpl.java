package com.study.myapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.myapp.dao.BookDAO;
import com.study.myapp.dto.BookDTO;

@Service("service") //객체생성
public class BookServiceImpl implements BookService {
	
	@Autowired // 객체 주입
	private BookDAO dao; //=> 객체 생성 필요.생성자 만들고, setter 생성해야 하는데 dao에서 @Registered 생성,  @Service @Autowired 

	@Override
	public List<BookDTO> getList() {		
		return dao.select();
	}

	@Override
	public boolean bookInsert(BookDTO insertDto) {		
		return dao.insert(insertDto)==1?true:false;
	}

	@Override
	public boolean bookUpdate(int code, int price) {		
		return dao.update(code,price)==1? true:false;
	}

	@Override
	public boolean bookDelete(int code) {		
		return dao.delete(code)==1? true:false;
	}

}