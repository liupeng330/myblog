<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>分类管理页</title>
</head>
<body>
<%@ include file="top.jsp" %>

<table border="1px" width="100%"
    <c:forEach var="category" items="${allCategories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.createTime}</td>
            <td>${category.updateTime}</td>
        </tr>
    </c:forEach>
</table>

<%@ include file="bottom.jsp" %>
</body>
</html>
