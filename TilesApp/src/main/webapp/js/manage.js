/**
 *
 */

console.log("manage.js start....");

function updateMemberFnc(e) {
  // modifyMember.do 사용자 정보 수정
  let tr = $(e.target).parent().parent(); //tr

  let id = tr.children().eq(0).text();
  let name = tr.find("input.name").val();
  let phone = tr.find("input.phone").val();
  let addr = tr.find("input.addr").val();
  let resp = tr.find("input.resp").val();

  // console.log(tr.children().eq(1).children().eq(0).val());

  $.ajax({
    url: "modiMember.do",
    method: "post",
    data: { mid: id, mname: name, mphone: phone, maddr: addr, resp: resp },
    success: function (result) {
      console.log(result);
      if (result.retCode == "Success") {
        alert("수정완료");
        tr.replaceWith(makeRow(result.member));
      } else {
        alert("수정오류");
      }
    },
    error: function (reject) {
      console.log(reject);
    },
  });
} // end of updateMemberFnc()

//페이지를 다 읽어라
$(document).ready(function () {
  // let clone = $("#template").clone(true);
  // console.log(clone.find("tr"));
  // let tr = clone.find("tr");
  // tr.find(".name").val("test");
  // $("#list").append(tr);

  console.log($("#list"));
  // 목록가져오는 ajax 호출
  $.ajax({
    url: "memberList.do",
    success: function (result) {
      $(result).each(function (idx, item) {
        //				console.log(result);
        $("#list").append(makeRow(item));
      });
    },
    error: function (reject) {
      console.log(reject);
    },
  });

  // 등록이벤트
  $("#addBtn").on("click", function () {
    let id = $("#mid").val(); // element.value 속성읽어옴
    let name = $("#mname").val();
    let phone = $("#mphone").val();
    let addr = $("#maddr").val();
    let img = $("#mimage")[0].files[0];

    let formData = new FormData();
    formData.append("id", id);
    formData.append("name", name);
    formData.append("phone", phone);
    formData.append("addr", addr);
    formData.append("img", img);

    $.ajax({
      url: "addMember.do",
      method: "post",
      data: formData,
      contentType: false,
      processData: false,
      success: function (result) {
        console.log(result);
        if (result.retCode == "Success") {
          $("#list").append(makeRow(result.member));
        } else {
          alert("입력미완");
        }
      },
      error: function (reject) {
        console.log(reject);
      },
    });
  });
});

function makeRow(member = {}) {
  // member값을 활용해서 tr생성
  let tr = $("<tr />"); // document.createElement('tr')
  tr.on("dblclick", function (e) {
    let id = $(this).children().eq(0).text();
    let name = $(this).children().eq(1).text();
    let phone = $(this).children().eq(2).text();
    let addr = $(this).children().eq(3).text();
    let resp = $(this).children().eq(4).text();

    let nTr = $("<tr />").append(
      $("<td />").text(id),
      $("<td />").append($("<input class='name' />").val(name)),
      $("<td />").append($("<input class='phone'/>").val(phone)),
      $("<td />").append($("<input class='addr'/>").val(addr)),
      $("<td />").append($("<input class='resp'/>").val(resp)),
      $("<td />").append(
        $(
          "<button onclick='updateMemberFnc(event)' class='btn btn-info' id='updbtn'>수정</button>"
        )
      )
    );
    // nTr = $("#template tr").clone(true);
    // nTr.find("input.name").val(name);

    $(this).replaceWith(nTr);
  });
  tr.append(
    $("<td />").text(member.memberId),
    $("<td />").text(member.memberName),
    $("<td />").text(member.memberPhone),
    $("<td />").text(member.memberAddr),
    $("<td />").text(member.responsibility),
    $("<td />").append(
      $('<button class="btn btn-danger">삭제</button>')
        .attr("mid", member.memberId)
        .on("click", deleteMemberFnc)
    )
  );
  return tr;
}

function deleteMemberFnc(e) {
  if (!window.confirm("삭제하시겠습니까?")) {
    return;
  }

  let user = $(e.target).attr("mid");

  $.ajax({
    url: "removeMember.do",
    data: { id: user }, // removeMember.do?id=user
    success: function (result) {
      console.log(result);
      if (result.retCode == "Success") {
        $(e.target).parent().parent().remove();
      } else {
        alert("오류");
      }
    },
    error: function (reject) {
      console.log(reject);
    },
  });
} // end of deleteFnc
