package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;
// Spring
@Controller
public class GoodsModel {
   @RequestMapping("goods/goods_all.do") // if문 대신 사용
   public String goodsAll(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   GoodsDAO dao=new GoodsDAO();
	   ArrayList<GoodsVO> list=dao.goodsAllListData(curpage);
	   int totalpage=dao.goodsAllTotalPage();
	   
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   if(endPage>totalpage)
		   endPage=totalpage;
	   // 화면에 출력할 모든 데이터를 JSP로 전송 
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("main_jsp", "../goods/goods_all.jsp");// main.jsp에서 include되는 파일 지정 
	   CommonsModel.footerData(request);
	   return "../main/main.jsp";// cd  ==> ..
   }
   @RequestMapping("goods/goods_best.do")
   public String goodsBest(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   GoodsDAO dao=new GoodsDAO();
	   ArrayList<GoodsVO> list=dao.goodsBestListData(curpage);
	   int totalpage=dao.goodsBestTotalPage();
	   
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   if(endPage>totalpage)
		   endPage=totalpage;
	   // 화면에 출력할 모든 데이터를 JSP로 전송 
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("main_jsp", "../goods/goods_best.jsp");// main.jsp에서 include되는 파일 지정 
	   CommonsModel.footerData(request);
	   return "../main/main.jsp";
   }
   @RequestMapping("goods/goods_new.do")
   public String goodsNew(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   GoodsDAO dao=new GoodsDAO();
	   ArrayList<GoodsVO> list=dao.goodsBestListData(curpage);
	   int totalpage=dao.goodsBestTotalPage();
	   
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   if(endPage>totalpage)
		   endPage=totalpage;
	   // 화면에 출력할 모든 데이터를 JSP로 전송 
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("main_jsp", "../goods/goods_new.jsp");// main.jsp에서 include되는 파일 지정 
	   CommonsModel.footerData(request);
	   return "../main/main.jsp";
   }
   @RequestMapping("goods/goods_special.do")
   public String goodsSpecial(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   GoodsDAO dao=new GoodsDAO();
	   ArrayList<GoodsVO> list=dao.goodsSpecialListData(curpage);
	   int totalpage=dao.goodsSpecialTotalPage();
	   
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   if(endPage>totalpage)
		   endPage=totalpage;
	   // 화면에 출력할 모든 데이터를 JSP로 전송 
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("main_jsp", "../goods/goods_special.jsp");// main.jsp에서 include되는 파일 지정 
	   CommonsModel.footerData(request);
	   return "../main/main.jsp";
   }
   @RequestMapping("goods/goods_detail.do")
   public String goods_detail(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   GoodsDAO dao=new GoodsDAO();
	   GoodsVO vo=dao.goodsDetailData(Integer.parseInt(no));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../goods/goods_detail.jsp");
	   CommonsModel.footerData(request);
	   return "../main/main.jsp";
   }
   
//////////////////////// 장바구니 구현   
   
