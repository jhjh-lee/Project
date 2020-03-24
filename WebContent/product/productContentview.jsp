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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="${conPath }/css/productContentview.css">

<script>
	$(document).ready(function(){
		
		$('#delete').click(function(){
			var confirmChk = confirm("정말 삭제 하시겠습니까?");
				if(confirmChk == true){
					location.href="${conPath }/productDelete.do?pCode=${productContentView.pCode}";
				}else{
					
				}
		});
		
		$('#cart').click(function(){
			console.log(cNt + "//TEST");
			var cNt = $('.cNt').val();
			location.href='${conPath}/cartInsert.do?pCode=${productContentView.pCode }&cNt='+cNt;	
		});
		
		$('#modi').click(function(){
			location.href='${conPath}/producModifyview.do?pCode=${productContentView.pCode }';	
		});
		
			/* if(${not empty member}){
			}else{
				alert('로그인후 이용하세요');
				return false;
			}*/ 
	
	});
</script>		
</head>
<body>
	
<jsp:include page="../main/header.jsp"></jsp:include>
	<form action="${conPath }/cartInsert.do" method="post" >
		<div id ="wrap">
			<div class="content">
				<h2>${productContentView.pName }</h2>
					<div class="productContentLeft">
						<div class="leftImg">
							<img src="${conPath }/productFileUp/${productContentView.pFilename}" >
						</div>
					</div>
					
					<div class="productContentRight">
						<div class="rightContent">
							<ul>
								<li><h1>${productContentView.pCode }</h1></li>
							</ul>
							<ul>
								<li><h1>${productContentView.pName }</h1></li>
							</ul>
							<ul>
								<li><h2><fmt:formatNumber value="${productContentView.pPrice}" groupingUsed="true" /><b>원</b> </h2> </li>
							</ul>
							
							<table>
								<tr>
									<td>
										<h3>${productContentView.pDes }</h3>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<select name="cNt" class="cNt">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
										</select>
									</td>
								</tr>
							</table>
								<br>
								
							<p>
								<c:if test="${not empty member }">
									<input type="button" value="장바구니에 담기"  id="cart" class="orderBtn">
								</c:if>
							</p>
							<br>
							<p>
								<c:if test="${not empty admin }">
									<input type="button" value="상품수정" class="orderBtn" id="modi">
									<input type="button" value="상품삭제" class="orderBtn" id="delete">
								</c:if>
							</p>
						
						</div>
					</div>				
				</div>
			</div>
			
		</form>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>