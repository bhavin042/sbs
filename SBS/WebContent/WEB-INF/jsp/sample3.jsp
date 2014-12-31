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

	<h2>Contact Manager</h2>	
	<form:form method="POST" id="trialFormBean" commandName="trialFormBean" action="/SBS/displayContact">

		<table>
                <tr>
                     <th>ID</th>
                     <th>Name</th>
                  </tr>
                  <c:forEach var="trial" items="${trialList}">
                     <tr>
                        <td>${trial.id}</td>
                        <td>${trial.name}</td>
                     </tr>
                   </c:forEach>
            </table>
	</form:form>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>