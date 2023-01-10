<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<%--
	out : JspWriter
	1) 출력 스트림 : 브라우저에서 읽어가는 메모리(신뢰성)
		=> <%= %> : 메모리에 변수가 자바 관련일 때 출력 시 사용
	2) 출력 메모리 크기 결정 : buffer="8kb"
	3) 주요 메소드
		getBufferSize() => 메모리 총크기
		getRemaining() => 사용 후에 남아있는 버퍼
		println(), print()
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int size=out.getBufferSize();
		int re=out.getRemaining();
		out.println("total : "+size);
		out.println("remain : "+re);
	%>
	<br>
	총 버퍼 크기 : <%="total : "+size %><br>
	남아있는 버퍼 : <%="remain : "+re %><br>
	사용중인 버퍼 : <%= size-re %>
</body>
</html>




