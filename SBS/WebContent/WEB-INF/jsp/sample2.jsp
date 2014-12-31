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
</head>

<body>

	<h2>Main Page</h2>
	<form:form method="post" id="trialFormBean" commandName="trialFormBean" action="/SBS/addContact">

		<table>
			<tr>
				<td><form:label path="">Id</form:label></td>
				<td><form:input id="id" path="id" /></td>
			</tr>
			<tr>
				<td><form:label path="">Name</form:label></td>
				<td><form:input id="name" path="name" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type ="submit" value="Add Contact" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type ="submit" onclick="form.action='/SBS/displayContact';"  value="Display Contact" /></td>
			</tr>

		</table>

	</form:form>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>