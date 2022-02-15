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
	<script type="text/javascript">

	function formAutoSubmit () {
	
			document.getElementById("preload").submit();
		
		}

    </script>
    <script type = "text/javascript" >  
    function disableBack() { window.history.forward(); }  
    setTimeout("disableBack()", 0);  
    window.onunload = function () { null };  
	</script> 
</head>
<c:choose>
	<c:when test="${cabinet_exist=='CABINET_EXISTS'}">
	<body onload="formAutoSubmit()">
	</c:when>
	<c:otherwise>
	<body>
	</c:otherwise>
</c:choose>
<header class="header">
	<div class="container">
		<div class="logo">
			<img src="/darthvider/jsp/img/logo.svg" alt="Logotype" class="header_logo"> <div><fmt:message key="header.logo"/></div>
		</div>
		<ul class="main_menu">
			<li><a href="/darthvider/jsp/main.jsp" class="menu_link"><fmt:message key="header.menu.main"/></a></li>
			<li><a href="/darthvider/jsp/tariffs.jsp" class="menu_link"><fmt:message key="header.menu.tariff"/></a></li>
			<li><a href="/darthvider/jsp/help.jsp" class="menu_link"><fmt:message key="header.menu.help"/></a></li>
			<li><a href="/darthvider/jsp/about.jsp" class="menu_link"><fmt:message key="header.menu.about"/></a></li>
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
		      <div><fmt:message key="header.locale.en"/></div>
		      </label>
		   <label for="option-2" class="option option-2">
		     <div class="dot"></div>
		      <div><fmt:message key="header.locale.ru"/></div>
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
				<li><a href="#" class="menu_link">${email}</a></li>
				<li><a onclick="disableBack();" href="/darthvider/controller?command=logout"><img src="/darthvider/jsp/img/logout.svg" alt="Exit"></a></li>
			</ul>
		</c:otherwise>
	</c:choose>
	</div>	
