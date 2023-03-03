package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class JejuController {
   @Autowired
   private JejuDAO dao;
   
   @Autowired
   private ReplyDAO rdao;
   
   @GetMapping("jeju/location.do")
   public String jeju_location(String page,Model model)
   {
	   if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		List<JejuLocationVO> sList=dao.jejuLocationListData(map);
		int totalpage=dao.jejuTotalPage();
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("sList", sList);
		model.addAttribute("main_jsp", "../jeju/location.jsp");
	   return "main/main";
   }
   @GetMapping("jeju/food.do")
   public String jeju_food(String page,Model model)
   {
	   if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		List<JejuFoodVO> sList=dao.jejuFoodListData(map);
		int totalpage=dao.jejuFoodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("sList", sList);
		model.addAttribute("main_jsp", "../jeju/food.jsp");
	   return "main/main";
   }
   // food_detail.do?no=${vo.no }&type=2
   @GetMapping("jeju/food_detail.do")
   public String jeju_food_detail(int fno,int type,Model model)
   {
	   JejuFoodVO vo=dao.jejuDetailData(fno);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_jsp", "../jeju/food_detail.jsp");
	   
	   // 댓글 
	   List<ReplyVO> rList=rdao.replyListData(fno, type);
	   model.addAttribute("rList", rList);
	   return "main/main";
   }
   
   @GetMapping("jeju/jeju_food_list.do")
   public String jeju_food_list(Model model)
   {
	   model.addAttribute("main_jsp", "../vuejs/jeju_food.jsp");
	   return "main/main";
   }
}
