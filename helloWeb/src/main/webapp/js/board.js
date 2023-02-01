/**
 * empList.js
 */

// 목록출력하기
fetch("../BoardJson")
  .then((resolve) => resolve.json())
  .then((result) => {
    // console.log(result);
    // 배열관련메소드 : forEach, map, filter, reduce 메소드
    result.forEach(function (item, idx, arry) {
      let tr = makeTr(item); // tr 생성 후 반환
      // console.log(tr);
      list.append(tr);
    }); // result배열에 등록된 값의 갯수만큼 function() 실행
  })
  .catch((reject) => {
    console.log(reject);
  });

// 저장버튼에 submit 이벤트 등록
document
  .querySelector('form[name="boardForm"]')
  .addEventListener("submit", addMemberFnc);

// 전체선택 체크박스
document
  .querySelector('thead input[type="checkbox"]')
  .addEventListener("change", allCheckChange);

// 선택삭제 버튼
document
  .querySelector("#delSelectedBtn")
  .addEventListener("click", deleteCheckedFnc);

// 데이터 한건 활용해서 tr 요소를 생성
function makeTr(item) {
  // DOM 요소생성
  let titles = ["no", "title", "username", "wday"];
  // 데이터 건수만큼 반복
  let tr = document.createElement("tr");
  // columns 갯수만큼 반복
  titles.forEach(function (title) {
    let td = document.createElement("td");
    td.innerText = item[title];
    tr.append(td);
  });
  //삭제
  let td = document.createElement("td");
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  btn.addEventListener("click", deleteRowFunc);
  td.append(btn);
  tr.append(td);

  //수정
  td = document.createElement("td");
  btn = document.createElement("button");
  btn.innerText = "수정";
  btn.addEventListener("click", modifyTrFunc);
  td.append(btn);
  tr.append(td);

  //체크박스
  td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.addEventListener("change", checkedFunc);
  td.append(chk);
  tr.append(td);

  // tr반환
  return tr;
}

function checkedFunc() {
  let allTr = document.querySelectorAll("tbody#list tr");
  let chkTr = document.querySelectorAll('tbody input[type="checkbox"]:checked');
  if (allTr.length == chkTr.length) {
    document.querySelector('thead input[type="checkbox"]').checked = true;
  } else {
    document.querySelector('thead input[type="checkbox"]').checked = false;
  }
}

// 삭제버튼 이벤트 콜백함수
function deleteRowFunc() {
  let id = this.parentElement.parentElement.firstChild.innerText;
  fetch("../empListJson?del_id=" + id, {
    method: "DELETE",
  })
    .then((resolve) => resolve.json())
    .then((result) => {
      if (result.retCode == "Success") {
        alert("정상적으로 삭제");
        this.parentElement.parentElement.remove();
      } else if (result.retCode == "Fail") {
        alert("삭제중 오류발생");
      }
    })
    .catch((reject) => console.log(reject));
}