</header>
<main class="container">
		<c:choose>
			<c:when test="${status!='SUCCESS_LOGIN'}">	
				<c:redirect url="/jsp/error/401.jsp"/>
			</c:when>
			<c:when test="${user_status!=0}">
			<div class = "block_message error_message"> Sorry,you're blocked on our site. If you don't agree or don't know reason you can contact us via helpform. </div>
			</c:when>
			<c:when test="${role==0}">
			<div class = "block_message error_message">You can't create cabinet on our site, if you don't verify your email. </div>
			</c:when>
			<c:when test="${cabinet_exist != 'CABINET_EXISTS' && cabinet_exist != 'CABINET_LOADED'}">
			<div class="cabinet_welcome"><fmt:message key="cabinet.welcome"/></div>
			<div class="error_cabinet">
			<c:if test="${cabinet_exist == 'WRONG_NAME'}">
					<span class="error_message"><fmt:message key="cabinet.error.name"/></span>
					</c:if>
					<c:if test="${cabinet_exist == 'WRONG_SURNAME'}">
					<span class="error_message"><fmt:message key="cabinet.error.surname"/></span>
					</c:if>
					<c:if test="${cabinet_exist == 'WRONG_PHONE'}">
					<span class="error_message"><fmt:message key="cabinet.error.phone"/></span>
					</c:if>
					<c:if test="${cabinet_exist == 'WRONG_CITY'}">
					<span class="error_message"><fmt:message key="cabinet.error.city"/></span>
			</c:if>
			</div>
            <section class="cabinet-reg">          	
                <div class="autharization_form">

                <form class="authorization_login" id="reg"  action="<c:url value="/controller"/>">
               		<input type="hidden" name="command" value="createCabinet" >
                    <input type="text" placeholder="Name" name="firstname" value="${firstname}"required  >
                    <input type="text" placeholder="Surname" name="lastname" value="${lastname}" required >
                    <input type="tel" placeholder="Telephone(+375XXXXXXXXX)" value="${phone}" name="phone" required >
                    <input type="text" placeholder="City" name="city" value="${city}" required >
                    <button class="authorization_submit primary_button">
                       <fmt:message key="help.button"/>
                    </button>
                </form>	
                </div>
            </section>
            </c:when>
            <c:when test="${cabinet_exist=='CABINET_EXISTS'}">
            <section class="cabinet_profile">
            	 <form id="preload" action="<c:url value="/controller"/>">
			        <input name="command" type="hidden" value="preloadCabinet"/>
			    </form> 
			</c:when>
			<c:when test="${cabinet_exist=='CABINET_LOADED'}">
                <div class="cabinet_features">
                    <div class="cabinet_personal">
                        <h2 class="cabinet_title">
                            <fmt:message key="cabinet.personal.main"/>
                        </h2>
                        <div class="personal_data">
                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.personal.login"/> <a href="#login_change"><img src="img/gear.svg" alt="#"></a></h3>
                                <span id="data_login">${login}</span>
                            </div>
                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.personal.name"/> <a href="#name_change"><img src="img/gear.svg" alt="#"></a></h3>
                                <span id="data_name">${name}</span>
                            </div>

                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.personal.surname"/><a href="#surname_change"><img src="img/gear.svg" alt="#"></a></h3>
                                <span id="data_surname">${surname}</span>
                            </div>
                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.personal.phone"/><a href="#phone_change"><img src="img/gear.svg" alt="#"></a></h3>
                                <span id="data_phone">${phone}</span>
                            </div>
                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.personal.city"/><a href="#city_change"><img src="img/gear.svg" alt="#"></a>
                                </h3>
                                <span id="data_city">${city}</span>
                            </div>
                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.personal.password"/><a href="#password_change"><img src="img/gear.svg" alt="#"></a> </h3>
                                <span id="data_pass">${password}</span>
                            </div>
                        </div>
                    </div>
                    <div class="cabinet_personal">
                    	<c:if test="${role=='2'}">
                    	<a href="/darthvider/jsp/admin.jsp" style="font-size:40px"><fmt:message key="cabinet.admin"/></a>
                    	</c:if>
                    	<h2 class="cabinet_title">
                          <fmt:message key="cabinet.balance"/>
                           <div class="data_elem">
                                <span class="cabinet_title" style="font-size:40px">${balance}$</span>
                            </div>
                    	</h2>
                        <h2 class="cabinet_title">
                            <fmt:message key="cabinet.contract.main"/>
                        </h2>
                        <div class="personal_data">
                            <div class="data_elem">
                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.contract.tariffs"/></h3>
                                <span id="data_discount">
                                <c:forEach var="tariff" items="${tariffs}">
                                <a href="http://localhost:8080/darthvider/jsp/tariff.jsp?tariff_id=${tariff}">${tariff}</a>
                                </c:forEach>
                                </span>
                            </div>
                                <h3><fmt:message key="cabinet.contract.id"/></h3>
                                <span id="data_contract">${contractId}</span>
                            </div>
                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.contract.start"/></h3>
                                <span id="data_start_date">${contractStart}</span>
                            </div>

                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.contract.end"/></h3>
                                <span id="data_end_date">${contractEnd}</span>
                            </div>
                            <div class="data_elem">
                                <h3><fmt:message key="cabinet.contract.discount"/></h3>
                                <span id="data_discount">${contractDiscount}%</span>
                            </div>
                        </div>
                    </div>

                </div>
            </section>
 <div id="login_change" class="zatemnenie">
      <div class="okno">
      <div><fmt:message key="cabinet.form.login"/><a href="#"><img src="/darthvider/jsp/img/cross.svg" style="margin-left:10px;" height="16px" alt="Press to change"></a></div>
       <form class="cabinet_form"  action="<c:url value="/controller"/>">
		
		<input type="hidden" name="command" value="changeData">
		
		<input type="hidden" name="typeofchange" value="login">
		
    	<input  class="cabinet_form_elem" type="text"  name="datatochange" placeholder="Data to change.." required>

    	<input class="cabinet_form_elem" type="password" name="password" placeholder="Your password.." required>
    	
    	<input class="cabinet_form_elem" type="password" name="password_repeat" placeholder="Repeat your password.." required>
    	
    	<c:if test="${cabinet_status == 'PASSWORD_NOT_REPEATED'}">
		<span class="error_message"><fmt:message key="main.login.passnotrepeated"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'DATA_ALREADY_EXISTS'}">
		<span class="error_message"><fmt:message key="main.login.userexist"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'BAD_LOGIN'}">
		<span class="error_message"><fmt:message key="main.login.badlogin"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'WRONG_PASSWORD'}">
		<span class="error_message"><fmt:message key="cabinet.error.wrongpass"/></span>
		</c:if>
    
    	
    	<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="help.button"/>
		</button>

    </form>

      </div>
