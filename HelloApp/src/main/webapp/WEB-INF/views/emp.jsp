<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>현재 페이지는 empForm.do의 결과 emp.jsp 입니다.</h3>
<%
Map<String, String> list = (Map<String, String>) request.getAttribute("jobList");
%>
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
			<%
			for (Entry<String, String> ent : list.entrySet()){
			%>
				<option value="<%=ent.getKey() %>"><%=ent.getValue() %>
			<%
			}
			%>
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