// 수정화면 함수
function modifyTrFunc() {
  // this => 수정
  let thisTr = this.parentElement.parentElement;
  // console.log("사원번호: ", thisTr.children[0].innerText);
  // console.log("이름: ", thisTr.children[1].innerText);
  let id = thisTr.children[0].innerText;
  let name = thisTr.children[1].innerText;
  let mail = thisTr.children[2].innerText;
  let hire = thisTr.children[3].innerText;
  let job = thisTr.children[4].innerText;
  // 변경할 항목 배열에 등록
  let modItems = [name, mail, hire, job];

  let newTr = document.createElement("tr");

  let td = document.createElement("td");
  td.innerText = id; // 변경불가항목
  newTr.append(td);

  // 변경
  modItems.forEach((item) => {
    td = document.createElement("td");
    let inp = document.createElement("input");
    inp.style = "width: 100px";
    inp.value = item;
    td.append(inp);
    newTr.append(td);
  });
  // 삭제:비활성화, 변경: DB반영
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  btn.disabled = true;
  td = document.createElement("td");
  td.append(btn);
  newTr.append(td);
  // 변경버튼
  btn = document.createElement("button");
  btn.innerText = "변경";
  btn.addEventListener("click", updateMemberFun);
  td = document.createElement("td");
  td.append(btn);
  newTr.append(td);

  td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.addEventListener("click", checkedFunc);
  td.append(chk);
  newTr.append(td);

  document.querySelectorAll("button").forEach((chk) => {
    chk.disabled = true;
  });

  thisTr.replaceWith(newTr);
}
// 수정 처리 함수
function updateMemberFun() {
  let currTr = this.parentElement.parentElement;
  let id = currTr.children[0].innerText;
  let name = currTr.children[1].children[0].value;
  let mail = currTr.children[2].children[0].value;
  let hDate = currTr.children[3].children[0].value;
  let job = currTr.children[4].children[0].value;
  fetch("../empListJson", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body:
      "param=update&id=" +
      id +
      "&name=" +
      name +
      "&mail=" +
      mail +
      "&hire=" +
      hDate +
      "&job=" +
      job,
  })
    .then((resolve) => resolve.text())
    .then((result) => {
      // console.log(result);
      if (result.indexOf("Success") != -1) {
        let newTr = makeTr({
          id: id,
          lastName: name,
          email: mail,
          hireDate: hDate,
          job: job,
        });
        currTr.replaceWith(newTr);
        alert("정상적으로 처리");
      } else {
        console.log("error 발생");
      }
    })
    .catch((reject) => console.log(reject));

  document.querySelectorAll("button").forEach((chk) => {
    chk.disabled = false;
  });
}

// 저장 처리 함수
function addMemberFnc(evnt) {
  evnt.preventDefault(); // submit 하면 페이지 이동되는데 페이지 이동 안할려고
  console.log("여기에 출력");
  let name = document.querySelector('input[name="username"]').value;
  let title = document.querySelector('input[name="title"]').value;
  let text = document.querySelector('input[name="text"]').value;

  if (!name || !title || !text) {
    alert("필수입력값을 확인!!");
    return;
  }

  fetch("../empListJson", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" }, // key=val&key1 = val1
    body: "param=&name=" + name + "&title=" + title + "&text=" + text, // 'id=' -> 자바에서 파라미터, id는 js에서 만든 let id(input value 값)
  })
    .then((resolve) => resolve.json())
    .then((result) => {
      if (result.retCode == "Success") {
        alert("정상처리");
        list.append(
          makeTr({
            id: id,
            lastName: name,
            email: mail,
            hireDate: hDate,
            job: job,
          })
        );
        document.querySelector('input[name="username"]').value = "";
        document.querySelector('input[name="title"]').value = "";
        document.querySelector('input[name="text"]').value = "";
      } else if (result.retCode == "Fail") {
        alert("처리중 에러!");
      }
    });
}

// 전체선택 체크박스
function allCheckChange() {
  // tbody에 있는 체크박스 선택
  document.querySelectorAll('tbody input[type="checkbox"]').forEach((chk) => {
    chk.checked = this.checked;
  });
}

// 선택삭제 처리
// fetch API => 비동기방식처리 => 동기식 처리 (async, await)
async function deleteCheckedFnc() {
  let ids = [];
  let chks = document.querySelectorAll('tbody input[type="checkbox"]:checked');

  for (let i = 0; i < chks.length; i++) {
    let id = chks[i].parentElement.parentElement.firstChild.innerText;
    let resp = await fetch("../empListJson?del_id=" + id, {
      method: "DELETE",
    });
    let json = await resp.json();
    console.log(json);
    ids.push(json);
  }
  console.log(ids);

  processAfterFetch(ids); // [{id: 101, retCode: Success},....]
}

// 화면처리
function processAfterFetch(ary = []) {
  let targetTr = document.querySelectorAll("#list tr");
  console.log(targetTr, ary);
  // targetTr vs ary
  targetTr.forEach((tr) => {
    for (let i = 0; i < ary.length; i++) {
      if (tr.children[0].innerText == ary[i].id) {
        if (ary[i].retCode == "Success") {
          tr.remove(); // Success 조건 하에 삭제
        } else {
          tr.setAttribute("class", "delError");
        }
      }
    }
  });
}
