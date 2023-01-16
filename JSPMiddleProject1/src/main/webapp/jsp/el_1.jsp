<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL => <%= %>을 대체 (화면에 데이터 출력) : 표현식
	-- Spring, 실무
	자바 제어문 : => JSTL
	------------------------> 자바를 최소화
	1) 복잡 (HTML & 자바)
	2) Front / Back => 공동 작업이 가능하게 만든다
	-------------------------------------------
	1. 내장객체 (525page)
	  1) requestScope : request.getAttribute()
	  2) sessionScope : session.getAttribute()
	  3) param : request.getParameter()
	  => 사용 방식
	  	 <%= %> ==> ${}
	  	 <%
	  	 	String name="홍길동";
	  	 %>
	  	 ${name} ==> 출력하지 않는다
	  	 <%
	  	 	String name="홍길동";
	  	 	request.setAttribute("name",name)
	  	 %>
	  	 ${requestScope.name} => ${name} (requestScope 생략 가능)
	2. 연산자
 --%>
<%
	String name="홍길동";
	// ${}를 이용하여 출력 => request,session => 추가적으로 담아서 출력 => setAttribute()
	request.setAttribute("name", "심청이");
	session.setAttribute("name", "박문수");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일반 변수일 때 출력</h1>
이름 : <%=name %><br>
<h1>EL을 이용</h1>
이름 : ${requestScope.name} <%-- <%= request.getAttribute("name")과 동일 %> --%>
<br>
이름 : ${name } <%-- requestScope를 생략할 수 있다 --%>
<br>
<h1>Session에 저장된 데이터 읽기</h1>
이름 : ${sessionScope.name }<br>
이름 : ${name }	<%-- request가 우선순위이기 때문에 키값이 같다면 request를 출력 --%>
</body>
</html>