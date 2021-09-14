<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<fmt:setLocale value="${locale}" scope="session"/>
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
</head>
<body>
<header class="header">
	<div class="container">
		<div class="logo">
			<img src="/darthvider/jsp/img/logo.svg" alt="Logotype" class="header_logo"> <span><fmt:message key="header.logo"/></span>
		</div>
		<ul class="main_menu">
			<li><a href="#" class="menu_link"><fmt:message key="header.menu.main"/></a></li>
			<li><a href="/darthvider/jsp/tariffs.jsp" class="menu_link"><fmt:message key="header.menu.tariff"/></a></li>
			<li><a href="#" class="menu_link"><fmt:message key="header.menu.help"/></a></li>
			<li><a href="#" class="menu_link"><fmt:message key="header.menu.about"/></a></li>
		</ul>
		<form class="wrapper"  action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="changeLocale">
		<c:choose>
		<c:when test="${locale!='ru_RU'}">
		 <input type="radio" name="locale" value="EN" id="option-1" checked>
		 <input type="radio" name="locale" value="RU" id="option-2">
		</c:when>
		<c:otherwise>
		 <input type="radio" name="locale" value="EN" id="option-1" >
		 <input type="radio" name="locale" value="RU" id="option-2" checked>
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
			<a href="#login_section"  class="login"><fmt:message key="header.authorization.signin"/></a>
			<a href="#login_section"  class="reg"><fmt:message key="header.authorization.signup"/></a>
		</div>
		</c:when>
		<c:otherwise>
			<ul class="main_menu">
				<li><a href="#" class="menu_link">${email}</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
	</div>
