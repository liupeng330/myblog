<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="header.jspf" %>

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

<%@ include file="footer.jspf" %>
