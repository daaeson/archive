<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	Date date=new Date();
%>
<c:set var="today" value="<%=date %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>날짜 변환</h1>
	<h2>자바</h2>
	<%=date %><br>
	<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
	%>
	<%=today %>
	<h2>JSTL</h2>
	<fmt:formatDate value="${today }" pattern="yyyy-MM-dd"/>
	<h1>숫자 변환</h1>
	<%
		long a = 12345678910l;
		DecimalFormat d= new DecimalFormat("###,###,###");
		String num = d.format(a);
	%>
	<%=num %><br>
	<c:set var="num" value="<%=a %>"/>
	$<fmt:formatNumber value="${num }" type="number"/><br>
	<fmt:formatNumber value="${num }" type="currency" currencySymbol="$"/>
</body>
</html>