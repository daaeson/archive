<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	<c:if> => <c:if test="조건문"> => <c:else>는 존재하지 않는다
	<c:choose>
	
	= 화면 출력
	  request/session 저장 => Model 클래스
	  <c:forEach>
	  <c:forTokens>
	  <c:if>, <c:choose>
	  <fmt:formatData> ==> Oracle
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int sex=1;
%>
<%--
	JSTL : XML 형식으로 제작
			=> 열고 닫는 태그가 명확해야 한다 <c:set></c:set>  <c:set/>
			=> 대소문자 구분 <C:Set>(X)
			=> 속성값은 반드시 ""
			
 --%>
<c:set var="sex" value="<%=sex %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>&lt;if test=""&gt;</h1>
	<%
		if(sex==1)
		{
	%>
			남자
	<%
		}
		else
		{
	%>
			여자
	<%
		}
	%>
	<h2>JSTL = if</h2>
	<c:if test="${sex==1 }">
		남자
	</c:if>
	<c:if test="${sex!=1 }">
		여자
	</c:if>
	
	<h1>다중 조건문&lt;c:choose&gt;</h1>
	<%
		int star=3;
	%>
	<c:set var="star" value="<%=star %>"/>
	<h2>자바 이용</h2>
	<%
		if(star==1)
		{
	%>
			★☆☆☆☆
	<%		
		}
		else if(star==2)
		{
	%>
			★★☆☆☆
	<%
		}
		else if(star==3)
		{
	%>
			★★★☆☆
	<%
		}
		else if(star==4)
		{
	%>
			★★★★☆
	<%
		}
		else if(star==5)
		{
	%>
			★★★★★
	<%
		}
		else
		{
	%>
			☆☆☆☆☆
	<%
		}
	%>
	<h2>JSTL</h2>
	<c:choose>
		<c:when test="${star==1 }">★☆☆☆☆</c:when>
		<c:when test="${star==2 }">★★☆☆☆</c:when>
		<c:when test="${star==3 }">★★★☆☆</c:when>
		<c:when test="${star==4 }">★★★★☆</c:when>
		<c:when test="${star==5 }">★★★★★</c:when>
		<c:otherwise>☆☆☆☆☆</c:otherwise>
	</c:choose>
</body>
</html>