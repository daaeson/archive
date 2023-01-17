<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="model" class="com.sist.model.BoardModel"/>
<%--
	출력 형식 : EL
	${출력물} => 일반 변수가 아니다
				request.getAttribute("key") => request.setAttribute("key","value")
					=> ${requestScope.key}
					=> 생략 가능 => ${key}
				session.getAttribute("key") => session.setAttribute("key","value")
					=> ${sessionScope.key}
	연산자
		산술연산자 => 문자열 결합 (+=)
					null 연산 => null은 0이다
					/ => div
					% => mod
		비교연산자 => 결과값 (true/false)
					== eq
					!= ne
					<  lt
					>  gt
					<= le
					>= ge
		논리연산자 => 결과값 (true/false)
					&& and
					|| or
					not
		삼항연산자
	
	<c:if test="${비교연산, 논리연산}">
	
	JSTL => JSP 파일 내 Java 코드를 최소화 (자바 제어문을 태그형으로 제작)
		core 
			=> <c:set> : 변수 설정
						 <c:set var="msg" value="Hello JSTL">
						 => request.setAttribute("msg","Hello JSTL");
			=> <c:out> : 화면 출력
						 <c:out values="Hello"> => JQuery, VueJS.. 자바스크립트에서 주로 사용
						 => out.println("Hello")
			=> <c:forEach> : for문
							 <c:forEach var="i" begin="1" end="10" step="1"> => step은 양수만 지정 가능(증가만 가능 감소 불가)
							 => for(int i=1; i<=10; i++) *** end로 지정된 숫자까지 포함
							 <c:forEach var="vo" items="${list}"> => 가장 많이 사용 => for-each(향상된 for문)
							 => for(VO vo:list)
			=> <c:choose> : 다중 조건문
							<c:choose>
								<c:when test="조건문"></c:when>
								<c:when test="조건문"></c:when>
								<c:when test="조건문"></c:when>
								<c:when test="조건문"></c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
			=> <c:if> => XML이기 때문에 꼭 닫는 태그를 작성해야 한다
						<c:if test="">
							처리문장
						</c:if>
						XML => DTD(태그,속성 정의)를 볼 수 있어야 한다
							   XML 단점 : 태그에 순서가 있다 (DTD에서 순서 정의)
							   XML 파싱 => JAXP, JAXB
							   			  DOM/SAX
							   JSON 파싱 => Rest
			=> <c:forTokens> : StringTokenizer
		format => Oracle SQL 문장에서 처리
			<fmt:formatDate>
			<fmt:formatNumber>
		fn => Model에서 처리 => 메소드 String
		
		<c:forEach>, <c:if>, <c:choose>, <c:redirect> => 대소문자 구분
--%>
<%
	model.boardListData(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
   margin-top:100px;
}
.row{
   width: 800px;
   margin: 0px auto;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<img src="qna.jpg" style="width: 500px; height: 300px;">
		</div>
		<div style="height: 5px"></div>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.jsp" class="btn btn-sm btn-danger">새 글</a>
					</td>
				</tr>
			</table>
			<table class="table">
				<tr class="warning">
					<th width="10%" class="text-center">번호</th>
					<th width="45%" class="text-center">제목</th>
					<th width="15%" class="text-center">이름</th>
					<th width="20%" class="text-center">작성일</th>
					<th width="10%" class="text-center">조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td width="10%" class="text-center">${vo.no }</td>
					<td width="45%">
						<c:if test="${vo.group_tab>0 }">
							<c:forEach var="i" begin="0" end="${vo.group_tab }">
								&nbsp;&nbsp;
							</c:forEach>
							<img src="re_icon.png">
						</c:if>
						<c:if test="${vo.subject==msg }">
							<span style="color:gray">${vo.subject }</span>
						</c:if>
						<c:if test="${vo.subject!=msg }">
							<a href="detail.jsp?no=${vo.no }">${vo.subject }</a>
						</c:if>
						&nbsp;
						<c:if test="${today==vo.dbday }">
							<sup><img src="new.gif"></sup>
						</c:if>
					</td>
					<td width="15%" class="text-center">${vo.name }</td>
					<td width="20%" class="text-center">${vo.dbday }</td>
					<td width="10%" class="text-center">${vo.hit }</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>









