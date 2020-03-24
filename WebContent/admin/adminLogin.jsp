<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/login.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<br>
		<br>
		<br>
		<br>
	<div id="wrap_all">
	<div id="wrap">
	<form action="${conPath}/adminLogin.do" method="post">
		<table>
			<h1>Admin Log-in</h1>
			<br>
			<br>
			<tr><th>ID</th>
				<td><input type="text" name="aId" required="required" autofocus="autofocus" placeholder="Admin name"></td>
			</tr>
			<tr><th>PW</th>
				<td><input type="password" name="aPw" required="required" placeholder="Password"></td>
			</tr>
		</table>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<p class="subtn">
							<input type="submit" value="로그인" class="loginbtn">
							&nbsp;
							<input type="button" value="취소" class="resetbtn"
								onclick="location.href='${conPath}/main/main.jsp'">
			</p>
	</form>
</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>