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
<link href="${conPath }/css/productWriteview.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(document).ready(function(){
		$('form').submit(function(){
			if($('.pName').val()==''){
		           alert("상품명을입력하세요");
		           $('.pName').focus();
		           return false;
		       }
			if($('.pPrice').val()==''){
		           alert("가격 입력");
		           $('.pPrice').focus();
		           return false;
		       }
			if($('.pType').val()==''){
		           alert("상품타입 선택");
		           $('.pType').focus();
		           return false;
		       }
			if($('.pFilename').val()==''){
		           alert("상품이미지 입력바람");
		           $('.pFilename').focus();
		           return false;
		       }
			if($('.pStock').val()==''){
		           alert("재고 입력");
		           $('.pStock').focus();
		           return false;
		       }
		});
	});
</script>	

</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
			<form action="${conPath }/productWrite.do" method="post" enctype="multipart/form-data">
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								상품코드
							</td>
							<td class="th2">
								<input type="text" name="pCode" class="pCode" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								상품명
							</td>
							<td class="th2">
								<input type="text" name="pName" class="pName" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								상품설명
							</td>
							<td class="th2">
								<input type="text" name="pDes" class="pDes" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								상품이미지
							</td>
							<td class="th2">
								<input type="file" name="pFilename" class="pFilename" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								가격
							</td>
							<td class="th2">
								<input type="text" name="pPrice" class="pPrice" style="width: 100px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								재고
							</td>
							<td class="th2">
								<input type="text" name="pStock" class="pStock" style="width: 100px;">
							</td>
						</tr>
					</table>
					<p class="subtn">
							<input type="submit" value="상품 등록" class="upbtn">
							&nbsp;
							<input type="button" value="취소" class="resetbtn"
								onclick="location.href='${conPath}/main/main.jsp'">
					</p>
				</div>
				</form>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>