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
<link href="${conPath }/css/QnaContentview.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
			<h2>${noticeContentView.nNo }</h2>
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								${noticeContentView.nTitle }
							</td>
						</tr>
						<tr>
							<td class="th1">
								작성자
							</td>
							<td class="th2">
								${noticeContentView.aName }
							</td>
						</tr>
						<tr>
							<td class="th1" >
								작성일  <span>${noticeContentView.nRdate }</span>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="noticeText">
									<pre>${noticeContentView.nContent }</pre>
								</div>
							</td>
						</tr>
					</table>
					
					<p>
					<c:if test="${not empty admin }">
						<a href="${conPath }/noticeModifyview.do?nNo=${noticeContentView.nNo}">글 수정</a>
						<a href="${conPath }/noticeDelete.do?nNo=${noticeContentView.nNo}">글 삭제</a>
					</c:if>
						<a href="${conPath }/noticeList.do?pageNum=${param.pageNum}">리스트</a>
					</p>
						
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>