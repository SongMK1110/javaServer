<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link
	href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>

<div>
	작성자: <input type="text" id="writer" value="user1" readonly> 제목:
	<input type="text" id="title"> 내용: <input type="text"
		id="subject">
	<button id="addBtn" class="btn btn-success">저장</button>
	<button id="delBtn" class="btn btn-danger">삭제</button>
</div>
<br>

<table id="example" class="display" style="width: 100%">
	<thead>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일자</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="notice" items="${noticeList }">
			<tr>
				<td>${notice.noticeId }</td>
				<td>${notice.noticeWriter }</td>
				<td>${notice.noticeTitle }</td>
				<td>${notice.hitCount }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${notice.noticeDate }" /></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일자</th>
		</tr>
	</tfoot>
</table>

<script>
	var t = $('#example').DataTable();
	$('#addBtn').on(
			'click',
			function() {
				var formData = new FormData();
				formData.append('writer', $('#writer').val());
				formData.append('title', $('#title').val());
				formData.append('subject', $('#subject').val());

				// db insert 후 화면처리
				$.ajax({
					url : 'noticeAddJson.do',
					method : 'post',
					data : formData,
					contentType : false,
					processData : false,
					success : function(result) {
						console.log(result);
						t.row.add(
								[ result.noticeId, result.noticeWriter,
										result.noticeTitle, result.hitCount,
										result.noticeDate ]).draw(false);
					},
					error : function(reject) {
						console.log(reject);
					}
				});

			});

	$('#example tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			t.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		console.log($(this).children().eq(0).text());
		localStorage.setItem('noticeId', $(this).children().eq(0).text());
	});

	$('#delBtn').click(function() {
		console.log("삭제할 글번호: " + localStorage.getItem("noticeId"));
		$.ajax({
			url : "removenotice.do",
			method : "post",
			data : {
				nid : localStorage.getItem("noticeId")
			},
			success : function(result) {
				console.log(result);
				if (result.retCode == "Success") {
					t.row('.selected').remove().draw(false);
				} else {
					alert("처리 안됨");
				}
			},
			error : function(reject) {
				console.log(reject);
			},
		});
	});
</script>
