<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Nonage Shop</title>
  <link rel="stylesheet" href="css/shopping.css" >
  <script src="https://code.jquery.com/jquery-3.6.2.min.js" integrity="sha256-2krYZKh//PcchRtd+H+VyyQoZ/e3EcrkxhM8ycwASPA=" crossorigin="anonymous"></script> 
  <script type="text/javascript" src="member/member.js"></script>
  <script type="text/javascript" src="product/product.js"></script>
  <script type="text/javascript" src="mypage/mypage.js"></script>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

<body>
<div id="wrap">
<!--헤더파일 들어가는 곳 시작 -->
  <header>
    <!--로고 들어가는 곳 시작--->  
    <div id="logo">
      <a href="index">
        <img src="images/logo.png" width="180" height="100" alt="nonageshop">
      </a>  
    </div>
    <!--로고 들어가는 곳 끝-->     
    <nav id="catagory_menu">
     <ul>
       <c:choose>
       <c:when test="${empty sessionScope.loginUser}">
       <li>         
         <a href="login_form" style="width:100px;">LOGIN(CUSTOMER</a>
         <a href="admin_login_form" style="width:100px;">| ADMIN)</a>
	   </li>		       
       <li>/</li>
       <li><a href="contract">JOIN</a></li>
       </c:when>
       <c:otherwise>
       <li style="color:orange">
         ${sessionScope.loginUser.name}(${sessionScope.loginUser.id})
       </li>
       <li><a href="logout">LOGOUT</a></li>
       </c:otherwise>       
       </c:choose>
       <li>/</li>
       <li>
         <a href="cart_list">CART</a>
       </li><li>/</li>
       <li>
         <a href="mypage">MY PAGE</a>
       </li><li>/</li>
       <li>
         <a href="qna_list">Q&amp;A(1:1)</a>
       </li>
     </ul>
    </nav>

    <nav id="top_menu">
      <ul>
        <li>
          <a href="category?kind=1">Heels</a>
        </li>  
        <li>
          <a href="category?kind=2">Boots</a>
        </li>  
        <li>
          <a href="category?kind=3">Sandals</a>
        </li> 
        <li>
          <a href="category?kind=4">Sneakers</a>
        </li> 
        <li>
          <a href="category?kind=5">On Sale</a>
        </li>  
      </ul>
    </nav>
    <div class="clear"></div>
    <hr>
  </header>
  <!--헤더파일 들어가는 곳 끝 -->