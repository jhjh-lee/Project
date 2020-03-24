<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
		<jsp:include page="header.jsp" />
	<div id="content_form">
	
		<div class="visual">
			<div>
				<img src="${conPath }/images/main.png">
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
