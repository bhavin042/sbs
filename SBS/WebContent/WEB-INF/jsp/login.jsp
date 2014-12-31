<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page session="true"%>
<html>
<head>
<title>Secure Banking System</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<div><jsp:include page="/WEB-INF/jsp/header.jsp" /></div>

<script type="text/javascript">
function val_UserName()
{
var userName=document.forms["loginForm"]["username"].value;
var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?0123456789";

/*if(userId.length<= || userId.length>=10)
	document.getElementById("uid").style.visibility="visible";*/
	if(userName=="")
		//alert ("Please Enter User Name");
		document.getElementById("UName").style.visibility="visible";

	else
		{

for (var i = 0; i < userName.length; i++) {
    if (iChars.indexOf(userName.charAt(i)) != -1) 
       // alert ("Please Enter valid user id");
    	document.getElementById("UName").style.visibility="visible";


}
}}
function val_password()
{
var password=document.forms["loginForm"]["password"].value;
var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";

/*if(userId.length<= || userId.length>=10)
	document.getElementById("uid").style.visibility="visible";*/
	if(password=="")
    	document.getElementById("pwd").style.visibility="visible";
	else
		{

for (var i = 0; i < password.length; i++) {
    if (iChars.indexOf(password.charAt(i)) != -1) 
       // alert ("Please Enter valid user id");
    	document.getElementById("pwd").style.visibility="visible";


}
}}

</script>


</head>
<body onload='document.loginForm.username.focus();'>
	
	<label id="UName" style="visibility:hidden;color: red"> Please enter valid User Name </label>
				<label id="pwd" style="visibility:hidden;color: red"> Please enter valid Password </label>
	<div id="login-box">

		<h3 align="center">SBS Login</h3>

		<c:if test="${successMessage != null}">
			<div style="font-size: 15px; color: green;" align="left">
				<div id="status_message">${successMessage}</div> 
			</div>					 		
		</c:if>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username' onBlur="val_UserName()"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' onBlur="val_password()"/></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div align="left"><a href="creatUserProfile">Create Profile</a></div>
			<div align="left"><a href="<%=request.getContextPath()%>/OTP/generateOTP">Forget Password ?</a></div>
			<%-- <div align="left"><a href="<%=request.getContextPath()%>/PKI/uploader">Upload certi</a></div> --%>
			
		</form>
	</div>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>