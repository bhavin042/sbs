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
             <!--     <tr>
                     <th>ID</th>
                     <th>First Name</th>
                     <th>Last Name</th>
                     <th>Email</th>
                     <th>Address</th>
                    
                     <th>Identification Type</th>
                     <th>Identification Number</th>
              
                     <th>Role</th>
                     <th>Security question 1</th>
                     <th>Security Answer 1</th>
                     <th>Security question 2</th>
                     <th>Security Answer 2</th>
                     <th>Security question 3</th>
                     <th>Security Answer 3</th>
                     
                  </tr>
                  -->
                  <c:forEach var="user" items="${userList}">
                     <tr>
                        <th>UserName</th>
                        <td>${user.userName}</td>
                     </tr>
                     <tr>
                        <th>First Name</th>
                        <td>${user.firstName}</td>
                     </tr>
                     <tr>
                        <th>Last Name</th>
                        <td>${user.lastName}</td>
                     </tr>
                     <tr>
                        <th>Email</th>
                        <td>${user.emailId}</td>
                     </tr>
                     <tr>
                        <th>Address</th>
                        <td>${user.address}</td>
                     </tr>
                    
                     <tr>
                        <th>Identification Type</th>
                        <td>${user.idType}</td>
                     </tr>
                     <tr>
                        <th>Identification Number</th>
                        <td>${user.idNo}</td>
                     </tr>
                   
                      <tr>
                        <th>Role Id</th>
                        <td>${user.roleId}</td>
                      </tr>
                      <tr>
                        <th>Security question 1</th>
                        <td>${user.securityQues1}</td>
                      </tr>
                      <tr>
                      <th>Security Answer 1</th>
                        <td>${user.securityAns1}</td>
                      </tr>
                      <tr>
                        <th>Security question 1</th>
                        <td>${user.securityQues2}</td>
                      </tr>
                      <tr>
                      <th>Security Answer 1</th>
                        <td>${user.securityAns2}</td>
                      </tr>
                      <tr>
                         <th>Security question 1</th>
                        <td>${user.securityQues3}</td>
                      </tr>
                      <tr>
                      <th>Security Answer 1</th>
                        <td>${user.securityAns3}</td>   
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