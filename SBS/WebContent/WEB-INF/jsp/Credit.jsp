<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add credit</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>

<script type="text/javascript">
function val_balance()
{
var balance=document.forms["Credit"]["balance"].value;
var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

/*if(userId.length<= || userId.length>=10)
	document.getElementById("uid").style.visibility="visible";*/
	if(balance=="")
		//alert ("Please Enter amount");
    	document.getElementById("bal").style.visibility="visible";

	else
		{

for (var i = 0; i < balance.length; i++) {
    if (iChars.indexOf(balance.charAt(i)) != -1) 
       // alert ("Please Enter valid user id");
    	document.getElementById("bal").style.visibility="visible";


}
}}

</script>
</head>
<body>
<div><jsp:include page="/WEB-INF/jsp/header.jsp" /></div>
<a id=Home href="${UpdatedAcc}">Home</a>
<form name="Credit" action="/SBS/updateAccount" id="CreditAccount" method="post" >
       <table>
       <!-- <tr>
			<td><div style="color:red">${errorMessage}</div></td>
			<td></td>
			</tr> -->
			<tr>
				<td align="right">Enter Amount:</td>
				<td align="left">
				<input name="balance" type="text" autocomplete="off"  onBlur="val_balance()"/></td>
				<td align="left">
				<label id="bal" style="visibility:hidden;color: red"> Please enter valid amount </label>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="submit" /></td>
				<td><input  type="reset" value="Cancel" /></td>
			</tr>
		</table>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<a href="${logoutUrl}">Logout</a>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	</form>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>