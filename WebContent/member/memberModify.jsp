<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${conPath }/css/join.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
	  $( "#datepicker" ).datepicker({
	    	dateFormat : 'yy-mm-dd',
	    	monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    	showMonthAfterYear : true,
	    	yearSuffix : '년', // "2020년 3월"
	    	showOtherMonths : true,
	    	dayNamesMin:['일','월','화','수','목','금','토']
	    });
  } );
  $(document).ready(function(){
		$('.mPwchk').keyup(function(){
			var mPw = $('.mPw').val();
			var mPwchk = $(this).val();
			if(mPw == mPwchk){
				$('#pwChkResult').html('비밀번호 일치');
			}else{
				$('#pwChkResult').html('비밀번호 불일치');
			}
		});
  </script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id ="wrap">
			<div class="join">
	<form action="${conPath }/memberModify.do" method="post"
			><!-- enctype="multipart/form-data" -->
		<table>
			<tr><th>아이디</th><b>*</b>
					<td><input type="text" name="mId" value="${member.mId }"
									readonly="readonly">
					</td>
			</tr>
			<tr><th>비밀번호</th><b>*</b>
					<td><input type="password" name="mPw" value="${member.mPw }"
									required="required">
					</td>
			</tr>
			<tr><th>이름</th><b>*</b>
					<td><input type="text" name="mName" value="${member.mName }"
									required="required">
					</td>
			</tr>
			<tr><th>주소</th>
					<td colspan="2">
						<input type="text" name="mAddress" value="${member.mAddress }">
					</td>
			</tr>
			<tr><th>전화번호</th>
					<td><input type="text" name="mTel" value="${member.mTel }"
									required="required">
					</td>
			</tr>
			<tr><th>이메일</th>
					<td><input type="email" name="mEmail" value="${member.mEmail }">
					</td>
			</tr>
			<tr><th>생년월일</th>
					<td colspan="2">
						<input type="text" name="mBirth" value="${member.mBirth }" id="birth">
					</td>
			</tr>
			
			
			<tr><td colspan="3">
						<input type="submit" value="정보수정" class="btn">
						<input type="reset" value="취소" class="btn">
						<input type="button" value="이전" onclick="history.go(-1)" class="btn">	
					</td>
			</tr>
		</table>
	</form>
	</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>