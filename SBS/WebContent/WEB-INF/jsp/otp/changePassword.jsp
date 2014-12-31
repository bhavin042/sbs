<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Secure Banking System</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>
	
	
	<script type="text/javascript">

  	function validatePassword(str){
 	   	var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
    	return re.test(str);
  	}

  	function checkForm(form)
  	{
   	 	if(form.userName.value == "") {
      		alert("Username cannot be blank!");
      		form.userName.focus();
      		return false;
    	}
    
   		temp = /^\w+$/;
    
   		if(!temp.test(form.userName.value)) {
      		alert("Username must contain only letters, numbers and underscores!");
      		form.userName.focus();
    	  	return false;
    	}
	  if(form.password.value != ""){
		  if(form.password.value == form.cpassword.value){
			  if(!validatePassword(form.password.value)) {
			        alert("Minimum Length of password is 6 & The password should have one number, one lowercase and one uppercase letter.");
			        form.password.focus();
			        return false;
			  }
		  }
		  else{
			  alert("New Password and Confirm Password Does not Match");
		        form.password.focus();
		        return false;
		  }
	  }else{
		  alert("Please enter new password !!");
	        form.password.focus();
	        return false;
	  }
	    return true;
  }

</script>

</head>

<body oncontextmenu="disableRightClick()">
	
		<c:if test="${successMessage != null}">
			<div style="font-size: 15px; color: green;" align="left">
				<div id="status_message">${successMessage}</div> 
			</div>					 		
		</c:if>
		
		<c:if test="${errorMessage != null}">
			<div style="font-size: 15px; color: red;" align="left">
				<div id="status_message">${errorMessage}</div> 
			</div>					 		
		</c:if>
		<c:if test="${serverErrorMessage != null}">
			<div style="font-size: 15px; color: red;" align="left">
				<div id="status_message">${serverErrorMessage}</div> 
			</div>					 		
		</c:if>
		
	<h5>Enter The Below Details</h5>
		<form:form method="post" id="forgetPasswordFormBean" commandName="forgetPasswordFormBean" action="/SBS/OTP/changePassword">
		<%-- onsubmit="return checkForm(this);" --%>
		<table>
			<tr>
				<td><form:label path="">UserName</form:label></td>
				<td><form:input id="userName" path="userName" name="userName"/></td>
			</tr>
			<tr>
				<td><form:label path="">New Password</form:label></td>
				<td><form:input id="password" path="password" name="password" type="password"/></td>
			</tr>
			<tr>
				<td><form:label path="">Confirm Password</form:label></td>
				<td><form:input id="cpassword" path="cpassword" name="cpassword" type="password"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type ="submit" value="Change Password" /></td>
			</tr>
		</table>

	</form:form>
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>