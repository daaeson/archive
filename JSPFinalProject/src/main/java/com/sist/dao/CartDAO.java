package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
public class CartDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// 1. 장바구니 출력
	public List<CartVO> goodsCartListData(String id)
	{
		List<CartVO> list = new ArrayList<CartVO>();
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT bno,gno,id,pgb.account,total_price,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,"
					+ "buy_ok,goods_poster,goods_name,goods_price "
					+ "FROM project_goods_buy pgb, goods_all ga "
					+ "WHERE pgb.gno=ga.no "
					+ "AND id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CartVO vo = new CartVO();
				vo.setBno(rs.getInt(1));
				vo.setGno(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.getGvo().setAccount(rs.getInt(4));
				vo.setTotal_price(rs.getInt(5));
				vo.setDbday(rs.getString(6));
				vo.setBuy_ok(rs.getString(7));
				vo.getGvo().setGoods_poster(rs.getString(8));
				vo.getGvo().setGoods_name(rs.getString(9));
				vo.getGvo().setGoods_price(rs.getString(10));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	
	// 장바구니 입력
	public void goodsCartInsert(CartVO vo)
	{
		try
		{
			conn=CreateConnection.getConnection();
			String sql = "INSERT INTO project_goods_buy VALUES"
					+ "(pgb_bno_seq.nextval,?,?,?,?,'n',SYSDATE)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getGno());
			ps.setString(2, vo.getId());
			ps.setInt(3, vo.getAccount());
			ps.setInt(4, vo.getTotal_price());
			ps.executeUpdate();
			//(pgb_bno_seq.nextval,#{gno},#{id},#{account},#{total_price},'n',SYSDATE)
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
	}
	
	public void goodsCartDelete(int bno)
	{
		try
		{
			conn=CreateConnection.getConnection();
			String sql = "DELETE FROM project_goods_buy "
					+ "WHERE bno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bno);
			ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
		
	}
	
	/*
		ssf.openSession(true) => true : commit 수행 => insert,update,delete는 true(commit) 필요
	*/
}