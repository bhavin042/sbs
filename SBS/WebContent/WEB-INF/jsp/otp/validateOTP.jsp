<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>OTP Validation</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<link type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/jquery.keypad.css" rel="stylesheet"> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/bootstrap/js/jquery.plugin.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/css/bootstrap/js/jquery.keypad.js"></script>
	
	<script type="text/javascript">
	function validateUserName(form)
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
	    return true;
  }

	  $(function() {
		$('#otp').keypad(); 
	  });
		  
	  $('#viewKeypad').click(function() { 
		  alert('The current value is: ' + $('#otpKeypad').val()); 
	  }); 
		 
	  $('#removeKeypad').toggle(function() { 
	       $(this).text('Re-attach'); 
	       $('#otpKeypad').keypad('destroy'); 
      }, 
	    function() { 
	        $(this).text('Remove'); 
	        $('#otp').keypad(); 
	    } 
   	);
	</script>
</head>

<body>
		<c:if test="${otpGenerationMessage != null}">
			<div style="font-size: 15px; color: green;" align="left">
				<div id="status_message">${otpGenerationMessage}</div> 
			</div>					 		
		</c:if>
		
		<c:if test="${errorMessage != null}">
			<div style="font-size: 15px; color: red;" align="left">
				<div id="status_message">${errorMessage}</div> 
			</div>					 		
		</c:if>
	
		<h5>Enter The Below Details</h5>
		<form:form method="post" id="forgetPasswordFormBean" commandName="forgetPasswordFormBean" action="/SBS/OTP/validateOTP"
		onsubmit="return validateUserName(this);">

		<table>
			<tr>
				<td><form:label path="">UserName</form:label></td>
				<td><form:input id="userName" path="userName" name="userName"/></td>
			</tr>
			<tr>
				<td><form:label path="">OTP</form:label></td>
				<td><form:input id="otp" path="otp" onclick="$(otp).keypad();"/></td>
				<td colspan="2"><input type ="submit" value="Validate OTP" /></td>
			</tr>
		</table>

	</form:form>
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>