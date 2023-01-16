package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

public class FoodModel {
	public void categoryListData(HttpServletRequest request,HttpServletResponse response)
	{
		// DAO 연결
		FoodDAO dao = new FoodDAO();
		ArrayList<CategoryVO> list = dao.categoryListData();
		// JSP로 전송
		request.setAttribute("list", list);
	}
	public void foodListData(HttpServletRequest request,HttpServletResponse response)
	{
		String cno=request.getParameter("cno");
		FoodDAO dao = new FoodDAO();
		CategoryVO vo = dao.categoryInfoData(Integer.parseInt(cno));
		ArrayList<FoodVO> list = dao.foodListData(Integer.parseInt(cno));
		
		// 동시에 JSP로 전송 (food_list.jsp)
		request.setAttribute("vo", vo);
		request.setAttribute("list", list);
		
		// request에 여러 개의 데이터를 저장하여 전송할 수 있다 (화면 출력에 관련된 데이터를 모두 따로 담아 전송)
	}
	public void foodDetailData(HttpServletRequest request,HttpServletResponse response)
	{
		String fno=request.getParameter("fno");
		FoodDAO dao = new FoodDAO();
		FoodVO vo = dao.foodDetailData(Integer.parseInt(fno));
		request.setAttribute("vo", vo);
	}
}











