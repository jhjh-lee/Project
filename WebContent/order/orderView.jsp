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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="${conPath }/css/orderView.css" rel="stylesheet">
<script>
	$(document).ready(function(){
		$('form').submit(function(){
			if($('.oName').val()==''){
		           alert("배송자이름을 입력하세요(필수)");
		           $('.oName').focus();
		           return false;
	       }
			if($('.oAddress').val()==''){
		           alert("배송자주소를 입력하세요(필수)");
		           $('.oAddress').focus();
		           return false;
	       }
			if($('.oTel').val()==''){
		           alert("배송자전화을 입력하세요(필수)");
		           $('.oTel').focus();
		           return false;
	       }
		});
	});
</script>		
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<form action="${conPath }/order.do" method="get">
			<div class="content">
				<div class="head">
				<h1>주문/결제</h1>
				</div>
				<div class="list">					
					<table class="table">
						<tr>
							<th class="th1">
								상품정보
							</th>
							<th class="th1">
								상품명
							</th>
							<th class="th1">
								가격
							</th>
							<th class="th1">
								수량
							</th>
						</tr>
						<c:forEach items="${cartList }" var="cartLists" >
						<tr>
							<td>
								<input type="hidden" name="pCode" value="${cartLists.pCode}">
								<input type="hidden" name="cost" value="${cartLists.cost}">
								<input type="hidden" name="cNt" value="${cartLists.cNt}">
								<img alt="상품이미지" src="${conPath }/productFileUp/${cartLists.pFilename}"> 
							</td>
							<td style="text-align: left;">
								${cartLists.pName}
							</td>
							<td >
								<fmt:formatNumber value="${cartLists.pPrice}" groupingUsed="true" /><b>원</b>
								
							</td>
							<td >
								${cartLists.cNt}
							</td>
							<td >
								<fmt:formatNumber value="${cartLists.cost}" groupingUsed="true" /><b>원</b>
							</td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="3" style="text-align: left;">						
								<h2 style="text-align: right;">상품 합계 :&emsp;&emsp;&emsp; &emsp;&emsp;&emsp;&emsp; &emsp;<fmt:formatNumber value="${Allsum }" groupingUsed="true" /><b>원</b></h2>
							</td>
						</tr>
					</table>
						<div class="head2">
						<h1>주문자정보</h1>
						</div>
					<div class="orderDetailContent">
						<table class="table2">
							<tr>
								<td class="th1">
									이름 <b>*</b>
								</td>
								<td class="th2">
									<input type="text" name="oName" class="oName" value="" style="width: 400px;">
								</td>
							</tr>
							<tr>
								<td class="th1">
									우편번호 <b>*</b>
								</td>
								<td class="th2">
									<input type="text" name="oPostalcode" class="oPostalcode" value="" style="width: 200px;">
								</td>
							</tr>
							<tr>
								<td class="th1">
									주소 <b>*</b>
								</td>
								<td class="th2">
									<input type="text" name="oAddress" class="oAddress" value="" style="width: 400px;"><br>
								</td>
							</tr>
							<tr>
								<td class="th1">
									휴대전화 <b>*</b>
								</td>
								<td class="th2">
									<input type="text" name="oTel" class="oTel" value="" style="width: 400px;">
								</td>
							</tr>
							<tr>
								<td class="th1">
									이메일 
								</td>
								<td class="th2">
									<input type="text" name="oEmail" class="oEmail" value="" style="width: 400px;">
								</td>
							</tr>
						</table>
					</div>
					<div class="head2">
						<h1>결제 정보</h1>
						</div>
					<div class="payment">
						<br>
						<input type="hidden" name="Allsum" value="${Allsum }">
						<h2 style="text-align: center;">상품 합계 :&emsp;&emsp;&emsp; &emsp;&emsp;&emsp;&emsp; &emsp;<fmt:formatNumber value="${Allsum }" groupingUsed="true" /><b>원</b></h2>
						<br>
						<div class="btn">
						<input type="submit" class="Btn" value="결제하기">
						<br>
						</div>
					</div>
				</div>
			</div>
		</form>
		</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	

</body>
</html>