package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
/*
	MVC
		M(Model) => JAVA  DAO,VO,Service,Manager => 사용자 요청을 받아서 처리하고 결과값을 넘겨준다
		V(View)  => JSP => 전송 받은 데이터를 출력만 하는 기능
		C(Controller) => 요청받고 Model을 이용하여 처리, 처리 결과를 View로 전송
		*** 요청받기 : 브라우저에서 JSP / Servlet 어느 것으로 받을지 고민
					 JSP(화면 출력) Servlet(기능 처리)
		
		실행 순서
		  JSP (요청)
		   <a href=""> | <form action=""> | ajax
		=> Controller : Model의 메소드를 찾아 받아온 결과값을 JSP로 전송한다
*/
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// /Controller?cmd=list
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	// doGet() / doPost() => 통합 메소드 : service()
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd=request.getParameter("cmd");
		if(cmd.equals("list"))
		{
			ListModel model = new ListModel();
			model.execute(request);
		}
		else if(cmd.equals("update"))
		{
			UpdateModel model = new UpdateModel();
			model.execute(request);
		}
		else if(cmd.equals("delete"))
		{
			DeleteModel model = new DeleteModel();
			model.execute(request);
		}
		else if(cmd.equals("insert"))
		{
			InsertModel model = new InsertModel();
			model.execute(request);
		}
		
		// 해당 JSP로 값을 전송 (request)
		RequestDispatcher rd = request.getRequestDispatcher("view/"+cmd+".jsp");
		rd.forward(request, response);
		
		
		
	}

	
	
	
}













