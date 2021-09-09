<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>DarthVider.by-internet to every house!</title>
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
			<img src="/darthvider/jsp/img/logo.svg" alt="Logotype" class="header_logo"> <span>DarthVider</span>
		</div>
		<ul class="main_menu">
			<li><a href="#" class="menu_link">Home</a></li>
			<li><a href="/darthvider/jsp/tariffs.jsp" class="menu_link">Shop</a></li>
			<li><a href="#" class="menu_link">Help</a></li>
			<li><a href="#" class="menu_link">About</a></li>
		</ul>
	<c:choose>
		<c:when test="${status!='Logged'}">	
		<div class="authorization">
			<a href="#login_section"  class="login">Sign In</a>
			<a href="#login_section"  class="reg">Sign Up</a>
		</div>
		</c:when>
		<c:otherwise>
			Hello
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
						Your best <br> game provider
					</h2>
					<p>Fell the power of our connection right now! You underestimate the power of the Dark Side!</p>
					<a class="get_started primary_button" href="#">
						Get Started
					</a>
				</div>
				<img src="/darthvider/jsp/img/gamer.svg" alt="Gamer">
			</div>
			<div class="our_achievements">
				<ul class="achievements_list">
					<li class="achievements_item"><img src="/darthvider/jsp/img/user.svg" alt="#">
						<p class="achievements_text">90k+ <br><span class="achievements_describe">Users</span></p>
					</li>
					<li class="achievements_item"><img src="/darthvider/jsp/img/location.svg" alt="#">
						<p class="achievements_text">25 <br><span class="achievements_describe">Countries</span></p>
					</li>
					<li class="achievements_item"><img src="/darthvider/jsp/img/server.svg" alt="">
						<p class="achievements_text">50+<br><span class="achievements_describe">Plans</span></p>
					</li>
				</ul>
			</div>
		</section>
		<section class="choose_plan">
			<div class="plan_title">
				<h2>Choose Your Plan</h2>
				<p>Let's choose the package that is best for you and explore it happily and cheerfully.</p>
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
						<a class="primary_button" href="#">Select</a>
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
						<a class="primary_button" href="#">Select</a>
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
						<a class="primary_button" href="#">Select</a>
					</div>
				</div>

			</div>
			<a href="#" class="more_plans primary_button">More Plans</a>
		</section>
		<a id="login_section"></a>
		<section class="our_map">
		<c:choose>
		<c:when test="${status!='SUCCESS'}">	
			<div class="autharization_form">
				<div class="autharization_bttns">
					<button class="active_bttn login_btn">Login</button>
					<button class="reg_btn">Sign Up</button>
				</div>
				<form class="authorization_login" id="login"  action="<c:url value="/controller"/>">
					<input type="hidden" name="command" value="login"  required>
					<input type="text" name="email" placeholder="Email Address"  required>
					<input type="password" name="password" placeholder="Password"  required>
					<button type="submit" class="authorization_submit primary_button">
						Login
					</button>
					<a href="#" class="autharizathion_forgot">Forgot Password?</a>
				</form>
				
				<form class="authorization_login" id="reg" style="display: none" action="<c:url value="/controller"/>">
					<input type="hidden" name="command" value="signup">
					<input type="text"  name="login" value="${formMap.login}" placeholder="Login"  required>
					<input type="email"  name="email" value="${formMap.email}" placeholder="Email Address"  required>
					<input type="password"  name="password" value="${formMap.password}" placeholder="Password"  required>
					<input type="password"  name="password_repeat" value="${formMap.password_repeat}" placeholder="Password"  required>
					<button type="submit" class="authorization_submit primary_button">
						Sign Up
					</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
		<div class="autharization_form">
		<div class="success_registration">
			<img src="/darthvider/jsp/img/registerOk.png" alt="#">
			<div class="success_registration_text">
			You're succesfully registred, confirmation mail was sent!
			</div>
			</div>
		</div>
		</c:otherwise>
		</c:choose>
			<div class="network_map">
				<h2>Huge Global Network</h2>
				<p>See DarthVider everywhere to make it easier for you when you move locations</p>
				<img src="/darthvider/jsp/img/map.svg" alt="#">
			</div>
		</section>
		<div class="banner">
			<div class="banner_text">
				<h2>Subscribe Now for <br>
					Get Special Features!
				</h2>	
				<p>Let's subscribe with us and find the fun</p>
			</div>
			<div class="btn-wrapper">
				<a href="#" class="primary_button">Subscribe Now</a>
			</div>
		</div>
	</div>
</main>
<footer>
	<div class="container">
		<div class="footer_about">
			<div class="text_wrapper">
				<img src="/darthvider/jsp/img/logo.svg" alt="#">
				<h2>DarthVider</h2>
			</div>
			<p>DarthVider is the best game provider that you can only find </p>
			<span>@2021 Daniil Suzdaleu</span>
		</div>
	</div>
</footer>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="/darthvider/jsp/js/all.js"></script>
</html>