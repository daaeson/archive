package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.sist.vo.*;
// Connection을 미리 생성 => 생성된 Connection의 주소를 얻어서 사용
// Connection의 객체 생성을 제한할 수 있다 => 메모리 절약
// 연결에 소요되는 시간 단축 => 빠른 연결 속도
// 서버가 쉽게 다운되지 않는다
// 웹 프로그램의 일반화
// MyBatis, JPA => DB 연결 라이브러리 (DBCP)

public class FoodDAO {

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
	// DB 연동 => 기능
	// 1. 카테고리 일기
	public ArrayList<CategoryVO> categoryListData()
	{
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try
		{
			getConnection();
			String sql="SELECT /*+ INDEX_ASC(project_category pc_cno_pk)*/ cno,title,subject,poster "
					+ "FROM project_category";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CategoryVO vo = new CategoryVO();
				vo.setCno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setPoster(rs.getString(4));
				list.add(vo);
			}
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
	// 카테고리별 맛집 목록
	public ArrayList<FoodVO> foodListData(int cno)
	{
		ArrayList<FoodVO> list = new ArrayList<FoodVO>();
		try
		{
			getConnection();
			String sql="SELECT /*+ INDEX_ASC(project_food pf_fno_pk)*/ fno,name,score,address,tel,type,poster "
					+ "FROM project_food "
					+ "WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setScore(rs.getDouble(3));
				String addr=rs.getString(4);
				addr=addr.substring(0,addr.lastIndexOf("지"));
				vo.setAddress(addr);
				vo.setTel(rs.getString(5));
				vo.setType(rs.getString(6));
				String poster=rs.getString(7);
				poster=poster.substring(0,poster.indexOf("^"));
				vo.setPoster(poster);
				
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
	// 카테고리 정보
	public CategoryVO categoryInfoData(int cno)
	{
		CategoryVO vo = new CategoryVO();
		try
		{
			getConnection();
			String sql="SELECT title,subject FROM project_category "
					+ "WHERE cno=? ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setTitle(rs.getString(1));
			vo.setSubject(rs.getString(2));
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	
	// DAO(오라클에 저장된 데이터를 불러오기) ==> JSP
	// JSP에서 자바 / HTML을 분리
	// DAO => 자바(Model) HTML(View) => MV 패턴
	
	public FoodVO foodDetailData(int fno)
	   {
		   FoodVO vo=new FoodVO();
		   try
		   {
			   getConnection();
			   String sql="SELECT fno,name,score,poster,tel,type,time,parking,menu,price,address "
					     +"FROM food_location "
					     +"WHERE fno=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, fno);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setScore(rs.getDouble(3));
			   vo.setPoster(rs.getString(4));
			   vo.setTel(rs.getString(5));
			   vo.setType(rs.getString(6));
			   vo.setTime(rs.getString(7));
			   vo.setParking(rs.getString(8));
			   vo.setMenu(rs.getString(9));
			   vo.setPrice(rs.getString(10));
			   vo.setAddress(rs.getString(11));
			   rs.close();
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
		   return vo;
	   }
}

 









