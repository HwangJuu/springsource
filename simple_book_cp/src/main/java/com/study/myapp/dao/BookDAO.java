package com.study.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.myapp.dto.BookDTO;



import static com.study.myapp.dao.JdbcUtil.*;

@Repository //객체 생성
public class BookDAO {
	
	//private Connection con; config에서 새로 환경설정 후 필요없음
	@Autowired //객체주입
	private DataSource ds; // config.xml에서 HikariCP설정 부분을 주입.속도가 빨라짐
	
	public List<BookDTO> select(){
		List<BookDTO> list = new ArrayList<BookDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		Connection con = null; //변경 후
		
		String sql = "select * from booktbl";
		
		try {
			con = ds.getConnection(); //변경 후
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}		
		return list;
	}
	//insert
	public int insert(BookDTO insertDto) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into booktbl values(?,?,?,?)";
		Connection con = null;  //변경 후
		
		try {
			con = ds.getConnection();  //변경 후
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, insertDto.getCode());
			pstmt.setString(2, insertDto.getTitle());
			pstmt.setString(3, insertDto.getWriter());
			pstmt.setInt(4, insertDto.getPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			
		} finally {
			close(pstmt);
			close(con);
		}
		return result;
		
	}
	
	
	
	
	
	
	
	
	
}
