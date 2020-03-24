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
<link rel="stylesheet" href="${conPath }/css/join.css">
<style>
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('input[name="mId"]').keyup(function(){
			var mId = $('input[name="mId"]').val();
			$.ajax({
				url : '${conPath}/idConfirm.do',
				dataType : 'html',
				data : "mId="+mId,
				success : function(data){
					$('#idConfirmResult').html(mIdConfirm);
				}
			});//ajax
		});
		
</script>
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
  </script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<br>
	<br>
	<br>
	<br>
	<div id ="wrap">
			<div class="join">
				<form action="${conPath }/join.do" method="post" >
							<h3>회원가입</h3>
							<hr>
							<br>
						<table>
							<tr>
								<th>
									아이디<b>*</b>
								</th>
								<td>
									<input type="text" name="mId" class="mId" >
									<span id="idConfirmResult"></span>
								</td>
							</tr>
							<tr>
								<th>
									비밀번호<b>*</b>
								</th>
								<td>
									<input type="password" name="mPw" class="mPw">
								</td>
							</tr>
							<tr>
								<th>
									비밀번호 확인<b>*</b>
								</th>
								<td>
									<input type="password" name="mPwchk" class="mPwchk">
									<span id="pwChkResult"></span>
								</td>
							</tr>
							<tr>
								<th>
									이름<b>*</b>
								</th>
								<td>
									<input type="text" name="mName" class="mName">
								</td>
							</tr>
							<tr>
								<th>
									주소 
								</th>
								<td>
									<input type="text" name="mAddress" class="mAddress">
								</td>
							</tr>
							<tr>
								<th>
									전화번호<b>*</b>
								</th>
								<td>
									<input type="text" name="mTel" class="mTel" >
								</td>
							</tr>
							<tr>
								<th>
									이메일<b>*</b>
								</th>
								<td>
									<input type="tel" name="mEmail" class="mEmail" >
								</td>
							</tr>
							<tr>
								<th>
									생일
								</th>
								<td>
									<input type="text" name="mBirth" id="datepicker" >
								</td>
							</tr>					
						</table>
						<br>
						<br>
						<br>
						<br>
						<p class="subtn">
							<input type="submit" value="회원가입" class="joinbtn">
							&nbsp;
							<input type="button" value="취소" class="resetbtn"
								onclick="location.href='${conPath}/main/main.jsp'">
						</p>			
				</form>
			</div>
		</div>
		<jsp:include page="../main/footer.jsp"/>
</body>
</html>















