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
<link href="${conPath }/css/orderContentview.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<h2>주문상세</h2>
				<div class="list">
					주문번호 :${ordersMember.oNo } / 주문날짜 : ${ordersMember.oRdate }
					<table class="table">
						<tr>
							<th class="th1">
								이미지
							</th>
							<th class="th2" >
								상품정보
							</th>
							
							<th class="th1">
								수량
							</th>
							<th class="th1">
								합계
							</th>
						</tr>
						<c:forEach items="${orderDetailList }" var="orderDetailLists" >
						<tr>
							<td>
								<img alt="상품이미지" src="${conPath }/productFileUp/${orderDetailLists.pFilename}">
							</td>
							<td style="text-align: left;">
								${orderDetailLists.pName}
							</td>
							<td>
								${orderDetailLists.cNt}
							</td>
							<td >
								<b>￦</b><fmt:formatNumber value="${orderDetailLists.cost}" groupingUsed="true" /> 
							</td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="6" style="text-align: left;">
								<h2 style="text-align: right;">전체 금액 :<b>￦</b><fmt:formatNumber value="${Allsum }" groupingUsed="true" /> </h2>
							</td>
						</tr>
					</table>
					<br>
					<div class="orderDetailContent">
						<table class="table2">
							<tr>
								<td class="th1">
									배송 정보
								</td>
								<td class="th1">
									<p class="required">
								</td>
							</tr>
							<tr>
								<td class="th1">
									받으시는 분 
								</td>
								<td class="th2">
									${ordersMember.oName }
								</td>
							</tr>
							<tr>
								<td class="th1">
									주소 
								</td>
								<td class="th2">
									${ordersMember.oAddress }
								</td>
							</tr>
							<tr>
								<td class="th1">
									휴대전화 
								</td>
								<td class="th2">
									${ordersMember.oTel }
								</td>
							</tr>
						</table>
					</div>
					<div class="payment">
						
					</div>
				</div>
			</div>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	

</body>
</html>