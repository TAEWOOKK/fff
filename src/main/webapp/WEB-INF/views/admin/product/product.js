/**
 * 상품 상세정보 구현
 */
function go_detail(pageNum, rowsPerPage, pseq) {
	//productDetail.jsp 
	var theform = document.getElementById("prod_form");
	var url = "admin_product_detail?pageNum=" + pageNum 
				+ "&rowsPerPage=" + rowsPerPage 
				+ "&pseq=" + pseq;
	
	//url을 보내기 위한 action 속성
	//pseq를 조건으로 admin_product_detail이라는 url에 제품에 대한 정보를 보낼거야
	theform.action = url;
	theform.submit();
}

//상품 목록 페이지 상품명 검색 기능 구현
function go_search() {
	//productDetail.jsp 
	var theform = document.getElementById("prod_form");
	
	theform.action = "admin_product_list";
	theform.submit;
}

//상품 등록 화면 표시 요청
function go_wrt() {
	var theform = document.getElementById("prod_form");
	
	theform.method = "GET";
	theform.action = "admin_product_write_form"
	theform.submit();
}

//price3(순익) 필드 계산
//price1, price2 를 읽어서 (price2 - price1) 해주는 기능
//그러면 상품등록 화면에서 원가[A], 판매가[B] 입력하면 [B-A]에 자동으로 숫자 등록 된다.
function go_ab() {
	var price2 = document.getElementById("price2").value;
	var price1 = document.getElementById("price1").value;
	var price3 = price2 - price1;
	
	document.getElementById("price3").value = price3;
}

//상품등록 요청
//상품등록 시 모든 칸을 다 입력하기 위해서 작성하지 않은 칸이 있을 경우 경고창 띄움
function go_save() {
	
	if (document.getElementById("kind").value == "") {
		alert("상품 종류를 입력하세요.");
		document.getElementById("kind").focus();
		return false;
	} else if (document.getElementById("name").value == "") {
		alert("상품명을 입력하세요.");
		document.getElementById("name").focus();
		return false;
	} else if (document.getElementById("price1").value == "") {
		alert("상품원가를 입력하세요.");
		document.getElementById("price1").focus();
		return false;
	} else if (document.getElementById("price2").value == "") {
		alert("판매가를 입력하세요.");
		document.getElementById("price2").focus();
		return false;
	} else if (document.getElementById("price3").value == "") {
		alert("상품원가를 입력하세요.");
		document.getElementById("price2").focus();
		return false;
	} else if (document.getElementById("content").value == "") {
		alert("상품설명을 입력하세요.");
		document.getElementById("content").focus();
		return false;
	} else {
		var theform = document.getElementById("write_form");
		
		theform.action = "admin_product_write";
		theform.enctype = "multipart/form-data"; //productWrite.jsp 상단 인코딩 method명
		theform.submit();
	}
}

//상품 상세보기 내 목록 버튼 - 조회 페이지로 이동
function go_list() {
	var theform = document.getElementById("detail_form");
	
	theform.action = "admin_product_list";
	theform.submit();
}

//상품 상세보기 내 수정 버튼 - pseq 조건으로 정보 가져와야함
function go_mod() {
	var theform = document.getElementById("detail_form");
	
	theform.action = "admin_product_update_form?pseq";
	theform.method = "GET";
	theform.submit();
}

// useyn 버튼 수정
function set_useyn() {
	var is_checked = document.getElementById("useyn").checked;
	
	if (is_checked) {
		document.getElementById("useyn").value = 'y';
	} else {
		document.getElementById("useyn").value = 'n';
	}
	
	console.log("useyn="+document.getElementById("useyn").value);
}

// bestyn 버튼 수정
function set_bestyn() {
	var is_checked = document.getElementById("bestyn").checked;
	
	if (is_checked) {
		document.getElementById("bestyn").value = 'y';
	} else {
		document.getElementById("bestyn").value = 'n';
	}
	console.log("bestyn="+document.getElementById("bestyn").value);
}

//전체 보기
function go_total() {
	var theform = document.getElementById("prod_form");
	
	document.getElementById("key").value = "";
	
	theform.action = "admin_product_list";
	theform.submit();
}

//상품 수정
function go_mod_save() {
	
	if (document.getElementById("kind").value == "") {
		alert("상품 종류를 입력하세요.");
		document.getElementById("kind").focus();
		return false;
	} else if (document.getElementById("name").value == "") {
		alert("상품명을 입력하세요.");
		document.getElementById("name").focus();
		return false;
	} else if (document.getElementById("price1").value == "") {
		alert("상품원가를 입력하세요.");
		document.getElementById("price1").focus();
		return false;
	} else if (document.getElementById("price2").value == "") {
		alert("판매가를 입력하세요.");
		document.getElementById("price2").focus();
		return false;
	} else if (document.getElementById("price3").value == "") {
		alert("상품원가를 입력하세요.");
		document.getElementById("price2").focus();
		return false;
	} else if (document.getElementById("content").value == "") {
		alert("상품설명을 입력하세요.");
		document.getElementById("content").focus();
		return false;
	} else {
		var theform = document.getElementById("update_form");
		
		theform.action = "admin_product_update";
		theform.enctype = "multipart/form-data"; //productWrite.jsp 상단 인코딩 method명
		theform.submit();
	}
}













