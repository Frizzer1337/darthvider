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
			<li><a href="/darthvider/" class="menu_link">Home</a></li>
			<li><a href="#" class="menu_link">Shop</a></li>
			<li><a href="#" class="menu_link">Help</a></li>
			<li><a href="#" class="menu_link">About</a></li>
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
		      <span>English</span>
		      </label>
		   <label for="option-2" class="option option-2">
		     <div class="dot"></div>
		      <span>Russian</span>
		   </label>
	   </form>
	<c:choose>
		<c:when test="${status!='SUCCESS_LOGIN'}">	
		<div class="authorization">
			<a href="#login_section"  class="login">Sign In</a>
			<a href="#login_section"  class="reg">Sign Up</a>
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
        <section class="tariffs">
            <ul class="tariffs_list">
                <li class="tariff_item"><a href="#">
                    <h3>Stupid Plan</h3>
                    <ul>
                        <li>
                            <p>0 megabytes per month</p>
                        </li>
                        <li>
                            <p>1 sms</p>
                        </li>
                        <li>
                            <p>30 seconds to the network of Ukraine</p>
                        </li>
                        <li>
                            <p>3 minutes for tyrants</p>
                        </li>
                    </ul>
                    <span class="price_wrapper"><span class="tariff_price">2000 <span class="exchange">zlotych</span></span><button
                                                                                      class="primary_button">BUY</button></span></a>
                </li><li class="tariff_item"><a href="#">
                    <h3>Stupid Plan</h3>
                    <ul>
                        <li>
                            <p>0 megabytes per month</p>
                        </li>
                        <li>
                            <p>1 sms</p>
                        </li>
                        <li>
                            <p>30 seconds to the network of Ukraine</p>
                        </li>
                        <li>
                            <p>3 minutes for tyrants</p>
                        </li>
                    </ul>
                    <span class="price_wrapper"><span class="tariff_price">2000 <span class="exchange">zlotych</span></span><button
                                                                                      class="primary_button">BUY</button></span></a>
                </li><li class="tariff_item"><a href="#">
                    <h3>Stupid Plan</h3>
                    <ul>
                        <li>
                            <p>0 megabytes per month</p>
                        </li>
                        <li>
                            <p>1 sms</p>
                        </li>
                        <li>
                            <p>30 seconds to the network of Ukraine</p>
                        </li>
                        <li>
                            <p>3 minutes for tyrants</p>
                        </li>
                    </ul>
                    <span class="price_wrapper"><span class="tariff_price">2000 <span class="exchange">zlotych</span></span><button
                                                                                      class="primary_button">BUY</button></span></a>
                </li>
            </ul>
        </section>
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
<script src="js/all.js"></script>
</html>