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
<link href="${conPath }/css/QnaList.css" rel="stylesheet">		
</head>
<body>

<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<div class="head">
				<h1>Q&A</h1>
				</div>
				<div class="list">
					<table class="table">
						<tr>
							<th class="th1">
								번호
							</th>
							<th class="th2" >
								제목
							</th>
							<th class="th1">
								작성자
							</th>
							<th class="th1">
								작성일
							</th>
						</tr>
					<c:if test="${empty QnaList }">
						<tr>
							<td colspan="4">
								글이없습니다.
							</td>
						</tr>
					</c:if>
					<c:forEach items="${QnaList}" var="QnaLists">
						<tr>
							<td>
								${QnaLists.qNo }
							</td>
							<td style="text-align: left; padding-left: 50px;">
								<a href="${conPath }/QnaContentview.do?qNo=${QnaLists.qNo}&pageNum=${pageNum}">
								${QnaLists.qTitle }</a>
							</td>
							<td>
								<c:if test="${not empty QnaLists.mId}">
									${QnaLists.mId}
								</c:if>
								<c:if test="${not empty QnaLists.aId}">
									<b style="font-weight: bold;">관리자</b>
								</c:if>
							</td>

							<td>
								${QnaLists.qRdate}
							</td>
						
						</tr>
					</c:forEach>
					</table>
					<p><a href="${conPath }/QnaWriteview.do?pageNum=${pageNum}">글쓰기</a></p>
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/QnaList.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/QnaList.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/QnaList.do?pageNum=${endPage+1}">  
						   			다음
						   		</a>
						</c:if>
					</div>			
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>