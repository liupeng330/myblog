<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Blog!!</title>
</head>
<body>
<%@ include file="top.jsp" %>
<div id="content">
    <c:if test="${!empty category}"><h1>分类： ${category}</h1></c:if>
    <c:if test="${!empty tag}"><h1>标签： ${tag}</h1></c:if>
    <c:forEach var="blog" items="${allBlogs}">
        <div class="post">
            <h2><a href="/blog/show/${blog.id}.html">${blog.title}</a></h2>
            <small>${blog.updateTime} by ${blog.userName} ${blog.showCount}</small>
            <div class="entry">
                <strong>摘要： </strong> ${blog.summary}&nbsp;&nbsp;<a href="/blog/show/${blog.id}.html">阅读全文</a>
            </div>
            <ul class="postmetadata">
                <li class="icon_cat">
                    <strong>Categories: </strong><a href="/category/${blog.categoryVo.id}.html">${blog.categoryVo.name}</a>
                </li>
                <li class="icon_bullet">
                    <strong>Tags: </strong>
                    <c:forEach var="tag" items="${blog.tagVos}">
                        <a href="/tag/${tag.id}.html">${tag.name}</a>&nbsp;
                    </c:forEach>
                </li>
            </ul>
        </div>
        <br/>
        <br/>
    </c:forEach>
    </div>
</body>
</html>
