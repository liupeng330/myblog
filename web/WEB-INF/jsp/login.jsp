<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员登陆页</title>
</head>
<body>
<%@ include file="top.jspf" %>

<c:if test="${!empty errorMsg}">
    <div style="color:red">${errorMsg}</div>
</c:if>

<form:form method="POST" commandName="user" action="/management/user/login.html">
<table border="1px">
        <tr>
            <td width="20%">用户名</td>
            <td width="80%"><form:input path="name" /></td>
        </tr>
        <tr>
            <td width="20%">密码</td>
            <td width="80%"><form:input path="password" type="password" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登录">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
