<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<title>Spring 3 MVC Series - Contact Manager</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>

<script type="text/javascript">

   	function validateUserName(form)
  	{
   	 	if(form.ruName.value == "") {
      		alert("Username cannot be blank!");
      		form.ruName.focus();
      		return false;
    	}
   		temp = /^\w+$/;
   		if(!temp.test(form.ruName.value)) {
      		alert("Username must contain only letters, numbers and underscores!");
      		form.ruName.focus();
    	  	return false;
    	}
	    return true;
  }

</script>

</head>

<body>

	<h2>Main Page</h2>
	<form:form method="post" id="pkiFormBean" commandName="PKIFormBean" action="/SBS/PKI/verifyUpCerti" 
	onsubmit="return validateUserName(this);">

		<table>
			<tr>
				<td><form:label path="">Please enter username of receiver:</form:label></td>
				<td><form:input id="ruName" path="ruName" name="ruName"/></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type ="submit" value="Verify Certificate" /></td>
			</tr>
			
		</table>

	</form:form>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>