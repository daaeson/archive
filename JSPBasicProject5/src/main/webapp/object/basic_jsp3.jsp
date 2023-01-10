<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	pageContext
	1) JSP 안에서 사용되는 내부 객체를 얻어올 수 있다 => 사용 빈도 낮다
		request : pageContext.getRequest()
		response : pageContext.getResponse()
		session : pageContext.getSession()
		out : pageContext.getOut()
		application : pageContext.getServletContext()
		page : pageContext.getPage()
		config : pageContext.getServletConfig()
		exception : pageContext.getException()
	2) 웹 흐름 (데이터 공유(request)-include, request를 공유하며 화면 이동 가능-forward)
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String serverName=request.getServerName();
		String serverName2=pageContext.getRequest().getServerName();
		out.println(serverName+"<br>");
		out.println(serverName2);
	%>
</body>
</html>









