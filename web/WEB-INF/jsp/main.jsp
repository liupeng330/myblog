<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myblog" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="mytest" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>My Blog!!</title>
    <style>
        .wrapper {
            height:100vh;
            display:flex;
        // Direction of the items, can be row or column
        flex-direction: column;
        }
        header, footer {
            height: 30px;
        }
        main {
            flex:1;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <header>
        <%@ include file="top.jspf" %>
    </header>
    <main>
        <c:if test="${!empty category}">
            <h1>分类： ${category.name}</h1>
        </c:if>
        <c:if test="${!empty tag}">
            <h1>标签： ${tag.name}</h1>
        </c:if>
        <c:forEach var="blog" items="${pageResult.currentPageData}">
            <div class="post">
                <h2><a href="<c:url value="/blog/show/${blog.id}.html"/>">${blog.title}</a></h2>
                <small>${blog.updateTime} by ${blog.userName} ${blog.showCount}</small>
                <div class="entry">
                    <strong>摘要： </strong> ${blog.summary}&nbsp;&nbsp;<a href="<c:url value="/blog/show/${blog.id}.html"/>">阅读全文</a>
                </div>
                <ul class="postmetadata">
                    <li class="icon_cat">
                        <strong>Categories: </strong><a href="<c:url value="/category/${blog.categoryVo.id}.html"/>">${blog.categoryVo.name}</a>
                    </li>
                    <li class="icon_bullet">
                        <strong>Tags: </strong>
                        <c:forEach var="tag" items="${blog.tagVos}">
                            <a href="<c:url value="/tag/${tag.id}.html"/>">${tag.name}</a>&nbsp;
                        </c:forEach>
                    </li>
                </ul>
            </div>
            <br/>
            <br/>
        </c:forEach>
    </main>
    <footer>
        <c:if test="${!empty category}">
            <myblog:PageBar pageUrl="/category/${category.id}.html" pageAttrKey="pageResult"/>
        </c:if>
        <c:if test="${!empty tag}">
            <myblog:PageBar pageUrl="/tag/${tag.id}.html" pageAttrKey="pageResult"/>
        </c:if>
        <c:if test="${empty tag and empty category}">
            <myblog:PageBar pageUrl="/blog.html" pageAttrKey="pageResult"/>
        </c:if>
    </footer>
    <%--<myblog:mytest myattr="${pageResult.currentPageData}"/>--%>
</div>
</body>
</html>
