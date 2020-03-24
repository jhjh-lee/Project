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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
$(document).ready(function(){
	 $('#delete').click(function(){
			var confirmChk = confirm("글을 삭제하시겠습니까?");
				if(confirmChk == true){
					location.href="${conPath }/QnaDelete.do?qNo=${QnaContentview.qNo }";
				}
			});
 	 $('#modif').click(function(){
			var confirmChk = confirm("글을 수정하시겠습니까?");
				if(confirmChk == true){
					location.href="${conPath }/QnaModifyview.do?qNo=${QnaContentview.qNo }";
				}
			});
	})
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<div id ="wrap">
			<div class="content">
				<h2>${QnaContentview.qNo }</h2>
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								${QnaContentview.qTitle }
							</td>
						</tr>
						<tr>
							<td class="th1">
								작성자
							</td>
							<td class="th2">
								${QnaContentview.mId }
							</td>
						</tr>
						<c:if test="${not empty QnaContentview.qFilename }">
						<tr>
							<td colspan="2">
									<img class="rFilenameImg" alt="파일첨부" src="${conPath }/QNAFileboardUp/${QnaContentview.qFilename }">
							</td>
						</tr>
						</c:if>
						<tr>
							<td colspan="2">
								<div class="noticeText">
									<pre>${QnaContentview.qContent }</pre>
								</div>
							</td>
						</tr>
					</table>
					
					<p>
					<c:if test="${member.mId eq QnaContentview.mId || not empty admin}">
					<input type="button" value="글 수정" class="orderBtn" id="modif">
					</c:if>
					</p>
						
				</div>
			</div>
	</div>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>