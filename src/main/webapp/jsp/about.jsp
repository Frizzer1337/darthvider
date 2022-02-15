<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<c:choose>
	<c:when test="${locale!=null}">
		<fmt:setLocale value="${locale}" scope="session"/>
    </c:when>
    <c:otherwise>
   		 <fmt:setLocale value="en" scope="session"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="pages"/>

<html>
<head>
	<meta charset="UTF-8">
	<title><fmt:message key="page.title"/></title>
	<link rel="stylesheet" href="/darthvider/jsp/css/main.css">
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css2?family=Amatic+SC:wght@400;700&family=Arsenal:ital,wght@0,400;1,700&family=Fira+Code&display=swap" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Rubik:wght@700&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<header class="header">
	<div class="container">
		<div class="logo">
			<img src="/darthvider/jsp/img/logo.svg" alt="Logotype" class="header_logo"> <span><fmt:message key="header.logo"/></span>
		</div>
		<ul class="main_menu">
			<li><a href="/darthvider/jsp/main.jsp" class="menu_link"><fmt:message key="header.menu.main"/></a></li>
			<li><a href="/darthvider/jsp/tariffs.jsp" class="menu_link"><fmt:message key="header.menu.tariff"/></a></li>
			<li><a href="/darthvider/jsp/help.jsp" class="menu_link"><fmt:message key="header.menu.help"/></a></li>
			<li><a href="#" class="menu_link"><fmt:message key="header.menu.about"/></a></li>
		</ul>
		<form class="wrapper"  action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="changeLocale">
		<c:choose>
		<c:when test="${locale!='ru_RU'}">
		 <input type="radio" name="locale" value="EN" id="option-1" checked>
		 <input type="radio" name="locale" value="RU" id="option-2" >
		</c:when>
		<c:otherwise>
		 <input type="radio" name="locale" value="EN" id="option-1" >
		 <input type="radio" name="locale" value="RU" id="option-2"  checked>
		</c:otherwise>
		</c:choose>
		   <label for="option-1" class="option option-1">
		     <div class="dot"></div>
		      <span><fmt:message key="header.locale.en"/></span>
		      </label>
		   <label for="option-2" class="option option-2">
		     <div class="dot"></div>
		      <span><fmt:message key="header.locale.ru"/></span>
		   </label>
	   </form>
	<c:choose>
		<c:when test="${status!='SUCCESS_LOGIN'}">	
		<div class="authorization">
			<a href="/darthvider/jsp/main.jsp#login_section"  class="login"><fmt:message key="header.authorization.signin"/></a>
			<a href="/darthvider/jsp/main.jsp#login_section"  class="reg"><fmt:message key="header.authorization.signup"/></a>
		</div>
		</c:when>
		<c:otherwise>
			<ul class="main_menu">
				<li><a href="/darthvider/jsp/cabinet.jsp" class="menu_link">${email}</a></li>
				<li><a href="/darthvider/controller?command=logout"><img src="/darthvider/jsp/img/logout.svg" alt="Exit"></a></li>
			</ul>
		</c:otherwise>
	</c:choose>
	</div>
</header>
<main class="container">
<section class="provider-banner">
	<div class="primary_content">
				<div class="title">
					<h2>
						<fmt:message key="about.slogan.firstpart"/>
					</h2>
					<p class="about-text"><fmt:message key="about.slogan.under"/></p>
				</div>
				<img src="/darthvider/jsp/img/about.png" height=680 alt="Gamer">
			</div>
</section>
</main> 
<footer>
	<div class="container">
		<div class="footer_about">
			<div class="text_wrapper">
				<img src="/darthvider/jsp/img/logo.svg" alt="#">
				<h2><fmt:message key="footer.logo"/></h2>
			</div>
			<p><fmt:message key="footer.text"/> </p>
			<span><fmt:message key="footer.author"/></span>
		</div>
	</div>
</footer>
</body>
<script src="/darthvider/jsp/js/all.js"></script>
</html>