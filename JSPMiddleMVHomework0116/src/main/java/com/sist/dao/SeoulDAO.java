package com.sist.dao;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.sist.vo.*;

public class SeoulDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	
	public void getConnection()
	{
		try
		{
			Context init = new InitialContext();
			Context c = (Context)init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void disconnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	public ArrayList<SeoulVO> locationListData()
	{
		ArrayList<SeoulVO> list = new ArrayList<SeoulVO>();
		try
		{
			getConnection();
			String sql = "SELECT no,title,poster,num "
					+ "FROM (SELECT no,title,poster,rownum as num "
					+ "FROM (SELECT no,title,poster "
					+ "FROM seoul_location ORDER BY no ASC)) "
					+ "WHERE num BETWEEN 1 AND 20";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				SeoulVO vo = new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disconnection();
		}
		return list;
		
	}
}
