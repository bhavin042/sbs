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

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<script type="text/javascript">
function validate_fileupload()
{
	var fup = document.getElementById('ufile');
    var fileName = fup.value;
    var allowed_extensions = "cer";
    var file_extension = fileName.split('.').pop(); 
    // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

    if(allowed_extensions !=file_extension)
    {
    	alert("Please Select .cer file"); // valid file extension
    	return false;
    }
  
    return true;
}
</script>
</head>
<body>
    <br>
    <br>
    <div align="center">
        <h1> Upload Certificate Here !!</h1>
 
        <form:form method="post" action="/SBS/PKI/savefiles" 
            modelAttribute="uploadForm" enctype="multipart/form-data" onsubmit="return validate_fileupload(this);">
 
            <p>Select file</p>
 		<!-- "onchange="validate_fileupload(this.value);" -->
            <table id="fileTable">
                       	
                <tr>
                    <td><input name="files" type="file" id="ufile" /></td>
                </tr>
                <!-- <tr>
                    <td><input name="ruName" type="text" id="ruName"/></td>
                </tr> -->
            </table>
            <br />
            <input type="text" id="uName" name="uName" placeholder="User Name">
           <%-- User Name <form:input name="uname" type="text" id="uName" path="uName" /> --%>
            <input type="submit" value="Upload" />
           <!--  <input id="addFile" type="button" value="Add File" /> -->
        </form:form>
 
        <br />
    </div>
</body>
</html>