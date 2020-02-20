<%--
  Created by IntelliJ IDEA.
  User: EMINCAKICI
  Date: 2/21/2020
  Time: 12:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;chtarset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Title</title>
</head>
<body>
        <form:form modelAttribute="owner" method="post">
                First Name: <form:input path="firstName"/><br/>
                <form:errors path="firstName" cssStyle="color: red"/>
                <br/>
                Last Name: <form:input path="lastName"/><br/>
                <form:errors path="lastName" cssStyle="color: red"/>
                <br/>
                <form:button name="submit">Update</form:button>
        </form:form>
</body>
</html>
