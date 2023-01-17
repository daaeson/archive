package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArrayList<String> list=new ArrayList<String>();
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자가 보내준 URI를 받는다
    	String uri=request.getRequestURI();
    	System.out.println(uri);
    	uri=uri.substring(request.getContextPath().length()+1);
    	System.out.println(uri);
    	// 2. 요청 처리에 맞는 메소드를 찾는다
    	for(String cls:list)
    	{
    		try
    		{
    			// 클래스 메모리 할당
    			Class clsName=Class.forName(cls);
    			Object obj=clsName.getDeclaredConstructor().newInstance(); // 리플렉션 (메모리 할당 시 new를 사용하지 않고 클래스 정보를 읽어서 메모리 할당)
    			// 클래스에 선언한 모든 메소드를 읽어온다
    			Method[] methods=clsName.getDeclaredMethods();
    			
    			for(Method m:methods)
    			{
    				// method 위에 Annotation을 읽는다
    				RequestMapping rm = m.getAnnotation(RequestMapping.class);
    				if(rm.value().equals(uri))
    				{
    					String jsp=(String)m.invoke(obj, request, response);
    					
    					if(jsp.startsWith("redirect:"))
    					{
    						response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
    					}
    					else
    					{
    						RequestDispatcher rd=request.getRequestDispatcher(jsp);
    						rd.forward(request, response);
    					}
    					return;
    				}
    			}
    		}catch(Exception ex) {}
    	}
    	// 3. 해당 JSP로 request를 전송한다
    	
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		list.add("com.sist.model.FoodModel");
		list.add("com.sist.model.BoardModel");
		// XML에서 찾는 연습 => XML에 클래스 등록하는 연습 => Controller 생성
		// XML에 등록한 데이터를 읽어올 때(파싱할 때) 절대 경로명을 사용 => Mac/Window의 차이
	}

	

}
