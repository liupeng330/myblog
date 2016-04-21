<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>分类管理页</title>
</head>
<body>
<%@ include file="top.jspf" %>
    <a href="<c:url value="/management/category/create.html"/>">创建</a><br/>
    <table border="1px" width="100%">
        <tr>
            <td>id</td>
            <td>分类名</td>
            <td>创建时间</td>
            <td>更改时间</td>
            <td>操作列</td>
        </tr>
        <c:forEach var="category" items="${allCategories}">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>${category.createTime}</td>
                <td>${category.updateTime}</td>
                <td><a href="<c:url value="/management/category/delete/${category.id}.html"/>">删除</a> &nbsp;
                <a href="<c:url value="/management/category/update/${category.id}.html"/>">更新</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
