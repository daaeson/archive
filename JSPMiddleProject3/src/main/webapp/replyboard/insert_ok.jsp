<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="com.sist.model.BoardModel"/>
<%
	model.boardInsert(request, response); // Controller가 호출하는 방식으로 변경
%>
<%--
	화면이동 (list.jsp로 이동) 
	sendRedirect("list.jsp")
--%>
<c:redirect url="list.jsp"/>