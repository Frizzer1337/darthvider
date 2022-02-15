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
    <script>
		function myFunction() {
		  // Declare variables
		  var input, filter, table, tr, td, i, txtValue;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		
		  // Loop through all table rows, and hide those who don't match the search query
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[2];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }
		  }
		}
		
		function myFunction2() {
			  // Declare variables
			  var input, filter, table, tr, td, i, txtValue;
			  input = document.getElementById("myInput");
			  filter = input.value.toUpperCase();
			  table = document.getElementById("myTable");
			  tr = table.getElementsByTagName("tr");
			
			  // Loop through all table rows, and hide those who don't match the search query
			  for (i = 0; i < tr.length; i++) {
			    td = tr[i].getElementsByTagName("td")[1];
			    if (td) {
			      txtValue = td.textContent || td.innerText;
			      if (txtValue.toUpperCase().indexOf(filter) > -1) {
			        tr[i].style.display = "";
			      } else {
			        tr[i].style.display = "none";
			      }
			    }
			  }
			}
		
		 function hideFunction(){
			 
			    document.getElementById("newTariffForm").style.display = "block";
			    }
		 
		 function hideFunction1(){
			 
			    document.getElementById("newAdminForm").style.display = "block";
			    }
		 function hideFunction2(){
			 
			    document.getElementById("changePriceForm").style.display = "block";
			    }
</script>
<script type = "text/javascript" >  
    function disableBack() { window.history.forward(); }  
    setTimeout("disableBack()", 0);  
    window.onunload = function () { null };  
	</script> 
</head>
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
				<li><a href="/darthvider/jsp/cabinet.jsp" class="menu_link">${email}</a></li>
				<li><a id="logout" onclick="disableBack();" href="/darthvider/controller?command=logout"><img src="/darthvider/jsp/img/logout.svg" alt="Exit"></a></li>
			</ul>
		</c:otherwise>
	</c:choose>
	</div>	