</div>	
 <div id="name_change" class="zatemnenie">
      <div class="okno">
      <div><fmt:message key="cabinet.form.name"/><a href="#"><img src="/darthvider/jsp/img/cross.svg" style="margin-left:10px;" height="16px" alt="Press to change"></a></div>
       <form class="cabinet_form"  action="<c:url value="/controller"/>">
		
		<input type="hidden" name="command" value="changeData">
		
		<input type="hidden" name="typeofchange" value="name">
		
    	<input  class="cabinet_form_elem" type="text"  name="datatochange" placeholder="Data to change.." required>

    	<input class="cabinet_form_elem" type="password" name="password" placeholder="Your password.." required>
    	
    	<input class="cabinet_form_elem" type="password" name="password_repeat" placeholder="Repeat your password.." required>
    	
    	<c:if test="${cabinet_status == 'PASSWORD_NOT_REPEATED'}">
		<span class="error_message"><fmt:message key="main.login.passnotrepeated"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'BAD_NAME'}">
		<span class="error_message"><fmt:message key="cabinet.error.name"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'WRONG_PASSWORD'}">
		<span class="error_message"><fmt:message key="cabinet.error.wrongpass"/></span>
		</c:if>
    
    	
    	<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="help.button"/>
		</button>

    </form>

      </div>
</div>	
<div id="surname_change" class="zatemnenie">
      <div class="okno">
      <div><fmt:message key="cabinet.form.surname"/><a href="#"><img src="/darthvider/jsp/img/cross.svg" style="margin-left:10px;" height="16px" alt="Press to change"></a></div>
       <form class="cabinet_form"  action="<c:url value="/controller"/>">
		
		<input type="hidden" name="command" value="changeData">
		
		<input type="hidden" name="typeofchange" value="surname">
		
    	<input  class="cabinet_form_elem" type="text"  name="datatochange" placeholder="Data to change.." required>

    	<input class="cabinet_form_elem" type="password" name="password" placeholder="Your password.." required>
    	
    	<input class="cabinet_form_elem" type="password" name="password_repeat" placeholder="Repeat your password.." required>
    	
    	<c:if test="${cabinet_status == 'PASSWORD_NOT_REPEATED'}">
		<span class="error_message"><fmt:message key="main.login.passnotrepeated"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'BAD_SURNAME'}">
		<span class="error_message"><fmt:message key="cabinet.error.surname"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'WRONG_PASSWORD'}">
		<span class="error_message"><fmt:message key="cabinet.error.wrongpass"/></span>
		</c:if>
    
    	
    	<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="help.button"/>
		</button>

    </form>

      </div>
