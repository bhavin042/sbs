<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<div id="logo">
		<h2 align="center">
			<a href="#">Secure Banking System</a>
		</h2>
	</div>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
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
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>


	</sec:authorize>
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
</html>