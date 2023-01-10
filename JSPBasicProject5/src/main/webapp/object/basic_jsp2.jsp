<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	내부객체 : request, response, session
			 out => <%= %> out.println()
			 application : 서버 관리
			 			   => log(), getInitParameter(), getRealPath(macOS와 호환)
			 	1) 서버 정보 => getServerInfo()
			 	2) 버전 정보 => getMajorVersion(), getMinorVersion()
			 				  3.1 => 3:major / 1:minor
			 pageContext : 각 객체 생성
			 			   => include, forward
			 				  <jsp:include> <jsp:forward> => 페이지의 흐름 표현
			 config(환경설정 => web.xml), exception(예외처리 => try~catch), page(this)
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>application 객체 (ServletContext)</h1>
	서버 이름 : <%=application.getServerInfo() %><br>
	버전 : <%=application.getMajorVersion()+"."+application.getMinorVersion() %><br>
	<b>실제 경로명 : <%=application.getRealPath("/") %></b><br>
	<%-- log => web.xml을 읽어온다 (xml, 한글 변환 등을 등록해놓으면 따로 세팅할 필요없다) --%>
	<%--
		<context-param>
		 <param-name>driver</param-name> =======> 키
		 <param-value>oracle.jdbc.driver.OracleDriver</param-value> ======> 값
		</context-param>
	 --%>
	 <%
	 	String driver=application.getInitParameter("driver");
		String url=application.getInitParameter("url");
		String username=application.getInitParameter("username");
		String password=application.getInitParameter("password");
					 
		application.log("드라이버명 : "+driver);
		application.log("오라클 연결 주소 : "+url);
		application.log("사용자 : "+username);
		application.log("비밀번호 : "+password);
	 %>
</body>
</html>