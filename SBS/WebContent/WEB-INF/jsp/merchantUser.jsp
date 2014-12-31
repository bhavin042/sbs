<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<title>Individual User Profile Page</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>
<style type="text/css">
#Profile,#Acc,#Credit,#Debit,#Transfer,#Payments{
padding-right: 50px;
padding-left: 50px;
font-size: 20px;
color: black;
font-family: "Times New Roman";
}
</style>
</head>
<body>
<div><jsp:include page="/WEB-INF/jsp/header.jsp" /></div>
<h2>Welcome User</h2>
<a id=Profile href="profile.html">Profile</a>
<a id=Acc href="account.html">Account Details</a>
<a id=Credit href="AddCredit">Credit</a>
<a id=Debit href="Debit">Debit</a>
<a id=Transfer href="TransferAmount">Transfer Funds</a>
<a id=Payments href="PaymentRequest">Submit Requests</a>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<a href="${logoutUrl}">Logout</a>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>	
</body>
</html>
