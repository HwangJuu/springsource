package com.study.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static com.study.myapp.dao.JdbcUtil.*;
import com.study.myapp.dto.BoardDTO;

@Repository //저장소 객체
public class BoardDAO {
	
	//private Connection con;
	@Autowired
	private DataSource ds;
	
	//insert
	public int insert(BoardDTO insertDto) {
		PreparedStatement pstmt = null;
		String sql = "insert into spring_board(bno,title,content, writer) values(seq_board.nextval,?,?,?)";
		Connection con = null;
		
		int result = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getTitle());
			pstmt.setString(2, insertDto.getContent());
			pstmt.setString(3, insertDto.getWriter());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);			
		}
		return result;
	}
	//list
	public List<BoardDTO> select(){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from spring_board";
		Connection con = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
				
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
	
	//update - bno가 일치하면 title, content,updatedate 수정
	public int update(BoardDTO updateDto) {		
		PreparedStatement pstmt = null;
		String sql = "update spring_board set title=?, content=?, updatedate=sysdate where bno=?";
		int result = 0;
		Connection con = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateDto.getTitle());
			pstmt.setString(2, updateDto.getContent());
			pstmt.setInt(3, updateDto.getBno());
	
			result = pstmt.executeUpdate();
					
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		
		}
		return result;
	}
	
	//게시글 삭제 - bno 일치시 삭제
	public int delete(int bno) {
		PreparedStatement pstmt = null;
		String sql = "delete from spring_board where bno=?";
		int result = 0;	
		Connection con = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);	
	
			result = pstmt.executeUpdate();				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);		
		}
		return result;
		
	}
	
	//게시글 조회 (하나만 가지고 오는거)
	public BoardDTO selectOne(int bno) {
		BoardDTO dto = null;
		PreparedStatement pstmt = null;
		String sql = "select * from spring_board where bno=?";
		ResultSet rs = null;
		Connection con = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);	
	
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
			close(con);		
		}
		return dto;
		
	}
	
}
