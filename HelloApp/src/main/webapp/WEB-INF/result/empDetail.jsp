<%@page import="com.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
	EmpVO emp = (EmpVO) request.getAttribute("searchVO");
	Integer age = (Integer) request.getAttribute("myAge");
	String id = (String) request.getAttribute("loginId");
%>
<%=age %> , <%=id %>
<h3>현재 페이지는 empDetail.do의 결과 empDetail.jsp 입니다.</h3>
<table class="table">
	<tr>
		<th>사원번호</th>
		<td><%=emp.getEmployeeId() %></td>
	</tr>
	<tr>
		<th>FirstName</th>
		<td><%=emp.getFirstName() %></td>
	</tr>
	<tr>
		<th>LastName</th>
		<td><%=emp.getLastName() %></td>
	</tr>
	<tr>
		<th>Email</th>
		<td><%=emp.getEmail() %></td>
	</tr>
	<tr>
		<th>Job_ID</th>
		<td><%=emp.getJobId() %></td>
	</tr>
	<tr>
		<th>Hire_Date</th>
		<td><%=emp.getHireDate() %></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button class="btn btn-primary"
			 onclick="location.href='empModForm.do?id=<%=emp.getEmployeeId()%>'">수정</button>
			<button class="btn btn-warning"
			 onclick="location.href='empRemove.do?id=<%=emp.getEmployeeId()%>'">삭제</button> <!-- empRemove.do?id=?? removeEmp(int id)-->
		</td>
	</tr>
</table>

<jsp:include page="../includes/footer.jsp"></jsp:include>