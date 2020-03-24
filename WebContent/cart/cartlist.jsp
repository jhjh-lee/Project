<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
#wrap {
	margin: 0 auto;
	padding: 0 50px;
	border-bottom: 1px solid lightgray;
	overflow: hidden;
	text-align: center;
}

#head {
	width: 1100px;
	height: auto;
	line-height: auto;
	margin: 0 auto;
	padding: 0 50px;
	font-family: 'Noto Sans KR', sans-serif;
	overflow: hidden;
	text-align: left;
}

#head h1 {
	padding-top: 40px;
	font-size: 40px;
}

#wrap .content .list {
	margin: 40px auto;
	width: 1300px;
	padding: 0 150px;
}

#wrap .content .list .table {
	width: 1100px;
	margin: 0 auto;
	text-align: center;
	border-bottom: 1px solid #dfdfdf;
	border-collapse: collapse;
	font-size: 13px;
}

#wrap .content .list .table tr {
	height: 50px;
}

#wrap .content .list .table .th1 {
	width: 130px;
	text-align: left;
}

#wrap .content .list .table .th3 {
	width: 10px;
}

#wrap .content .list .table td {
	border-top: 1px solid #dfdfdf;
	border-bottom: 1px solid #dfdfdf;
	box-sizing: border-box;
	padding: 10px;
}

#wrap .content .list p {
	width: 1100px;
	height: 50px;
	text-align: right;
	margin-top: 20px;
	margin-right: 40px;
}

.paging {
	margin: 0 auto;
	text-align: center;
	height: 50px;
	line-height: 20px;
}

.paging span {
	padding: 0 7px;
}

.table img {
	width: 150px;
}

.delete, .select {
	cursor: pointer;
}

.Btn {
	width: 150px;
	height: 50px;
	color: #1B4B31 !important;
	background-color: transparent !important;
	border-color: #1B4B31 !important;
	border: 1px solid green;
}

.Btn:hover {
	background-color: #cccccc;
	opacity: 0.5;
	cursor: pointer;
}

.bot {
	border-top: 2px solid #dfdfdf;
}
</style>

<script>
	
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div id="head">
		<h1>장바구니</h1>
	</div>
	<div id="wrap">
		<div class="content">
			<div class="list">
				<table class="table">
					<tr>
						<th class="th1">상품정보</th>
						<th class="th1">수량</th>
						<th class="th1">가격</th>
					</tr>
					<c:if test="${empty cartList }">
						<tr>
							<td colspan="3">장바구니가 비었습니다.</td>
						</tr>
					</c:if>

					<c:forEach items="${cartList }" var="cartLists">
						<form action="cartModify.do" method="get">
							<tr>

								<td>
									<a href="${conPath }/productContentView.do?pCode=${cartLists.pCode}">
									<img alt="상품이미지" src="${conPath }/productFileUp/${cartLists.pFilename}"></a>
								</td>
								<td style="text-align: left;">
									${cartLists.pName}
								</td>
								<td>
									<fmt:formatNumber value="${cartLists.pPrice}" groupingUsed="true" /><b>원</b>
								</td>
								
							</tr>
						</form>
					</c:forEach>
					<tr>
						<td colspan="3" style="text-align: left;">
							<h2 style="text-align: right;">
								상품 합계 :&emsp;&emsp;&emsp; &emsp;&emsp;&emsp;&emsp; &emsp;
								<fmt:formatNumber value="${Allsum }" groupingUsed="true" />
								<b>원</b>
							</h2>
						</td>
					</tr>
				</table>
				<span class="bot">
					<p>
						<input type="button" value="주문하기" class="Btn"
							onclick="location.href='${conPath }/orderView.do'">
					</p>
				</span>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>