    // 1. 세션을 이용하는 방식 (단점 : 종료 시 사라진다)
    /*@RequestMapping("goods/cart_insert.do")
    public String goods_cart(HttpServletRequest request, HttpServletResponse response)
    {
    	
//		<input type="hidden" name=no id="goods_no" value="${vo.no}">
//      <input type="hidden" name=account id="goods_account">
//      <input type="hidden" name=total id="goods_total">
		
    	HttpSession session = request.getSession();
    	String id=(String)session.getAttribute("id");
    	String no=request.getParameter("no");
    	String account=request.getParameter("account");
    	String total = request.getParameter("total");
	   
    	List<CartVO> list=(List<CartVO>)session.getAttribute("cart"); // 이 카트와 가장 마지막 줄 카트 동일
    	int cno = 1;
    	if(list==null)
    	{
    		list=new ArrayList<CartVO>();
    		cno=1;
    	}// 처음엔 리스트가 존재하지 않기 때문에 null 값일 때 처리
    	else
    	{	// Session 이용 시 (DB 연동하지 않았을 때) 자동 증가번호
    		int max=1;
    		for(CartVO c:list)
    		{
    			if(max<c.getBno())
    			{
    				max=c.getBno();
    			}
    		}
    		cno=max+1;
    	}
    	// GoodsVO vo = new GoodsVO();
    	// GoodsDAO dao = new GoodsDAO();
    	
//		BNO         NOT NULL NUMBER       
//		GNO                  NUMBER       
//		ID                   VARCHAR2(20) 
//		ACCOUNT              NUMBER       
//		TOTAL_PRICE          NUMBER       
//		BUY_OK               CHAR(1)      
//		REGDATE              DATE      	
    	
    	CartVO vo = new CartVO();
    	vo.setBno(cno);
    	vo.setGno(Integer.parseInt(no));
    	vo.setId(id);
    	vo.setAccount(Integer.parseInt(account));
    	vo.setTotal_price(Integer.parseInt(total));
    	vo.setBuy_ok("n");
    	vo.setRegdate(new Date());
    	list.add(vo);
    	session.setAttribute("cart", list);  // 이 카트와 위의 카트 동일
    	return "redirect:cart_list.do";
    }
    @RequestMapping("goods/cart_list.do")
    public String goods_cart_list(HttpServletRequest request, HttpServletResponse response) 
    {
    	HttpSession session = request.getSession();
    	List<CartVO> list=(List<CartVO>)session.getAttribute("cart");
    	GoodsDAO dao = new GoodsDAO();
    	if(list!=null)
    	{
        	for(CartVO vo:list)
        	{
        		GoodsVO g=dao.goodsDetailData(vo.getGno());
        		vo.getGvo().setGoods_name(g.getGoods_name());
        		vo.getGvo().setGoods_poster(g.getGoods_poster());
        		vo.getGvo().setGoods_price(g.getGoods_price());
        	}    		
    	}
    	else
    	{
    		request.setAttribute("count", 0);
    	}
    	
    	request.setAttribute("list", list);
    	request.setAttribute("count", list.size());
    	request.setAttribute("mypage_jsp", "../goods/cart_list.jsp");
    	request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
    	return "../main/main.jsp";
    }
    @RequestMapping("goods/cart_cancel.do")
    public String goods_cart_cancel(HttpServletRequest request, HttpServletResponse response)
    {
    	HttpSession session = request.getSession();
    	String bno=request.getParameter("bno");
    	List<CartVO> list=(List<CartVO>)session.getAttribute("cart");
    	for(CartVO vo:list)
    	{
    		if(vo.getBno()==Integer.parseInt(bno))
    		{
    			list.remove(vo);
    			break;
    		}
    	}
    	session.setAttribute("cart", list);
    	return "redirect:cart_list.do";
    }*/
    
    // DB를 이용
   	@RequestMapping("goods/cart_insert.do")
   	public String goods_cart(HttpServletRequest request, HttpServletResponse response)
   	{
   		String no=request.getParameter("no");
    	String account=request.getParameter("account");
    	String total = request.getParameter("total");
    	HttpSession session = request.getSession();
    	String id=(String)session.getAttribute("id");
    	CartVO vo = new CartVO();
    	vo.setGno(Integer.parseInt(no));
    	vo.setId(id);
    	vo.setAccount(Integer.parseInt(account));
    	vo.setTotal_price(Integer.parseInt(total));
    	// DAO 연동
//    	CartDAOmybatis.goodsCartInsert(vo);
    	CartDAO dao = new CartDAO();
    	dao.goodsCartInsert(vo);
   		return "redirect:cart_list.do";
    }
   	
   	@RequestMapping("goods/cart_list.do")
    public String goods_cart_list(HttpServletRequest request, HttpServletResponse response) 
    {
   		HttpSession session = request.getSession();
   		String id=(String)session.getAttribute("id");
//   	List<CartVO> list = CartDAOmybatis.goodsCartListData(id);
   		CartDAO dao = new CartDAO(); //
   		List<CartVO> list=dao.goodsCartListData(id); //
   		if(list.size()>0)
   		{
   			request.setAttribute("count", list.size());
   			request.setAttribute("list", list);
   		}	
   		else
   			request.setAttribute("count", 0);
   		request.setAttribute("mypage_jsp", "../goods/cart_list.jsp");
    	request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
    	return "../main/main.jsp";
    }
    
   	@RequestMapping("goods/cart_cancel.do")
    public String goods_cart_cancel(HttpServletRequest request, HttpServletResponse response)
    {
   		String bno=request.getParameter("bno");
   		CartDAO dao = new CartDAO();
   		dao.goodsCartDelete(Integer.parseInt(bno));
   		return "redirect:cart_list.do";
    }
    
}