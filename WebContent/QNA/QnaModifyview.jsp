<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/QnaModifyview.css" rel="stylesheet">
<script>/* 
$(document).ready(function(){
	 $('#modif').click(function(){
			var confirmChk = confirm("글을 수정하시겠습니까?");
				if(confirmChk == true){
					location.href="${conPath }/QnaModify.do?qNo=${qnaModifyview.qNo }";
				}
			});
	}) */

</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<form action="${conPath }/QnaModify.do" method="post" enctype="multipart/form-data">
		<div id ="wrap">
			<div class="content">
				<div class="head">
				<h1>Q&A 글수정</h1>
				</div>
				<div class="list">
					<table class="table">
						
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								<input type="hidden" name="qNo" value="${qnaModifyview.qNo}">
								
								<input type="text" name="qTitle" class="nTitle" style="width: 650px;" value="${qnaModifyview.qTitle}">
							</td>
						</tr>
						<tr>
							<td class="th1">
								파일첨부
							</td>
							<td class="th2">
								<input type="file" name="qFilename" >
								<input type="hidden" name="orFilename" value="${qnaModifyview.qFilename}" style="width: 650px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								본문
							</td>
							<td>
								<textarea rows="25" cols="45" name="qContent" style=" width: 670px; text-align: left; resize: none;">${qnaModifyview.qContent}</textarea>
							</td>
						</tr>
					</table>
					
					<p>
						<input type="submit" class="Btn" value="수정하기" id="modif" >				
					</p>
						
				</div>
			</div>
	</div>
	</form>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>