</header>
<main class="main">
	<div class="container">
		<section class="provider-banner">
			<div class="primary_content">
				<div class="title">
					<h2>
						<fmt:message key="main.slogan.firstpart"/><br> <fmt:message key="main.slogan.secondpart"/>
					</h2>
					<p><fmt:message key="main.slogan.under"/></p>
					<a class="get_started primary_button" href="#">
						<fmt:message key="main.slogan.button"/>
					</a>
				</div>
				<img src="/darthvider/jsp/img/gamer.svg" alt="Gamer">
			</div>
			<div class="our_achievements">
				<ul class="achievements_list">
					<li class="achievements_item"><img src="/darthvider/jsp/img/user.svg" alt="#">
						<p class="achievements_text"><fmt:message key="main.achievements.usernum"/> <br><span class="achievements_describe"><fmt:message key="main.achievements.user"/></span></p>
					</li>
					<li class="achievements_item"><img src="/darthvider/jsp/img/location.svg" alt="#">
						<p class="achievements_text"><fmt:message key="main.achievements.countrynum"/><br><span class="achievements_describe"><fmt:message key="main.achievements.country"/></span></p>
					</li>
					<li class="achievements_item"><img src="/darthvider/jsp/img/server.svg" alt="">
						<p class="achievements_text"><fmt:message key="main.achievements.plannum"/><br><span class="achievements_describe"><fmt:message key="main.achievements.plan"/></span></p>
					</li>
				</ul>
			</div>
		</section>
		<section class="choose_plan">
			<div class="plan_title">
				<h2><fmt:message key="main.plans.bigtext"/></h2>
				<p><fmt:message key="main.plans.smalltext"/></p>
			</div>
			<div class="plans">
				<div class="plan_item"><img src="/darthvider/jsp/img/toChange.svg" alt="#">
					<div class="plan_description">
						<h3>Stupid Plan</h3>
						<ul>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>

						</ul>
						<p class="price">Free</p>
						<a class="primary_button" href="#"><fmt:message key="main.plans.submitbut"/></a>
					</div>
				</div>
				<div class="plan_item"><img src="/darthvider/jsp/img/toChange.svg" alt="#">
					<div class="plan_description">
						<h3>Stupid Plan</h3>
						<ul>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>

						</ul>
						<p class="price">Free</p>
						<a class="primary_button" href="#"><fmt:message key="main.plans.submitbut"/></a>
					</div>
				</div>
				<div class="plan_item"><img src="/darthvider/jsp/img/toChange.svg" alt="#">
					<div class="plan_description">
						<h3>Stupid Plan</h3>
						<ul>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>
							<li><img src="/darthvider/jsp/img/listOk.svg" alt="#">
								<p>Unlimited Bandwitch</p>
							</li>

						</ul>
						<p class="price">Free</p>
						<a class="primary_button" href="#"><fmt:message key="main.plans.submitbut"/></a>
					</div>
				</div>

			</div>
			<a href="#" class="more_plans primary_button"><fmt:message key="main.plans.morebut"/></a>
		</section>
		<a id="login_section"></a>
		<section class="our_map">
		<c:choose>
		<c:when test="${status=='SUCCESS_REGISTRATION'}">	
		<div class="autharization_form">
			<div class="success_registration">
			<img src="/darthvider/jsp/img/registerOk.png" alt="#">
			<div class="success_registration_text">
			<fmt:message key="main.login.succes"/>
			</div>
			</div>
		</div>
		</c:when>
		<c:when test="${status=='SUCCESS_LOGIN'}">	
			<div class="network_map">
				<h2><fmt:message key="main.login.logintext"/></h2>
				<p><fmt:message key="main.login.logintextunder"/></p>
				<img src="/darthvider/jsp/img/rocket.png" height="250px" alt="#">
			</div>
		</c:when>
		<c:otherwise>
		<div class="autharization_form">
				<div class="autharization_bttns">
					<button class="active_bttn login_btn"><fmt:message key="main.login.login"/></button>
					<button class="reg_btn"><fmt:message key="main.login.signup"/></button>
				</div>
				<form class="authorization_login" id="login"  action="<c:url value="/controller"/>">
					<input type="hidden" name="command" value="login"  required>
					<c:if test="${status == 'WRONG_LOGIN'}">
					<span class="error_message"><fmt:message key="main.login.loginerr"/></span>
					</c:if>
					<input type="text" name="email" placeholder="Email Address"  required>
					<input type="password" name="password" placeholder="Password"  required>
					<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="main.login.login"/>
					</button>
					<a href="#" class="autharizathion_forgot"><fmt:message key="main.login.forgot"/></a>
				</form>
				
				<form class="authorization_login" id="reg" style="display: none" action="<c:url value="/controller"/>">
					<input type="hidden" name="command" value="signup">
					<c:if test="${status == 'PASSWORD_NOT_REPEATED'}">
					<span class="error_message"><fmt:message key="main.login.passnotrepeated"/></span>
					</c:if>
					<c:if test="${status == 'BAD_PASSWORD'}">
					<span class="error_message"><fmt:message key="main.login.badpass"/></span>
					</c:if>
					<c:if test="${status == 'BAD_EMAIL'}">
					<span class="error_message"><fmt:message key="main.login.bademail"/></span>
					</c:if>
					<c:if test="${status == 'BAD_LOGIN'}">
					<span class="error_message"><fmt:message key="main.login.badlogin"/></span>
					</c:if>
					<c:if test="${status == 'DATA_ALREADY_EXISTS'}">
					<span class="error_message"><fmt:message key="main.login.userexist"/></span>
					</c:if>
					<input type="text"  name="login" value="${formMap.login}" placeholder="Login"  required>
					<input type="email"  name="email" value="${formMap.email}" placeholder="Email Address"  required>
					<input type="password"  name="password" value="${formMap.password}" placeholder="Password"  required>
					<input type="password"  name="password_repeat" value="${formMap.password_repeat}" placeholder="Password"  required>
					<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="main.login.signup"/>
					</button>
				</form>
		</div>
		</c:otherwise>
		</c:choose>
			<div class="network_map">
				<h2><fmt:message key="main.map.slogan"/></h2>
				<p><fmt:message key="main.map.under"/></p>
				<img src="/darthvider/jsp/img/map.svg" alt="#">
			</div>
		</section>
		<div class="banner">
			<div class="banner_text">
				<h2><fmt:message key="main.subscribe.sloganfirst"/><br>
					<fmt:message key="main.subscribe.slogansecond"/>
				</h2>	
				<p><fmt:message key="main.slogan.under"/></p>
			</div>
			<div class="btn-wrapper">
				<a href="#" class="primary_button"><fmt:message key="main.slogan.button"/></a>
			</div>
		</div>
	</div>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="/darthvider/jsp/js/all.js"></script>
</html>