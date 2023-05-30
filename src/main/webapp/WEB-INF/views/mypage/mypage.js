/**
 * 
 */
function go_cart() {
	var qty = document.getElementById("quantity");
	var theform = document.getElementById("theform");
	
	//(1) 수량 입력 여부 확인
	if (qty.value === "" || qty.value === "0") {
		alert("수량을 입력해 주세요.");
		qty.focus();
		
		return false;
	}
	
	// (2) 너무 큰 수량이 입력되면 수량 제한
	else if (qty.value > 30) {
		alert("수량이 너무 많습니다. 다시 입력해주세요.");
		qty.focus();
		
		return false;
	}
	
	// (3) 그 외의 경우 장바구니 데이터를 콘트롤러로 전달
	else {
		theform.action ="cart_insert";
		theform.submit();	// 콘트롤러로 데이터 전송
	}
}

/*
 * 장바구니 상품 삭제하기
 */
function go_cart_delete() {
	// input 태그의 엘리먼트 중에 name 속성이 cseq인 것들 중 check가 된 항목을 변수에 저장
	const query = "input[name='cseq']:checked";
	
	// 체크한 장바구니 항목의 갯수
	var len = document.querySelectorAll(query).length;
	console.log(len);
	
	if (len == 0) {
		alert("삭제할 항목을 선택해 주세요.");
	} else {
		var theform = document.getElementById("theform");
		
		theform.action = "cart_delete";
		theform.submit();
	}
}

/*
 * 장바구니 내역 주문처리
 */
function go_order_insert() {
	console.log('go_order_insert()...')
	var theform = document.getElementById("theform");
	
	theform.action = "order_insert";
	theform.submit();
}