</div>	
<div id="phone_change" class="zatemnenie">
      <div class="okno">
      <div><fmt:message key="cabinet.form.phone"/><a href="#"><img src="/darthvider/jsp/img/cross.svg" style="margin-left:10px;" height="16px" alt="Press to change"></a></div>
       <form class="cabinet_form"  action="<c:url value="/controller"/>">
		
		<input type="hidden" name="command" value="changeData">
		
		<input type="hidden" name="typeofchange" value="phone">
		
    	<input  class="cabinet_form_elem" type="text"  name="datatochange" placeholder="Data to change.." required>

    	<input class="cabinet_form_elem" type="password" name="password" placeholder="Your password.." required>
    	
    	<input class="cabinet_form_elem" type="password" name="password_repeat" placeholder="Repeat your password.." required>
    	
    	<c:if test="${cabinet_status == 'PASSWORD_NOT_REPEATED'}">
		<span class="error_message"><fmt:message key="main.login.passnotrepeated"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'DATA_ALREADY_EXISTS'}">
		<span class="error_message"><fmt:message key="main.login.userexist"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'BAD_PHONE'}">
		<span class="error_message"><fmt:message key="cabinet.error.phone"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'WRONG_PASSWORD'}">
		<span class="error_message"><fmt:message key="cabinet.error.wrongpass"/></span>
		</c:if>
    
    	
    	<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="help.button"/>
		</button>

    </form>

      </div>
</div>	
<div id="city_change" class="zatemnenie">
      <div class="okno">
      <div><fmt:message key="cabinet.form.city"/><a href="#"><img src="/darthvider/jsp/img/cross.svg" style="margin-left:10px;" height="16px" alt="Press to change"></a></div>
       <form class="cabinet_form"  action="<c:url value="/controller"/>">
		
		<input type="hidden" name="command" value="changeData">
		
		<input type="hidden" name="typeofchange" value="city">
		
    	<input  class="cabinet_form_elem" type="text"  name="datatochange" placeholder="Data to change.." required>

    	<input class="cabinet_form_elem" type="password" name="password" placeholder="Your password.." required>
    	
    	<input class="cabinet_form_elem" type="password" name="password_repeat" placeholder="Repeat your password.." required>
    	
    	<c:if test="${cabinet_status == 'PASSWORD_NOT_REPEATED'}">
		<span class="error_message"><fmt:message key="main.login.passnotrepeated"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'BAD_CITY'}">
		<span class="error_message"><fmt:message key="cabinet.error.city"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'WRONG_PASSWORD'}">
		<span class="error_message"><fmt:message key="cabinet.error.wrongpass"/></span>
		</c:if>
    
    	
    	<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="help.button"/>
		</button>

    </form>

      </div>
</div>	
<div id="password_change" class="zatemnenie">
      <div class="okno">
      <div><fmt:message key="cabinet.form.password"/><a href="#"><img src="/darthvider/jsp/img/cross.svg" style="margin-left:10px;" height="16px" alt="Press to change"></a></div>
       <form class="cabinet_form"  action="<c:url value="/controller"/>">
		
		<input type="hidden" name="command" value="changeData">
		
		<input type="hidden" name="typeofchange" value="newpassword">
		
    	<input  class="cabinet_form_elem" type="text"  name="datatochange" placeholder="Data to change.." required>

    	<input class="cabinet_form_elem" type="password" name="password" placeholder="Your password.." required>
    	
    	<input class="cabinet_form_elem" type="password" name="password_repeat" placeholder="Repeat your password.." required>
    	
    	<c:if test="${cabinet_status == 'PASSWORD_NOT_REPEATED'}">
		<span class="error_message"><fmt:message key="main.login.passnotrepeated"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'BAD_NEW_PASSWORD'}">
		<span class="error_message"><fmt:message key="main.login.badpass"/></span>
		</c:if>
		<c:if test="${cabinet_status == 'WRONG_PASSWORD'}">
		<span class="error_message"><fmt:message key="cabinet.error.wrongpass"/></span>
		</c:if>
    
    	
    	<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="help.button"/>
		</button>

    </form>

      </div>
</div>	
		</c:when>
		</c:choose>
</main>
<footer>
	<div class="container">
		<div class="footer_about">
			<div class="text_wrapper">
				<img src="/darthvider/jsp/img/logo.svg" alt="#">
				<h2><fmt:message key="footer.logo"/></h2>
			</div>
			<p><fmt:message key="footer.text"/> </p>
			<div><fmt:message key="footer.author"/></div>
		</div>
	</div>
</footer>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="/darthvider/jsp/js/all.js"></script>
</html>