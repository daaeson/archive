<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	// 1. 사용자 요청값
	String no=request.getParameter("no");
	BoardDAO dao=new BoardDAO();
	BoardVO vo=dao.boardUpdateData(Integer.parseInt(no));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	width: 600px;
	margin: 0px auto;
}
h1{
	text-align: center
}
</style>
</head>
<body>
	<div class="container">
		<h1>수정하기</h1>
		<div class="row">
		<form method="post" action="update_ok.jsp">	<!-- form 내부 데이터만 넘어간다 -->
			<table class="table">
				<tr>
					<th class="text-right success" width=20%>이름</th>
					<td width=80%>
						<input type=text name=name size=15 class="input-sm" value="<%=vo.getName()%>" required>
						<input type=hidden name=no value="<%=vo.getNo() %>">
						<!-- hidden : 데이터 값을 전송하되 사용자에게 감추는 경우 // 위치 : table 내 td 어느 곳에나-->
					</td>
				</tr>
				<tr>
					<th class="text-right success" width=20%>제목</th>
					<td width=80%>
						<input type=text name=subject size=50 class="input-sm" value="<%=vo.getSubject()%>" required><!-- "" 없으면 '자유게시판 만들기' 중 '자유게시판'만 출력 -->
					</td>
				</tr>
				<tr>
					<th class="text-right success" width=20%>내용</th>
					<td width=80%>
						<textarea rows="10" cols="50" name=content><%=vo.getContent()%></textarea>
					</td>
				</tr>
				<tr>
					<th class="text-right success" width=20%>비밀번호</th>
					<td width=80%>
						<input type=password name=pwd size=10 class="input-sm" required>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=submit class="btn btn-sm btn-info" value="수정">
						<input type=button class="btn btn-sm btn-warning" onclick="javascript:history.back()" value="취소">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>