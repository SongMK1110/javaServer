/**
 *
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
  {
    bookCode: "P0206001",
    bookTitle: "이것이자바다",
    bookAuthor: "홍성문",
    bookPress: "신흥출판사",
    bookPrice: "20000",
  },
  {
    bookCode: "P0206002",
    bookTitle: "이것이자바스크립트다",
    bookAuthor: "홍영웅",
    bookPress: "우리출판사",
    bookPrice: "25000",
  },
];
bookList.forEach(function (item, idx, arry) {
  let tr = makeTr(item); // tr 생성 후 반환
  list.append(tr);
});

// 저장버튼
document.querySelector("#btn").addEventListener("click", addMemberFnc);

// 전체선택 체크박스
document
  .querySelector('thead input[type="checkbox"]')
  .addEventListener("change", allCheckChange);

// 선택삭제 버튼
document.querySelector("#checkDel").addEventListener("click", deleteCheckedFnc);

function makeTr(item) {
  // DOM 요소생성
  let titles = [
    "bookCode",
    "bookTitle",
    "bookAuthor",
    "bookPress",
    "bookPrice",
  ];
  // 데이터 건수만큼 반복
  let tr = document.createElement("tr");
  // columns 갯수만큼 반복
  //체크박스
  let td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  // chk.addEventListener("change", checkedFunc);
  td.append(chk);
  tr.append(td);

  titles.forEach(function (title) {
    let td = document.createElement("td");
    td.innerText = item[title];
    tr.append(td);
  });

  //삭제
  td = document.createElement("td");
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  btn.addEventListener("click", deleteRowFunc);
  td.append(btn);
  tr.append(td);

  // tr반환
  return tr;
}

// 저장 처리 함수
function addMemberFnc(evnt) {
  evnt.preventDefault(); // submit 하면 페이지 이동되는데 페이지 이동 안할려고
  console.log("여기에 출력");
  let bookCode = document.querySelector("#bookCode").value;
  let bookName = document.querySelector("#bookName").value;
  let author = document.querySelector("#author").value;
  let press = document.querySelector("#press").value;
  let price = document.querySelector("#price").value;

  if (!bookCode || !bookName || !author || !press || !price) {
    alert("필수입력값을 확인!!");
    return;
  }
  list.append(
    makeTr({
      bookCode: bookCode,
      bookTitle: bookName,
      bookAuthor: author,
      bookPress: press,
      bookPrice: price,
    })
  );
  document.querySelector("#bookCode").value = "";
  document.querySelector("#bookName").value = "";
  document.querySelector("#author").value = "";
  document.querySelector("#press").value = "";
  document.querySelector("#price").value = "";
}

//삭제
function deleteRowFunc() {
  // let id = this.parentElement.parentElement.children[1].innerText;
  this.parentElement.parentElement.remove();
}

// 전체선택 체크박스
function allCheckChange() {
  // tbody에 있는 체크박스 선택
  document.querySelectorAll('tbody input[type="checkbox"]').forEach((chk) => {
    chk.checked = this.checked;
  });
}

function deleteCheckedFnc() {
  let chks = document.querySelectorAll('tbody input[type="checkbox"]:checked');

  for (let i = 0; i < chks.length; i++) {
    chks[i].parentElement.parentElement.remove();
  }
}
