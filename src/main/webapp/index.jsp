<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>DarthVider.by-internet to every house!</title>
<script type="text/javascript">

function formAutoSubmit () {

		document.getElementById("preload").submit();
	
	}

</script>

</head>

<body onload="formAutoSubmit()">
 <form id="preload" action="<c:url value="/controller"/>">
        <input name="command" type="hidden" value="preload"/>
    </form>
</body>
</html>