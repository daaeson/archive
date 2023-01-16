package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.sist.vo.*;
public class BoardDAO {
	
	private Connection conn; // 톰캣에 의해 미리 연결된 객체
	private PreparedStatement ps; // 오라클과 송수신하는 객체
	// Connection의 주소 얻기
	public void getConnection()
	{
		// 탐색기 형식 => 탐색기 열기 => C드라이버 접근 => Connection 객체 주소 불러오기
		try
		{
			Context init = new InitialContext(); // 탐색기 열기
			Context c = (Context)init.lookup("java://comp/env"); // C드라이버 접근
			DataSource ds=(DataSource)c.lookup("jdbc/oracle"); // 
			conn=ds.getConnection();
		}catch(Exception ex) {ex.printStackTrace();}
	}
	// 반환
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			// POOL에 반환 (POOL : Connection 객체가 저장된 메모리 공간)
		}catch(Exception ex) {}
	}
	// 기능 => JSTL / EL
	// 1. 목록
	public ArrayList<BoardVO> boardListData(int page)
	{
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		try
		{
			getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,group_tab,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit,group_tab "
					+ "FROM replyboard ORDER BY group_id DESC,group_step ASC)) "
					+ "WHERE num BETWEEN ? AND ? ";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1,start);
			ps.setInt(2,end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	public int boardRowCount()
	{
		int count=0;
		try
		{
			getConnection();
			String sql="SELECT COUNT(*) FORM replyBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return count;
	}
	// 2. 새글
	// 3. 상세보기
	// 4. 답변 => SQL 4개 문장
	// 5. 수정
	// 6. 삭제 => SQL 4개 문장
	
}
