package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.*;
import com.sist.model.*;
import java.net.*;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> list=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
// 		String path="C:\\webDev\\webStudy\\JSPMVCProject4\\src\\main\\webapp\\WEB-INF\\application.xml";
		try
		{
			// XML의 경로명을 읽을 때 사용 (Mac과 Window 호환)
			URL url=this.getClass().getClassLoader().getResource(".");
			File file=new File(url.toURI());
//			System.out.println(file.getPath());
			String path=file.getPath();
			path=path.replace("\\", File.separator);
			path=path.substring(0,path.lastIndexOf(File.separator));
			path=path+File.separator+"application.xml";
			System.out.println(path);
			// 1. XML 파서기
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance(); // 추상 클래스이기 때문에 new를 사용할 수 없어 이 방식으로 객체 생성
			DocumentBuilder db = dbf.newDocumentBuilder();
			// Jsoup(HTML Parser), DocumentBuilder(XML Parser) => 실무에서 사용하지는 않는다
			Document doc=db.parse(new File(path));
			// Root 태그 => beans
			Element beans=doc.getDocumentElement();
			System.out.println(beans.getTagName()); // 튜플의 값을 가져올 수 있도록 최상위 태그를 불러온다
			NodeList node=beans.getElementsByTagName("bean"); // bean 태그를 모두 모았다 select와 동일한 효과
			for(int i=0;i<node.getLength(); i++)
			{
				Element bean =(Element) node.item(i);
				String clsName=bean.getAttribute("class");
				System.out.println(clsName);
				list.add(clsName);
			}
			
		}catch(Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// uri 받기
		try
		{
			// http://localhost:8080/JSPMVCProject4/main/main.do
			String uri=request.getRequestURI();	// /JSPMVCProject4/main/main.do
			uri=uri.substring(request.getContextPath().length()+1); // main/main.do	// +1 -> / 이후의 값을 가져와라
			
			for(String cls:list)
			{
				Class clsName=Class.forName(cls);
				if(clsName.isAnnotationPresent(Controller.class)==false) // 클래스 위에 @Controller가 없는 경우
					continue; // Controller 가 없다면 다음으로 넘어가기 (제외한다)
				Object obj=clsName.getDeclaredConstructor().newInstance(); // Model 클래스의 객체를 생성한다
				// 선언된 모든 메소드 읽기
				Method[] methods = clsName.getDeclaredMethods();
				for(Method m:methods)
				{
					RequestMapping rm = m.getAnnotation(RequestMapping.class); // 메소드 위의 @RequestMapping을 읽기
					if(rm.value().equals(uri))
					{
						String jsp=(String)m.invoke(obj, request, response);
						RequestDispatcher rd=request.getRequestDispatcher(jsp);
						rd.forward(request, response);
						break;
					}
				}
			}
		}catch(Exception ex) {}
		
	}

}
