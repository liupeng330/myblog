<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Blog!!</title>
</head>
<body>
<%@ include file="top.jsp" %>
<div id="content">
    <c:if test="${!empty category}"><h1>分类： ${category}</h1></c:if>
    <c:if test="${!empty tag}"><h1>标签： ${tag}</h1></c:if>
    <c:forEach var="blog" items="${pageResult.currentPageData}">
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

<div style="background-color:#DDDDDD">
    共${pageResult.totalPageCount}页，第${pageResult.currentPageNo}页
    <c:if test="${pageResult.currentPageNo <=1}">
        首页&nbsp;&nbsp;
    </c:if>
    <c:if test="${pageResult.currentPageNo >1 }">
        <a href="/blog.html?pageNo=1">首页</a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${pageResult.hasPreviousPage}">
        <a href="/blog.html?pageNo=${pageResult.currentPageNo - 1}">上一页</a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${!pageResult.hasPreviousPage}">
        上一页&nbsp;&nbsp;
    </c:if>
    <c:if test="${pageResult.hasNextPage}">
        <a href="/blog.html?pageNo=${pageResult.currentPageNo + 1}">下一页</a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${!pageResult.hasNextPage}">
        下一页&nbsp;&nbsp;
    </c:if>
    <c:if test="${pageResult.currentPageNo >= pageResult.totalPageCount}">
        末页&nbsp;&nbsp;
    </c:if>
    <c:if test="${pageResult.currentPageNo < pageResult.totalPageCount}">
        <a href="/blog.html?pageNo=${pageResult.totalPageCount }">末页</a>&nbsp;&nbsp;
    </c:if>
</div>

</body>
</html>
