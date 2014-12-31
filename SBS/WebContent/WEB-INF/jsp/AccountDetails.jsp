<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>
</head>
<body>
<h2>Contact Manager</h2>	
<a id=Home href="${UpdatedAcc}">Home</a>
	<form:form method="POST" id="profile" >

		<table>
                  <c:forEach var="Acc" items="${accList}">
                     <tr>
                        <th>ID</th>
                        <td>${Acc.userName}</td>
                     </tr>
                     <tr>
                        <th>Account Number</th>
                        <td>${Acc.accountNum}</td>
                     </tr>
                     <tr>
                        <th>Balance</th>
                        <td>${Acc.balance}</td>
                     </tr>
                   </c:forEach>
            </table>
            <c:url value="/j_spring_security_logout" var="logoutUrl" />
		<a href="${logoutUrl}">Logout</a>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form:form>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>