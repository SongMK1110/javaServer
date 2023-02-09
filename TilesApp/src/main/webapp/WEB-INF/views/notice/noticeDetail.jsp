<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"
prefix="fmt"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<table class="table">
  <tr>
    <th>글번호</th>
    <td>${vo.noticeId }</td>
    <th>조회수</th>
    <td>${vo.hitCount }</td>
  </tr>
  <tr>
    <th colspan="2">작성자</th>
    <td colspan="2">${vo.noticeWriter }</td>
  </tr>
  <tr>
    <th colspan="2">제목</th>
    <td colspan="2">${vo.noticeTitle }</td>
  </tr>
  <tr>
    <th>내용</th>
    <td colspan="3">
      <textarea cols="60" rows="4">${vo.noticeSubject }</textarea>
    </td>
  </tr>
  <tr>
    <th>작성일자</th>
    <td>
      <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${vo.noticeDate }" />
    </td>
    <th>첨부파일</th>
    <td>
      <a target="_blank" href="upload/${vo.attachFile }">${vo.attachFile }</a>
    </td>
  </tr>
</table>
<br />
<div>
  <span><b>제목:</b></span
  ><span><input type="text" id="title" /></span> <span><b>내용:</b></span
  ><span><input type="text" id="subject" /></span> <span><b>작성자:</b></span
  ><span><input type="text" id="writer" value="${id }"/></span>
  <button class="btn btn-primary" id="addReply">댓글등록</button>
</div>
<br />
<table border="1" style="margin: auto; width: 80%">
  <thead>
    <tr style="border: 1px solid green">
      <th colspan="4">댓글정보</th>
    </tr>
  </thead>
  <tbody id="list"></tbody>
</table>
<script>
   let nid = ${vo.noticeId};
  let logid = '${id}';
   fetch("replyList.do?nid=" + nid)
     .then((resolve) => resolve.json())
     .then((result) => {
       console.log(result);
       result.forEach((reply) => {
         makeTr(reply);
       });
     })
     .catch((error) => {
       console.log(error);
     });

   function deleteReply(replyId) {
     // ajax호출 fetch()
     $.ajax({
       url: "removeReply.do",
       method: "post",
       data: { rid: replyId },
       success: function (result) {
         console.log(result);
         if (result.retCode == "Success") {
           // <tr data-id=3> => tr[data-id=3]
           $("tr[data-id=" + replyId + "]").remove();
         } else {
           alert("처리 안됨");
         }
       },
       error: function (reject) {
         console.log(reject);
       },
     });
   }

   $("#addReply").on("click", function () {
     let writer = $("#writer").val();
     let subject = $("#subject").val();
     let title = $("#title").val();

     $.ajax({
       url: "addReply.do",
       method: "post",
       data: { title: title, writer: writer, subject: subject, nid: nid }, //xxx(파라미터):xxx(위에변수)
       success: function (result) {
         console.log(result);
   			makeTr(result); // 새로 row 생성한 후 tbody 아레에 append
       },
       error: function (reject) {
         console.log(reject);
       },
     });
   });

   function makeTr(reply) {
     //tr: 댓글번호, 제목, 작성자, 작성일자
     //tr: 댓글내용
     //let tr1 = document.createElement('tr');
     //let td = document.createElement('td');
     //td.innerText = reply.replyId;
     //tr1.append(td);

     let tr1 = $("<tr />")
       .attr("data-id", reply.replyId)
       .append(
         $("<td />").html("<b>번호:</b> " + reply.replyId),
         $("<td />").html("<b>제목:</b>  " + reply.replyTitle),
         $("<td />").html("<b>작성자:</b>  " + reply.replyWriter),
         $("<td />").html("<b>날짜:</b>  " + reply.replyDate)
       );

     let tr2 = $("<tr />")
       .attr("data-id", reply.replyId)
       .append(
         $("<td colspan='4'/>").html(function (){
  				if(reply.replyWriter == logid){
  					return reply.replySubject +
             "<button onclick='deleteReply(" +
             reply.replyId +
             ")' class='btn btn-danger'>삭제</button>"
  				} else{
  					return reply.replySubject
  				}
  			})
       );
     console.log(tr1, tr2);
     $("#list").prepend(tr1, tr2);
   }
</script>
