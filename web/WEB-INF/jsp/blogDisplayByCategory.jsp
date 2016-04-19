<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: peng
  Date: 16-4-14
  Time: 下午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Blog!!</title>
</head>
<body>
<%@ include file="top.jsp" %>
<div id="content">
    <h1>分类： ${category}</h1>
    <c:forEach var="blog" items="${allBlogs}">
        <div class="post">
            <h2><a href="/blog/show/${blog.id}.html">${blog.title}</a></h2>
            <small>${blog.updateTime} by ${blog.userName} ${blog.showCount}</small>
            <div class="entry">
                <strong>摘要： </strong> ${blog.summary}
            </div>
            <ul class="postmetadata">
                <li class="icon_cat">
                    <strong>Categories: </strong>${blog.categoryVo.name}
                </li>
                <li class="icon_bullet">
                    <strong>Tags: </strong>
                    <c:forEach var="tag" items="${blog.tagVos}">
                        ${tag.name}&nbsp;
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