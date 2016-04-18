<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>博客管理页</title>
</head>
<body>
<div id="top-panel">
    <%@ include file="top.jsp" %>
</div>
<br/>

<div id="content-pane">
    <c:if test="${!empty errorMsg}">
        <div style="color:red">${errorMsg}</div>
    </c:if>
    <form action="<c:url value="/blog/create.html"/>" method="post">
        标题: &nbsp; <input  type="text" name="title" style="width:500px"/><br/>

        摘要: <br/>
        <textarea rows="4" cols="20" name="summary" style="width:80%;"></textarea><br/><br/>

        内容: <br/>
        <textarea rows="4" cols="20" name="content" style="width:80%;"></textarea><br/><br/>

        分类：&nbsp;
        <c:forEach var="category" items="${allCategories}">
            <input type="radio" name="categoryVo" value="${category.id}"/>${category.name} &nbsp;
        </c:forEach>
        <br/><br/>

        标签：&nbsp;
        <c:forEach var="tag" items="${allTags}">
            <input type="checkbox" name="tagVos" value="${tag.id}"/>${tag.name} &nbsp;
        </c:forEach>
        <br/><br/>

        状态: &nbsp;
        <input type="radio" name="status" value="PUBLISHED"/>发布 &nbsp;
        <input type="radio" name="status" value="DRAFT"/>草稿 &nbsp;
        <br/><br/>

        <input type="submit" value="保存">&nbsp;
        <input type="reset" value="重置"><br/>
    </form>
</div>
</body>
</html>
