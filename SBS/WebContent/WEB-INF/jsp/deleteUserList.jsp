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
	<form:form method="POST" id="delProfile" action="/SBS/deleteUserList" commandName="delProfile" 
	onsubmit="return validateUserName(this);">

		<table border="1">
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Address</th>
				
				<th>Identification Type</th>
				<th>Identification Number</th>
			
				<th>Role Id</th>
				<th>Security question 1</th>
				<th>Security Answer 1</th>
				<th>Security question 2</th>
				<th>Security Answer 2</th>
				<th>Security question 3</th>
				<th>Security Answer 3</th>
			</tr>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.userName}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.emailId}</td>
					<td>${user.address}</td>
					
					<td>${user.idType}</td>
					<td>${user.idNo}</td>
					
					<td>${user.roleId}</td>
					<td>${user.securityQues1}</td>
					<td>${user.securityAns1}</td>
					<td>${user.securityQues2}</td>
					<td>${user.securityAns2}</td>
					<td>${user.securityQues3}</td>
					<td>${user.securityAns3}</td>
				</tr>
			</c:forEach>
			</table>
			<h2>  Admin Functions</h2>
		<table>
		<tr>
				<td><label id = "uid">UserId</label></td>
				<td><input name="userId" type="text"/> </td>
			</tr>
			
			<tr>
				<td colspan="2"><input type ="submit" name=Delete onclick="form.action='/SBS/deleteUser';" value="Delete User"/></td>
			</tr>
	
		</table>
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<a href="${logoutUrl}">Logout</a>
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form:form>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>