package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap = new HashMap();
	public void init(ServletConfig config) throws ServletException {
		clsMap.put("list.do", new ListModel());
		clsMap.put("update.do", new UpdateModel());
		clsMap.put("delete.do", new DeleteModel());
		clsMap.put("insert.do", new InsertModel());
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		  	http://localhost
		  	
		  	uri = "/JSPMVCProject2/list.do"
		  	ContextPath = /JSPMVCProject2
		 */
		String uri=request.getRequestURI();
		uri=uri.substring(request.getContextPath().length()+1);
		System.out.println(uri);
		Model model=(Model)clsMap.get(uri);
		String jsp = model.execute(request);
		// 이동
		RequestDispatcher rd= request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}













