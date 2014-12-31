
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
<div><jsp:include page="/WEB-INF/jsp/header.jsp" /></div>
<a id=Home href="${UpdatedAcc}">Home</a>
<h2>List of Payment Requests</h2>
	<form:form method="POST" id="requestList" >
	<!--  
			<div style="color:red">${errorMessage}</div>
			-->
			
		<table>
                  <tr>
                     <th>To UserID</th>
                     <th>Amount</th>
                     <th>description</th>
                     <th>Make Payment</th>
                  </tr>
                  
                  <c:forEach var="req" items="${requestList}">
                     <tr>                    
                        <td>${req.toID}</td>
                        <td>${req.amount}</td>
                        <td>${req.description}</td>
                        <td><a id=makePayment href="makePayment/${req.reqID}">Make Payment</a></td>
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