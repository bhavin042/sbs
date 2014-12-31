<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>
</head>
<body>
<div><jsp:include page="/WEB-INF/jsp/header.jsp" /></div>
<a id=Home href="${UpdatedAcc}">Home</a>
<form action="/SBS/SubmitRequest" id="SubmitRequest" method="post" >
       <table>  
       <tr>
			<td><div style="color:red">${errorMessage}</div></td>
			<td></td>
			</tr>    
       <tr>
				<td align="right">Enter UserName:</td>
				<td align="left">
				<input name="fromID" type="text" autocomplete="off" />
				</td>
			</tr>
			<tr>
				<td align="right">Enter Amount:</td>
				<td align="left">
				<input name="amount" type="text" autocomplete="off" />
				</td>
			</tr>
			<tr>
				<td align="right">Enter Description:</td>
				<td align="left">
				<input name="description" type="text" autocomplete="off" />
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