</header>
<main class="container">	
		<c:choose>
			<c:when test="${role!='2'}">	
				<c:redirect url="/jsp/error/401.jsp"/>
			</c:when>
			<c:otherwise>
			<form name="tariff_form" id="form-1" action="<c:url value="/controller"/>">
					<input type="hidden" name="command" value="allUsers">	
					<a href="javascript:$('form#form-1').submit()" class="primary_button"><fmt:message key="admin.allUsers"/></a>
			</form>
			<c:if test="${not empty users}">
			<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for emails..">
			<div class="tableWrapper">
			<table id="myTable">
			<tr>
				<th class="user_data">User id</th>
				<th class="user_data">User login</th>
				<th class="user_data">User email</th>
				<th class="user_data">User role</th>
				<th class="user_data">User balance</th>
				<th class="user_data">Block</th>
			</tr>
			<c:forEach var="user" items="${users}" varStatus="loop">
			<tr>
                                <td class="user_data">${user.id}</td>
                                <td  class="user_data"> ${user.login}</td>
                                <td  class="user_data">${user.email}</td>
                                <td  class="user_data">${user.role}</td>                            
                                <td  class="user_data">${user.balance}</td>   
                                <c:if test="${user.status =='0'}">                   
                                <td  class="user_data">
		                            <form  id="block-user${user.id}" name="${user.id}" action="<c:url value="/controller"/>">
										<input type="hidden" name="command" value="blockUser">	
										<input type="hidden" name="user_to_block" value="${user.id}">	
										   <a href="javascript:$('form#block-user${user.id}').submit()" onclick=><img src="/darthvider/jsp/img/lock.svg"></a>
								 </form>
								</td>
								</c:if>
								 <c:if test="${user.status=='1'}">                   
                                <td  class="user_data">
		                            <form  id="block-user${user.id}" name="${user.id}" action="<c:url value="/controller"/>">
										<input type="hidden" name="command" value="unlockUser">	
										<input type="hidden" name="user_to_unlock" value="${user.id}">	
										   <a href="javascript:$('form#block-user${user.id}').submit()" style="margin-right:50px;" onclick=><img src="/darthvider/jsp/img/unlock.svg"></a>
								 </form>
								</td>
								</c:if>
                                
             </tr>
             </c:forEach>
             </table>
             </div>
             </c:if>
			<form name="tariff_form" id="form-2" action="<c:url value="/controller"/>">
					<input type="hidden" name="command" value="allTariffs">	
					<a href="javascript:$('form#form-2').submit()" class="primary_button"><fmt:message key="admin.allTariffs"/></a>
			</form>
			<c:if test="${not empty admin_tariffs}">
			<input type="text" id="myInput" onkeyup="myFunction2()" placeholder="Search for tariff names..">
			<div class="tableWrapper">
			<table id="myTable">
			<tr>
				<th class="user_data">Tariff id</th>
				<th class="user_data">Tariff name</th>
				<th class="user_data">Tariff price</th>
				<th class="user_data">Tariff discount</th>
				<th class="user_data">Tariff due type</th>
				<th class="user_data">Tariff active</th>
			</tr>
			<c:forEach var="tariff" items="${admin_tariffs}">
			<tr>
                                <td class="user_data">${tariff.id}</td>
                                <td  class="user_data"> ${tariff.name}</td>
                                <td  class="user_data">${tariff.price}</td>
                                <td  class="user_data">${tariff.discount}</td>
                                <td  class="user_data">${tariff.dueType}</td>    
                                <td  class="user_data">   
                                <c:if test="${tariff.status =='1'}">                       
                                <form  id="block-tariff${tariff.id}" name="${tariff.id}" action="<c:url value="/controller"/>">
										<input type="hidden" name="command" value="blockTariff">	
										<input type="hidden" name="tariff_to_block" value="${tariff.id}">	
										   <a href="javascript:$('form#block-tariff${tariff.id}').submit()" onclick=><img src="/darthvider/jsp/img/lock.svg"></a>
								</form>
								</c:if>
								 <c:if test="${tariff.status =='0'}">                       
                                <form  id="block-tariff${tariff.id}" name="${tariff.id}" action="<c:url value="/controller"/>">
										<input type="hidden" name="command" value="unlockTariff">	
										<input type="hidden" name="tariff_to_unlock" value="${tariff.id}">	
										   <a href="javascript:$('form#block-tariff${tariff.id}').submit()"  style="margin-right:50px;" onclick=><img src="/darthvider/jsp/img/unlock.svg"></a>
								</form>
								</c:if>
								</td> 
								
                                
             </tr>
             </c:forEach>
             </table>
             </div>
             </c:if>
            <a id="newTariffButton" onclick="hideFunction();" class="primary_button"><fmt:message key="admin.newTariff"/></a>
            <div class="autharization_form tariff_form ">
			<form  class="authorization_login" style="display:none" id="newTariffForm" action="<c:url value="/controller"/>">
					<input type="hidden"   name="command"   value="newTariff"  required>
					<input type="text"   name="tariffName"   placeholder="Tariff name(1-30)"  required>
					<input type="number" step="0.1"  name="tariffPrice"  placeholder="Tariff price"  required>
					<input type="number" step="1" min="0" max="100" name="tariffDiscount" placeholder="Tariff discount"  required>
					<input type="number" step="1" min="0" max="2" name="tariffDue"  placeholder="Due type(0,1,2)"  required>
					<textarea type="textarea"  rows="3" cols="52"  placeholder="Tariff short info" name="tariffShortInfo" required></textarea>
					<textarea type="textarea" rows="5" cols="52" name="tariffInfo" placeholder="Tariff info"  required></textarea>
					<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="admin.finishTariff"/>
					</button>

			</form>
			</div>
			<a  id="newAdminButton" class="primary_button" onclick="hideFunction1();"><fmt:message key="admin.newAdmin"/></a>	
			<div class="autharization_form tariff_form ">
					<form  class="authorization_login" style="display:none" id="newAdminForm" action="<c:url value="/controller"/>">
					<input type="hidden"   name="command"   value="newAdmin"  required>
					<input type="number" step="1" placeholder="User id..." name="adminId" required>
					<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="admin.finishAdmin"/>
					</button>
					</form>
			</div>
			<a  id="changePriceButton" class="primary_button" onclick="hideFunction2();"><fmt:message key="admin.changePrice"/></a>	
			<div class="autharization_form tariff_form ">
					<form  class="authorization_login" style="display:none" id="changePriceForm" action="<c:url value="/controller"/>">
					<input type="hidden"   name="command"   value="changePrice"  required>
					<input type="number" step="1" placeholder="Tariff id..." name="tariff_id" required>
					<input type="number" step="0.1"  placeholder="New price..." name="tariffPrice" required>
					<button type="submit" class="authorization_submit primary_button">
						<fmt:message key="admin.finishPrice"/>
					</button>
					</form>
			</div>
			<form name="tariff_form" id="form-3" action="<c:url value="/controller"/>">
					<input type="hidden" name="command" value="collectMoney">	
					<a href="javascript:$('form#form-3').submit()" class="primary_button"><fmt:message key="admin.collectMoney"/></a>
			</form>
			</c:otherwise>
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