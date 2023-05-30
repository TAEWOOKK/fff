/**
 * 약관 동의 여부 확인 및 회원가입 화면 요청
 */
function go_next() {
	if (document.formm.okon1[0].checked == true) {// 동의함
		// <div style="text-align: center;">
		// <input type="radio" name="okon1" class="agree" checked> 동의함 &nbsp;->0
		// &nbsp; &nbsp;
		// <input type="radio" name="okon1" class="agree" checked> 동의안함->1
		// </div>
		document.formm.action = "join_form"; // 요청 URL
		document.formm.submit(); // Controller로 전송
	} else if (document.formm.okon1[1].checked == true) {// 동의 안함 - 경고창
		alert("약관에 동의하셔야 합니다.")
	}
}

// ID값 입력 여부 확인
function idcheck() {
	if (document.getElementById("id").value == "") {
		alert("아이디를 입력해주세요");
		document.getElementById("id").focus();

		return false;
	}

	// 아이디가 입력된 경우 작은 팝업 윈도우 표시
	var url = "id_check_form?id=" + document.getElementById("id").value;
	window
			.open(url, "_blank_",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=350")// 팝업.

}

//회원 가입 시 필수 입력 확인
function go_save() {
	if (document.getElementById("id").value == "") {
		alert("사용자 아이디를 입력해 주세요");
		document.getElementById("id").focus();
		return false;
	} else if (document.getElementById("reid").value == "") {
		alert("아이디 중복체크를 해주세요");
		document.getElementById("reid").focus();
		return false;
	} else if (document.getElementById("pwd").value == "") {
		alert("비밀번호를 입력해 주세요");
		document.getElementById("pwd").focus();
		return false;
	} else if (document.getElementById("pwdCheck").value != document.getElementById("pwd").value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.getElementById("pwdCheck").focus();
		return false;
	} else if (document.getElementById("name").value == "") {
		alert("이름을 입력해 주세요");
		document.getElementById("name").focus();
		return false;
	} else if (document.getElementById("email").value == "") {
		alert("이메일을 입력해 주세요");
		document.getElementById("email").focus();
		return false;
	}else{
		document.getElementById("join").action = "join";//회원가입 요청
		document.getElementById("join").submit();
	}
}

//주소 찾기
function post_zip() {
	var url = "find_zip_num";
	window.open(url, "_blank_",
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=700, height=500")
}

//아이디 찾기 창 띄우기
function find_id_form() {
	var url = "find_id_form";
	
	window.open(url,"_blank_","toolbar=no, menubar=no,scrollbars=yes,width=700,height=450, resizable =no")
}

//이름과 이메일로 아이디 찾기 요청
function findMemberId() {
	if (document.getElementById("name").value=="") {
		alert("이름을 입력해 주세요");
		document.getElementById("name").focus();
		return false;
	} else if (document.getElementById("email").value=="") {
		alert("이메일을 입력해 주세요");
		document.getElementById("email").focus();
		return false;
	} else {
		var form = document.getElementById("findId");
		form.action = "find_id";
		form.submit();
	}
}

//비밀번호 찾기
function findPassword() {
	if (document.getElementById("id2").value=="") {
		alert("아이디를 입력해 주세요");
		document.getElementById("id2").focus();
		return false;
	}else if (document.getElementById("name2").value=="") {
		alert("이름을 입력해 주세요");
		document.getElementById("name2").focus();
		return false;
	} else if (document.getElementById("email2").value=="") {
		alert("이메일을 입력해 주세요");
		document.getElementById("email2").focus();
		return false;
	} else {
		var form = document.getElementById("findPW");
		form.action = "find_pwd";
		form.submit();
	}
}

//비밀번호 찾기 후 새 비밀번호 변경
function changePassword() {
	if (document.getElementById("pwd").value == "") {
		alert("비밀번호를 입력해 주세요");
		document.getElementById("pwd").focus();
		return false;
	} else if (document.getElementById("pwd").value
				!= document.getElementById("pwdcheck").value) {
		alert("비밀번호가 맞지 않습니다.");
		document.getElementById("pwd").focus();
		return false;
	} else {
		var form = document.getElementById("pwd_form");
		form.action = "change_pwd";
		form.submit();
	}
}






