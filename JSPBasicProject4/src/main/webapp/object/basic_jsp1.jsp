<%--
	171page (내장 객체) ***** => Spring
	
	1) 내부객체 (내장객체, 기본객체)
		=> 미리 객체를 생성한 다음 사용 가능
		=> 9개
		=> JSP 파일 => _jspService()에 필요한 코딩을 하는 파일

	  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
	  {
	  	final javax.servlet.jsp.PageContext pageContext;
	    javax.servlet.http.HttpSession session = null;
	    final javax.servlet.ServletContext application;
	    final javax.servlet.ServletConfig config;
	    javax.servlet.jsp.JspWriter out = null;
	    final java.lang.Object page = this;
	    javax.servlet.jsp.JspWriter _jspx_out = null;
	    javax.servlet.jsp.PageContext _jspx_page_context = null;
	    
	    // JSP 코딩 위치 (현재 메소드에 코딩하고 있음)
	  }
	  
	*** request
	*** response
	*** pageContext
	*** session
	*** application
		config
	*** out
		page
		exception

	_jspService() => 브라우저 화면에 출력
	{
		JSP 파일
	}
	
	
		1) 요청 관련 데이터 관리, 사용자의 브라우저 정보, 추가 기능 => request
			HttpServletRequest
			= 기능
			  1. 브라우저 정보(서버 정보)를 갖고 있다
			  	 http://localhost/JSPBasicProject4/object/basic_jsp1.jsp => URL
			  	 ______서버 정보___ __________사용자 요청 정보(URI)___________
			  	 				  ---------------- ContextPath
			  	  = getRequestURL() *****
			  	  = getRequestURI() *****
			  	  = getContextPath() *****
			  	  = getRemoteAddr() ***** ==> 사용자의 IP (조회수)
			  	  = getServerPort() ==> 80
			  	  = getServerInfo() ==> localhost
			  	  
			  2. 요청 관련 정보를 갖고 있다
			  	 사용자가 보내준 데이터 (단일 데이터) : getParameter()
			  	 사용자가 보내준 데이터 (다중 데이터) => checkbox : getParameterValues()
			  	 사용자가 보내준 데이터 Parameter : getParameterNames()
			  	 *** 데이터 전송 => 값을받는파일명?변수명(key)=값(value)
			  					 a.jsp?no=10
			  					  => request.getParameter("no");
			  					 a.jsp?id=admin&pwd=1234
			  					  => request.getParameter("id");
			  					  => request.getParameter("pwd");
			  					  	 *** 오직 String으로만 값을 받을 수 있다
			  					 a.jsp?hobby=a&hobby=b&hobby=c
			  					  => String[] request.getParameterValues("hobby")
			  	 브라우저(1byte) => 자바(2byte) : 디코딩
			  	 자바(2byte) => 브라우저(1byte) : 인코딩
			  	 
			  	 https://www.google.com/search?q=%EC%9E%90%EB%B0%94&oq=%EC%9E%90%EB%B0%94&aqs=chrome..69i57j69i59j0i433i512j69i59j0i131i433i512j69i60l2j69i61.6921j0j7&sourceid=chrome&ie=UTF-8				  	
				 자바 -> %EC%9E%90%EB%B0%94 => byte[]로 변경 (인코딩)
				 -> 자바 => 한글 변환 byte[] => String으로 변경 (디코딩)
				 1) byte[] => String (브라우저에서 값을 전송받는 경우) : 디코딩
				 	request.setCharacterEncoding("UTF-8"); => POST 방식에서 디코딩 하는 방식
				 2) String => byte[] (브라우저로 값을 전송) : 인코딩
				 	URLEncoder.encoder(데이터,"UTF-8"); => 자바/자바스크립트 동일한 방식 사용
				 --------------------------------------------- 처리방식(GET/POST)에 따라 다르게 코딩 => window10 (자동 처리) => server.xml 63row => URIEncodeing ="UTF-8"
			  
			  3. 추가 기능 => 사용자가 보내준 데이터 + 필요한 데이터를 추가하여 전송 (MVC,Spring)
			  		setAttribute(키,값) => Object를 첨부 (ArrayList)
			  		getAttribute(키)
			  		
		2) 응답 => response
			HttpServletResponse
			= JSP 한 개의 파일에서 1번만 response 가능(html/cookie 둘 중 어느 걸 보낼지 고민 필요)
			= 기능
			  1. HTML 파일 전송
			  2. Cookie 전송
			= setHeader() : 파일 업로드, 다운로드 시 사용
			= sendRedirect() : 서버에서 다른 파일로 이동 / forward()
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 전송값 받기 (request) -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	width: 500px;
	margin: 0px auto;
}
h1{
	text-align: center
}
</style>
<%--
	 <값 받을 때>
	 
	 파일명?
	
	 <form action="파일명">
	 
	 $.ajax({
	 	url:'a.jsp'
	 })
	 
	 axios.get('URL')
 --%>
</head>
<body>
	<div class="container">
		<h1>개인정보 전송</h1>
		<div class="row">
		<form method=post action="output.jsp">
		<!-- 
			method : get/post
			action : 받는 파일 지정
			전송되는 데이터 : input / select / textarea => 
		 -->	
			<table class="table">
				<tr>
					<th class="text-center" width="20%">이름</th>
					<td width=80%>
						<input type=text name="name" class="input-sm" size=15>
						<!-- request.getParameter("name") : 입력된 값을 읽어온다 
							 ?name=홍길동&sex=남자
							 ------------------
							  POST/GET => 값을 전송하거나 받는 경우 (Map) => 키/값
						-->
					</td>
				</tr>
				<tr>
					<th class="text-center" width="20%">성별</th>
					<td width=80%>
						<input type=radio name="sex" checked value="남자"> 남자
						<input type=radio name="sex" value="여자"> 여자
						<!-- name이 동일 (그룹), 반드시 보낼 데이터를 설정한다 (value) -->
					</td>
				</tr>
				<tr>
					<th class="text-center" width=20%>지역</th>
					<td width=80%>
						<select name="loc" class="input-sm">
							<option>서울</option>
							<option>경기</option>
							<option>인천</option>
							<option>대전</option>
							<option>광주</option>
							<option>대구</option>
							<option>부산</option>
							<!-- 
								<option>서울</option>		=> 넘기는 값 : 서울
								<option value="seoul">서울</option> => 넘기는 값 : seoul *** 권장
							-->
						</select>
					</td>
				</tr>
				<tr>
					<th class="text-center" width=20%>취미</th>
					<td width=80%>
						<input type=checkbox value="등산" name=hobby>등산
						<input type=checkbox value="캠핑" name=hobby>캠핑
						<input type=checkbox value="독서" name=hobby>독서
						<input type=checkbox value="여행" name=hobby>여행
						<input type=checkbox value="수영" name=hobby>수영
						<%-- 전송값은 value에 담고, name은 동일해야 한다 => String[] getParameterValues("hobby") --%>
					</td>
				</tr>
				<tr>
					<th width=20% class="text-center">소개</th>
					<td width=80%>
						<textarea rows="8" cols="35" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan=2 class="text-center">
						<input type=submit value="전송" class="btn btn-sm btn-danger">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>













