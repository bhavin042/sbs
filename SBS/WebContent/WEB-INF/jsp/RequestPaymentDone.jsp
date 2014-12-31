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
<h4>Requested payment submitted</h4>
<h4>${paymentDone}</h4>
<br>
<h4><%=(String)session.getAttribute("userName") %></h4>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<a href="${logoutUrl}">Logout</a>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>