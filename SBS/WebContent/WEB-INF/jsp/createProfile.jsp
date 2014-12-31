                                                                                                                                                                                                                                                                                                                                                                                                                           <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User  Profile Creation</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui-1.10.3.custom.css" media="screen"/>
</head>
<body>
<div><jsp:include page="/WEB-INF/jsp/header.jsp" /></div>
<!--  <c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	-->
	<form action="/SBS/creatUserProfile" id="userProfile" method="POST" >
		<table>
			<tr>
			<td><div style="color:red">${errorMessage}</div></td>
			<td></td>
			</tr>
			<tr>
				<td align="right">User Name:</td>
				<td align="left">
				<spring:bind path="user.userName">
				<input name="userName" type="text" autocomplete="off" />
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left">
				<spring:bind path="user.password">
				<input name="password" type="password" autocomplete="off" />
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td align="left">
				<spring:bind path="user.confirmPassword">
				<input name="confirmPassword" type="password" autocomplete="off" />
				</spring:bind>
				</td>
			</tr>


			<tr>
				<td align="right">First Name:</td>
				<td align="left">
				<spring:bind path="user.firstName">
				<input name="firstName" type="text" autocomplete="off" />
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="right">Last Name:</td>
				<td align="left">
				<spring:bind path="user.lastName">
				<input name="lastName" type="text" autocomplete="off" />
				</spring:bind>
				</td>
			</tr>


			<tr>
				<td align="right">Email:</td>
				<td align="left">
				<spring:bind path="user.emailId">
				<input name="emailId" type="text" autocomplete="off" />
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td align="left">
				<spring:bind path="user.address">
				<input name="address" type="text" autocomplete="off" />
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="right">Identification Type</td>
				<td align="left">
				<spring:bind path="user.idType">
				<input name="idType" type="text" placeholder="Passport/License..." autocomplete="off" />
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="right">Identification Number</td>
				<td align="left">
				<spring:bind path="user.idNo">
				<input name="idNo" type="text" autocomplete="off" />
				</spring:bind>
				</td>
			</tr>
			
			
			<tr>
      				<td align="right">Role:</td>
					<td>
					<spring:bind path="user.roleId">
						<select id="role" name="roleId">
							<option value=1 selected>Merchant</option>
							<option value=2>Regular Customer</option>
							<!--  <option value=3>System Admin</option>
							<option value=4>Regular Employee</option>
							-->
						</select>
						</spring:bind>
					</td>			<%-- <td align="left"><form:input name="roleID" type="text" autocomplete="off"/> --%>
    			</tr>
			<tr>
				<td align="right">Security Question 1</td>
				<td align="left">
				<spring:bind path="user.securityQues1">
				<input name="securityQues1" type="text"
						autocomplete="off" />
						</spring:bind>
						</td>
			</tr>
			<tr>
				<td align="right">Security Answer 1</td>
				<td align="left">
				<spring:bind path="user.securityAns1">
				<input name="securityAns1" type="text"
						autocomplete="off" />
						</spring:bind>
						</td>
			</tr>
			<tr>
				<td align="right">Security Question 2</td>
				<td align="left">
				<spring:bind path="user.securityQues2">
				<input name="securityQues2" type="text"
						autocomplete="off" />
						</spring:bind>
						</td>
			</tr>
			<tr>
				<td align="right">Security Answer 2</td>
				<td align="left">
				<spring:bind path="user.securityAns2">
				<input name="securityAns2" type="text"
						autocomplete="off" />
						</spring:bind>
						</td>
			</tr>
			<tr>
				<td align="right">Security Question 3</td>
				<td align="left">
				<spring:bind path="user.securityQues3">
				<input name="securityQues3" type="text"
						autocomplete="off" />
						</spring:bind>
						</td>
			</tr>
			<tr>
				<td align="right">Security Answer 3</td>
				<td align="left">
				<spring:bind path="user.securityAns3">
				<input name="securityAns3" type="text"
						autocomplete="off" />
						</spring:bind>
						</td>
			</tr>
			 <tr>
				<td align="right">Account Number</td>
				<td align="left">
				<spring:bind path="account.accountNum">
				<input name="accountNum" type="text"
						autocomplete="off" />
						</spring:bind>
						</td>
			</tr>
			
			 <tr>
			<td></td>
             <td>

             <tags:captcha privateKey="6Lc9Qf0SAAAAAA9zpSqFhUBFM5k0dzgc-Zh1DLfC" publicKey="6Lc9Qf0SAAAAABVo2UIzPTw90lAwkqv86BmiUZac"></tags:captcha>

            </td>

   </tr>
  
			<tr>
				<td></td>
				<td><input type="submit" value="submit" /></td>
				<td><input  type="reset" value="Cancel" /></td>
			</tr>
		</table>
		<br>
		<br>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<div><jsp:include page="/WEB-INF/jsp/footer.jsp" /></div>
</body>
</html>