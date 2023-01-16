<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="model" class="com.sist.model.SeoulModel"/>
<%
	model.locationListData(request, response);
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
   width: 960px;
   margin: 0px auto;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<c:forEach var="vo" items="${list }">
			<div class="col-md-3">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="${vo.poster }" style="width:260px;height: 260px">
			        <div class="caption">
			          <p style="font-size: 9px;font-weight: bold">${vo.title }</p>
			        </div>
			      </a>
			    </div>
			  </div>
		</c:forEach>
	</div>
		<%-- <div class="jumbotron">
			<h3 class="text-center">${vo.title }</h3> key와 매칭
			<h4 class="text-center">${vo.getSubject() }</h4>
		</div>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						
							request.setAttribute("list",list)
							=> ArrayList<FoodVO> list=(ArrayList<FoodVO>)request.getAttribute("list");
							   for(FoodVO vo:list)
							   {
							   }
						
						<c:forEach var="vo" items="${list }">
							<table class="table">
								<tr>
									<td class="text-center" width=30% rowspan="4">
										<a href="food_detail.jsp?fno=${vo.fno }"><img src="${vo.poster }" style="width: 310px; height: 270px"></a>
									</td>
									<td width="70%">
										<h4><a href="food_detail.jsp?fno=${vo.fno }">${vo.name }</a>&nbsp;<span style="color:orange">${vo.score }</span></h4>
									</td>
								</tr>
								<tr>
									<td width=70%>${vo.address }</td>
								</tr>
								<tr>
									<td width=70%>${vo.tel }</td>
								</tr>
								<tr>
									<td width=70%>${vo.type }</td>
								</tr>
							</table>
						</c:forEach>
					</td>
				</tr>
			</table>
		</div> --%>
	</div>
</body>
</html>