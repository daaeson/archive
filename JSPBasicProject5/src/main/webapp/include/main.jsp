<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mode=request.getParameter("mode");
	if(mode==null)
		mode="0";
	int index=Integer.parseInt(mode);
	String jsp="";
	// main.jsp 안에서 화면을 변경하는 위치
	switch(index)
	{
		case 0:
			jsp="../food/category.jsp";
			break;
		case 1:
			jsp="../food/food_list.jsp";
			break;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.main{
	margin-top: 50px;
}
.row{
	width:800px;
	margin: 0px auto;
}
h1{
	text-align: center
}
</style>
</head>
<body>
	<%
		pageContext.include("header.jsp");	// <jsp:include page="header.jsp">
	%>
	<div class="container main">
		<div class="col-sm-3">
			<%
				pageContext.include("login.jsp");
			%>
		</div>
		<div class="col-sm-9">
			<%
				pageContext.include(jsp); // include를 통해 main.jsp 에 포함된다 => main.jsp가 경로가 된다 => food 폴더의 다른 파일에 접근하고자 할 땐 ../food/로 경로 이동해야 함
			%>
		</div>
	</div>
</body>
</html>

