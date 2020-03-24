<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/QnaWriteview.css" rel="stylesheet">		
</head>
<body>
<jsp:include page="../main/header.jsp"></jsp:include>
		<form action="${conPath }/QnaWrite.do" method="post" enctype="multipart/form-data">
		<div id ="wrap">
			<div class="content">
			<div class="head">
				<h1>Q&A 글쓰기</h1>
			</div>
				<div class="list">
					<table class="table">
						<tr>
							<td class="th1">
								제목
							</td>
							<td class="th2">
								<input type="text" name="qTitle" class="qTitle"  required="required" style="width: 678px;">
							</td>
						</tr>
						<tr>
							<td class="th1">
								본문
							</td>
							<td class="th2">
								<textarea name="qContent" rows="3" cols="32"></textarea>
							</td>
						</tr>
						<tr>
							<td class="th1">
								첨부파일
							</td>
							<td class="th2">
								<input type="file" name="qFilename">
							</td>
						</tr>
					</table>
						<p>
						<input type="submit" class="#" value="글쓰기" >
						<a href="${conPath }/QnaList.do">뒤로</a>					
						</p>
						
				</div>	
			</div>
	</div>
	</form>
<jsp:include page="../main/footer.jsp"></jsp:include>	
</body>
</html>
















