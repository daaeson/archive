package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.sql.*;
import com.sist.vo.*;
import java.io.*;
public class CartDAOmybatis {
	private static SqlSessionFactory ssf; // 연결 담당 getconnection disconnection 가능한 CreateConnection과 동일
	static
	{
		// XML 파싱
		try
		{
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex) {ex.printStackTrace();}
	}
	/*
	<insert id="goodsCartInsert" parameterType="CartVO"> <!-- cart db에 데이터 집어넣기 -->
      INSERT INTO project_goods_buy VALUES(pgb_bno_seq.nextval,#{gno},#{id},#{account},#{total_price},'n',SYSDATE)
    </insert>
	*/
	public static void goodsCartInsert(CartVO vo)
	{
		ssf.openSession(true).insert("goodsCartInsert",vo);
	}
	// select
	/*
	  <select id="goodsCartListData" resultType="CartVO" parameterType="string">
	    SELECT bno,gno,id,account,total_price,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,
	    buy_ok,goods_poster,goods_name,goods_price
	    FROM project_goods_buy pgb, goods_all ga
	    WHERE pgb.gno=ga.no
	    AND id=#{id}
	  </select>
	  
	  public static List<CartVO> goodsCartListData(String id)
	  				resultType					   parameterType
	*/
	public static List<CartVO> goodsCartListData(String id)
	{
		List<CartVO> list = null;
		try
		{
			SqlSession session = ssf.openSession();
			list=session.selectList("goodsCartListData",id);
			session.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return list;
	}
	// delete
	/*
	  <delete id="goodsCartDelete" parameterType="int">
	    DELETE FROM project_goods_buy
	    WHERE bno=#{bno}
	  </delete>
	*/
	public static void goodsCartDelete(int bno)
	{
		ssf.openSession(true).delete("goodsCartDelete",bno);
	}
	
	/*
		ssf.openSession(true) => true : commit 수행 => insert,update,delete는 true(commit) 필요
	*/
}