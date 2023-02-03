<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<form name="myFrm" action="employee.do" method="post">
	<div class="form-group">
		<label>사원번호:</label> <input class="form-control" type="number"
			name="eid"><br>
	</div>
	<div class="form-group">
		<label>LastName:</label> <input class="form-control" type="text"
			name="Last_name"><br>
	</div>
	<div class="form-group">
		<label>이메일:</label> <input class="form-control" type="email"
			name="email"><br>
	</div>
	<div class="form-group">
		<label>입사일자:</label> <input class="form-control" type="date"
			name="hire_date"><br>
	</div>
	<div class="form-group">
		<label>직무:</label> <select name="job">
			<option value="IT_PROG">개발자</option>
			<option value="SA_REP" selected>영업사원</option>
			<option value="SA_MAN">영업팀장</option>
		</select>
	</div>
	<br>
	<div class="form-group">
		<input type="submit" value="전송"> <input type="button"
			value="조회" onclick="empGetFnc()"> <input type="button"
			value="네이버" onclick="naverGetFnc()"> <a
			href="../result/empList.jsp">조회</a>
	</div>
</form>

<script>
	function empGetFnc() {
		document.forms.myFrm.method = 'get';
		document.forms.myFrm.action = 'empList.do';
		document.forms.myFrm.submit();
	}
	function naverGetFnc() {
		document.forms.myFrm.method = 'delete';
		document.forms.myFrm.submit();
	}
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>