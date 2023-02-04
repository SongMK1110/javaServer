<%@page import="com.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
EmpVO emp = (EmpVO) request.getAttribute("modifyVO");
%>
<h3>현제 페이지는 empModForm.do의 결과 modify.jsp 입니다.</h3>
<form action="empModify.do" method="post">
	<table class="table">
		<tr>
			<th>사원번호</th>
			<td><input value="<%=emp.getEmployeeId()%>" name="eid" readonly></td>
		</tr>
		<tr>
			<th>FirstName</th>
			<td><input value="<%=emp.getFirstName()%>" name="first_name"></td>
		</tr>
		<tr>
			<th>LastName</th>
			<td><input value="<%=emp.getLastName()%>" name="Last_name"></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><input value="<%=emp.getEmail()%>" name="email"></td>
		</tr>
		<tr>
			<th>Job_ID</th>
			<td><input value="<%=emp.getJobId()%>" name="job"> <select>
					<option value="IT_PROG">개발자</option>
					<option value="SA_REP">영업사원</option>
					<option value="SA_MAN">영업팀장</option>
			</select></td>
		</tr>
		<tr>
			<th>Hire_Date</th>
			<td><input value="<%=emp.getHireDate().substring(0, 10)%>"
				name="hire_date" readonly></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button class="btn btn-primary" type="submit">수정완료</button>
			</td>
		</tr>
	</table>
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>