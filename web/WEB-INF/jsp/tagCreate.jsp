<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>标签管理页</title>
</head>
<body>
<%@ include file="top.jsp" %>

<form action="<c:url value="/management/tag/create.html"/>" method="post">
    <table border="1px"  width="100%">
        <tr>
            <td width="20%">标签名</td>
            <td width="80%"><input  type="text" name="name"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>

    <%@ include file="bottom.jsp" %>
</body>
</html>
