package com.sist.model;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import com.sist.vo.*;
public class BoardModel {
	public void boardListData(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list=dao.boardListData(curpage);
		request.setAttribute("today", new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
		request.setAttribute("list", list); // JSP에 전송
		request.setAttribute("msg", "관리자가 삭제한 게시물입니다");
	}
	
	// insert_ok.jsp 호출
	public void boardInsert(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			// 한글 변환
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		// DAO 연동
		BoardDAO dao = new BoardDAO();
		dao.boardInsert(vo);
	}
	
	public void boardDetailData(HttpServletRequest request, HttpServletResponse response)
	{
		String no = request.getParameter("no");
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.boardDetailData(Integer.parseInt(no), 1);
		request.setAttribute("vo", vo); // request에 값을 실어 jsp에 보낸다
	}
	
	public void boardReplyInsert(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			// 한글 변환
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String pno=request.getParameter("pno");
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		BoardDAO dao = new BoardDAO();
		dao.boardReplyInsert(Integer.parseInt(pno), vo);
		/*
		 * try { response.sendRedirect("list.jsp"); }
		 *  catch (IOException e) {e.printStackTrace(); }
		 *  => <c:redirect url="list.jsp"/>
		 */
	}	

	public void boardUpdateData(HttpServletRequest request, HttpServletResponse response)
	{
		String no = request.getParameter("no");
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.boardDetailData(Integer.parseInt(no), 2);
		request.setAttribute("vo", vo); // request에 값을 실어 jsp에 보낸다
	}
	
	public void boardUpdate(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			// 한글 변환
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String no=request.getParameter("no");
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		BoardDAO dao = new BoardDAO();
		boolean bCheck=dao.boardUpdate(vo);
		String msg="";
		if(bCheck==true)
			msg="yes";
		else
			msg="no";
		request.setAttribute("msg", msg);
		request.setAttribute("no", no);
	}
	
	public void boardDelete(HttpServletRequest request, HttpServletResponse response)
	{
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		BoardDAO dao = new BoardDAO();
		boolean bCheck=dao.boardDelete(Integer.parseInt(no), pwd);
		request.setAttribute("bCheck", bCheck);
	}
}



