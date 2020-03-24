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
<link rel="stylesheet" href="${conPath }/css/default.css">
<link rel="stylesheet" href="${conPath }/css/header.css">
</head>
<body>
<header>
	<c:if test="${empty member and empty amember}"> <%-- 로그인 전 화면 --%>
		<div class="skip" >
			<a href="${conPath }/main.do"><span>MAIN</span></a>
		</div>
		<div class="lnb">
			<ul>
				<li><a href="${conPath }/mainView.do"><span>MAIN</span></a></li>
				<li><a href="${conPath }/producList.do"><span>SHOP</span></a></li>
				<li><a href="${conPath }/About.do"><span>ABOUT</span></a></li>
				<li><a href="${conPath }/member/content.jsp"><span>CONTACT</span></a></li>
				<li><a href="${conPath }/QnaList.do"><span>Q&A</span></a></li>
				<li><a href="${conPath }/noticeList.do"><span>NOTICE</span></a></li>
			</ul>
		</div>
		<div class="spot">
			<ul>
				<li><a href="${conPath }/member/join.jsp">회원가입</a></li>
				<li><a href="${conPath }/member/login.jsp">로그인</a></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty member and empty amember}"> <%-- 사용자 모드 로그인 화면--%>
		<div class="skip" onclick="location.href='${conPath}/main.do'">
		</div>
		<div class="lnb">
			<ul>
				<li><a href="${conPath }/mainView.do"><span>MAIN</span></a></li>
				<li><a href="${conPath }/producList.do"><span>SHOP</span></a></li>
				<li><a href="${conPath }/About.do"><span>ABOUT</span></a></li>
				<li><a href="${conPath }/member/content.jsp"><span>CONTACT</span></a></li>
				<li><a href="${conPath }/QnaList.do"><span>Q&A</span></a></li>
				<li><a href="${conPath }/noticeList.do"><span>NOTICE</span></a></li>
			</ul>
		</div>
		<div class="spot">
			<ul>
				<li><a href="${conPath }/memberModifyView.do">정보수정</a></li>
				<li><a href="${conPath }/orderListView.do">주문리스트</a></li>
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${empty member and not empty amember}"> <%-- 관리자 모드 로그인 화면--%>
		<div class="skip" onclick="location.href='${conPath}/main.do'">
		</div>
		<div class="lnb">
			<ul>
				<li><a href="${conPath }/mainView.do"><span>MAIN</span></a></li>
				<li><a href="${conPath }/producList.do"><span>SHOP</span></a></li>
				<li><a href="${conPath }/About.do"><span>ABOUT</span></a></li>
				<li><a href="${conPath }/member/content.jsp"><span>CONTACT</span></a></li>
				<li><a href="${conPath }/QnaList.do"><span>Q&A</span></a></li>
				<li><a href="${conPath }/noticeList.do"><span>NOTICE</span></a></li>
			</ul>
		</div>
		<div class="spot">
			<ul>
				<li><a href="${conPath }/modifyView.do"></a></li>
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
			</ul>
		</div>
	</c:if>
</header>
</body>
</html>