<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css"
	media="screen" />
	<script type="text/javascript">

   	function validateUserName(form)
  	{
   	 	if(form.userId.value == "") {
      		alert("Username cannot be blank!");
      		form.userId.focus();
      		return false;
    	}
   		temp = /^\w+$/;
   		if(!temp.test(form.userId.value)) {
      		alert("Username must contain only letters, numbers and underscores!");
      		form.userId.focus();
    	  	return false;
    	}
	    return true;
  }

</script>
</head>
<body>
	<h2>Display Users</h2>
	<form:form method="POST" id="unauthProfile" action="/SBS/unauthUser" commandName="unauthProfile" 
	onsubmit="return validateUserName(this);">

		<table>
			<tr>
				<th>UserID</th>
				<th> UpdatedBalance</th>
				<th>Transaction Type</th>
				<th>Authorize</th>
			</tr>
			<c:forEach var="authUser" items="${userList}">
				<tr>
					<td> ${authUser.userId}</td>
					<td> ${authUser.updatedBalance}</td>
					<td> ${authUser.transactiontype}</td>
					<td> ${authUser.authval}</td>
					
				</tr>
			</c:forEach>
			
		
			<table>
		<tr>
				<td><label id = "uid">UserId</label></td>
				<td><input name="userId" type="text"/> </td>
			</tr>
			
			<tr>
				<td colspan="2"><input type ="submit" name=Delete onclick="form.action='/SBS/unauthTrans';" value="Delete Transactions"/></td>
			</tr>
	
		</table>
	
		</table>
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<a href="${logoutUrl}">Logout</a>
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
	</form:form>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>