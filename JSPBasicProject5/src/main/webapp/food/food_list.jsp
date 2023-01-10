<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
	// main.jsp?mode=1&cno=1
	String cno=request.getParameter("cno");
	// DAO 연결
	CategoryVO vo = dao.categoryInfoData(Integer.parseInt(cno));
	
	// 카테고리별 맛집 목록 읽기
	ArrayList<FoodVO> list=dao.category_food_list(Integer.parseInt(cno));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron">
		<h2 class="text-center"><%=vo.getTitle() %></h2>
		<h4 class="text-center"><%=vo.getSubject() %></h4>
	</div>
	<div class="row">
		<table class="table">
			<tr>
				<td class="text-center">
				<%
					for(FoodVO fvo:list)
					{
				%>
						
				<%	
					}
				%>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>










