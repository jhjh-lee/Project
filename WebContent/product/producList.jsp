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
<link href="${conPath }/css/productList.css" rel="stylesheet">			
</head>
<body>	
<jsp:include page="../main/header.jsp"></jsp:include>

		<div id ="wrap">
			<div class="content">
				<h2>SHOP</h2>
				<div class="list">
					<table class="table">
						<tr>
						<c:forEach items="${productList }" var="pLists" varStatus="idx">
							<td>
								<div class="product">
									<ul>
										<li><a href="${conPath }/productContentView.do?pCode=${pLists.pCode}">
										<img src="${conPath }/productFileUp/${pLists.pFilename}"></a></li>
										<li>${pLists.pName}</li>
										<li><fmt:formatNumber value="${pLists.pPrice}" groupingUsed="true" /> <b>원</b></li>
									</ul>
								</div>
							</td>
							<c:if test="${(idx.index)%3 eq 2 }"></tr><tr></c:if>
						</c:forEach>
					</table>
					<tr><td class="pup">
					<c:if test="${not empty admin }">
						<p><a href="${conPath }/producWriteview.do">상품 등록</a></p>
					</c:if>
					</td></tr>
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
							 	<a href="${conPath }/producList.do?pageNum=${startPage-1}"> 
							 		이전
							 	</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<span><b>  ${i } </b></span>
							</c:if>
							<c:if test="${i != pageNum }">
								 <span><a href="${conPath }/producList.do?pageNum=${i}"> ${i } </a></span>
							</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
						   		<a href="${conPath }/producList.do?pageNum=${endPage+1}">  
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