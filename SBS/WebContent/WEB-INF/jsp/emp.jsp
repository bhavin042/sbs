<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<div id="logo">
		<h2 align="center">
			<a href="#">Secure Banking System</a>
		</h2>
	</div>
	<h3>Title : ${title}</h3>
	<a href="authUser"><h3>Authenticate Transactions</h3></a>
	<a href="unauthUser"><h3>Delete User Transaction</h3></a>
	
	<h1>Message : ${message}</h1>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	<div align="center">
		<div class="container footer">
			<div class="navbar navbar-fixed-bottom">
				<ul class="nav nav-pills" style="float: right">
					<li><a href="#">© Copyright Secure Banking System- CSE 545
							· Design:Group 2</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html></html>