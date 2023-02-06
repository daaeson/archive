package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class RecipeModel {
   @RequestMapping("recipe/list.do")
   public String recipe_list(HttpServletRequest request,HttpServletResponse response)
   {
	   // 사용자가 보내준 페이지를 받는다 
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);// 현재 페이지
	   // 오라클 연결 
	   RecipeDAO dao=new RecipeDAO();
	   // 데이터 읽기 
	   List<RecipeVO> list=dao.recipeListData(curpage);
	   int count=dao.recipeRowCount();
	   int totalpage=(int)(Math.ceil(count/20.0));
	   
	   // 이전 / 다음 => 20 => 1780000 => 900000
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;// curpage=10   (9/10*10)+1
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   /*
	    *     [1]  ~~~~  [10]  =========================> curpage : 1~10 ==> 1
	    *     ---                                                        ==> 10
	    *     startPage  endPage                          curpage : 11~20 ==> 11
	    *                                                                 ==> 20
	    *                                                                 
	    *     startPage : 1 ,  11  ,  21......
	    *     endPage   : 10 , 20 ,   30
	    */                   
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("list", list);
	   request.setAttribute("count", count);
	   // request에 담아서 jsp로 전송 
	   request.setAttribute("main_jsp", "../recipe/list.jsp");
	   CommonsModel.footerData(request);
	   return "../main/main.jsp";
   }
}










