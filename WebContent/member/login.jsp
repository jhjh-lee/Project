<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${conPath }/css/default.css">
<link rel="stylesheet" href="${conPath }/css/login.css">
<script>
	$(document).ready(function(){
		$('form').submit(function(){
			if($('.mId').val()==''){
		           alert("ID를 입력하세요");
		           $('.mId').focus();
		           return false;
		       }
			if($('.mPw').val()==''){
		           alert("PW를 입력하세요");
		           $('.mPw').focus();
		           return false;
		       }
		});
	});
</script>	
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty joinResult }">
		<script>
			alert('${joinResult}');
		</script>
	</c:if>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div id="wrap_all">
	<div id="wrap">
	<form action="${conPath }/login.do" method="post">
			<h1>Log-in</h1>
			<br>
			<br>
			<table>
			<tr>
				<th>
					아이디
				</th>
				<td>
				<input type="text" name="mId" value="${mId }" required="required" placeholder="User name">
				</td>
			</tr>
			<tr>
				<th>
					비밀번호
				</th>
				<td>
				<input type="password" name="mPw" required="required" placeholder="Password">
				</td>
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
















