package com.sist.dao;
import java.util.*;
import java.sql.*;
public class ItemDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public ItemDAO()
	{
		try
		   {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
		   }catch(Exception ex) {}
	}
	
	public void getConnection()
	   {
		   try
		   {
			   conn=DriverManager.getConnection(URL,"hr","happy");
		   }catch(Exception ex) {}
	   }
	   public void disConnection()
	   {
		   try
		   {
			   if(ps!=null) ps.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
	   
	   public void itemCategoryInsert(CategoryVO vo)
	   {
		   try
		   {
			   getConnection();
			   String sql = "INSERT INTO item_category_2 VALUES(itca_icno_seq_2.nextval,?,?)";
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, vo.getName());
			   ps.setString(2, vo.getLink());
			   
			   ps.executeUpdate();
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
	   }
	   
	   public ArrayList<CategoryVO> itemCategoryInfoData()
	   {
		   ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		   try
		   {
			   //1. 연결 
			   getConnection();
			   //2. SQL문장 제작 
			   String sql="SELECT icno,name,link FROM item_category_2 ORDER BY icno ASC";
			   //3. SQL문장 전송
			   ps=conn.prepareStatement(sql);
			   //4. SQL문장 실행 요청 => 결과값 저장 
			   ResultSet rs=ps.executeQuery();
			   //5. ArrayList에 담기
			   while(rs.next())
			   {
				   CategoryVO vo=new CategoryVO();
				   vo.setIcno(rs.getInt(1));
				   vo.setName(rs.getString(2));
				   vo.setLink("https://campinglist.co.kr"+rs.getString(3));
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
	   
	   public void itemDetailInsert(ItemVO vo)
	   {
		   try
		   {
			   getConnection();
			   String sql="INSERT INTO item_2(ino,image,name,price,description,stock,status,discount,delivery_price,"
					     +"like_cnt,jjim_cnt,icno) VALUES(item_ino_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			   ps=conn.prepareStatement(sql);
			   // ?에 값을 채운다 
			   ps.setInt(1, vo.getIno());
			   ps.setString(2, vo.getImage());
			   ps.setString(3, vo.getName());
			   ps.setInt(4, vo.getPrice());
			   ps.setString(5, vo.getDescription());
			   ps.setInt(6, vo.getStock());
			   ps.setString(7, vo.getStatus());
			   ps.setInt(8, vo.getDiscount());
			   ps.setInt(9, vo.getDelivery_price());
			   ps.setInt(10, vo.getLike_cnt());
			   ps.setInt(11, vo.getJjim_cnt());
			   ps.setInt(12, vo.getIcno());
			   
			   ps.executeUpdate();
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
	   }   
}