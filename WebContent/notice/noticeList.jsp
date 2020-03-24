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
<style>
	.head{
		text-align: left;
		padding: 30px 100px 0 0;
		font-size: 15px;
	}
</style>			
</head>
<body>
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty noticeModiFyResult}">
		<script>
			alert('${noticeModiFyResult}');
		</script>
	</c:if>
	<c:if test="${not empty noticewriteResult}">
		<script>
			alert('${noticewriteResult}');
		</script>
	</c:if>
	<c:if test="${not empty noticeDeleteResult}">
		<script>
			alert('${noticeDeleteResult}');
		</script>
	</c:if>
	
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<div class="head">
					<h2>Notice</h2>
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
					<c:if test="${empty noticeList }">
						<tr>
							<td colspan="4">
								글이없습니다.
							</td>
						</tr>
					</c:if>
					<c:forEach items="${noticeList}" var="nLists">
						<tr>
							<td>
								<c:if test="${not empty nLists.nNo }">
								공지
								</c:if>
							</td>
							<td style="text-align: left; padding-left: 50px;">
								<a href="${conPath }/noticeContent_view.do?nNo=${nLists.nNo}&pageNum=${pageNum}">
								${nLists.nTitle }</a>
							</td>
							<td>
								${nLists.aName}
							</td>
							<td>
								${nLists.nRdate}
							</td>
						
						</tr>
					</c:forEach>
					</table>
					
					<c:if test="${not empty admin }">
						<p><a href="${conPath }/noticeWrite_view.do?pageNum=${pageNum}">글쓰기</a></p>
					</c:if>
					
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/noticeList.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/noticeList.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/noticeList.do?pageNum=${endPage+1}">  
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