<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL => 연산자
	1) 산술연산자
		${"10 "+10} => 오류
		${null+10} => 10 (null은 0으로 인식)
		
		${10 / 5} = 2
		${10 div 5} = 2
		${10 / 3} = 3.3333 ...
		
		${10 % 3} = 1
		${10 mod 3} =1
	2) 비교연산자
	3) 논리연산자
	4) 삼항연산자
	5) Empty
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL 연산자</h1>
<h2>산술연산자</h2>
&#36;{10+10}=${10+10 }<br>
&#36;{"10"+10}=${"10"+10 }<br>
&#36;{null+100}=${null+100 }<%-- null은 0으로 취급 --%><br>
&#36;{"Hello"+="EL"}=${"Hello"+="EL" }	<%-- 문자열 결합은 += 을 사용한다 --%><br>
&#36;{10-5}=${10-5 }<br>
&#36;{"10"-5}=${"10"-5 }<br>
&#36;{"10"*5}=${"10"*5 }<br>
&#36;{10/3}=${10/3 }<br>
&#36;{10%3}=${10%3 }<br>
<h4>문자열 결합은 +=, null은 자동으로 0으로 인식</h4>

<h2>비교연산자</h2>
&#36;{10==10}=${10==10 }<br>
&#36;{10 eq 10}=${10 eq 10 }<br>
&#36;{"hong"=="hong"}=${"hong"=="hong" }<br>
&#36;{"hong" ne "hong"}=${"hong" ne "hong" }<br>
&#36;{"hong" < "hong"}=${"hong" lt "hong" }<br>
&#36;{"hong" <= "hong"}=${"hong" le "hong" }<br>
&#36;{"hong" > "hong"}=${"hong" gt "hong" }<br>
&#36;{"hong" >= "hong"}=${"hong" ge "hong" }<br>
<h4>==(eq), !=(ne), (lt), (gt), (le) (ge)<br>
문자열, 날짜, 숫자가 동일하다</h4>

<h2>논리연산자</h2>
&#36;{(10==10)&&(10!=10)}=${(10==10) and (10!=10)}<br>
&#36;{(10==10)||(10!=10)}=${(10==10) or (10!=10)}<br>
&#36;{(10==10)&& not(10!=10)}=${(10==10) and not(10!=10)}<br>
<h4>&&(and:직렬),||(or:병렬),not</h4>

<h2>삼항연산자</h2>
&#36;{(10==10)?'A':'B'}=${(10==10)?'A':'B'}<br>
<%-- page=${curpage>1?curpage-1:curpage} : style 적용 --%>
<h4>조건?값1:값2 = if~else => 조건이 true일 때 값1, 조건이 false일 때 값2 => 페이지 변경할 때 사용</h4>

<h2>Empty연산자</h2>
<% request.setAttribute("name", ""); %>
&#36;{empty name}=${empty name }<br>
&#36;{name=""}=${name=="" }
<h4>값이 있으면 true, 값이 없으면 false</h4>
</body>
</html>













