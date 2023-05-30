<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본 에러 화면</title>
</head>
<style>
	body {
		margin-top: 15%;
	}
	.error {
		text-align: center;
		background-color: white;
		width: 500px;
		padding: 5%;
		border: 3px solid grey;
		box-shadow: 0px 5px 15px 0px rgba(0, 0, 0, 0.35);
	}
</style>
<body>
<div align="center">
	<div class="error">
		<span style="font-size:50px; margin:0;"><b>Error</b></span>
		<br><br><br>
		
		<div style="text-align:center;">
			<span>Message : ${exception.message}</span>
		</div>
		<br><br>
		<a href="index">메인화면으로 이동</a>
	</div>
</div>
</body>
</html>