<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ include file="header.jspf" %>--%>

<%--<h2>${blog.title}</h2>--%>
<%--<small>${blog.updateTime} by ${blog.userName} ${blog.showCount}</small>--%>
<%--<div class="entry">--%>
<%--<p>${blog.content}</p>--%>
<%--</div>--%>
<%--<ul class="postmetadata">--%>
<%--<li class="icon_cat">--%>
<%--<strong>Categories: </strong><a href="<c:url value="/category/${blog.categoryVo.id}.html"/>">${blog.categoryVo.name}</a>--%>
<%--</li>--%>
<%--<li class="icon_bullet">--%>
<%--<strong>Tags: </strong>--%>
<%--<c:forEach var="tag" items="${blog.tagVos}">--%>
<%--<a href="<c:url value="/tag/${tag.id}.html"/>">${tag.name}</a>&nbsp;--%>
<%--</c:forEach>--%>
<%--</li>--%>
<%--</ul>--%>
<%--<%@ include file="footer.jspf" %>--%>

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jspf" %>
<div id="wrapper">
    <div id="content">
        <c:if test="${!empty category}">
            <h1>分类： ${category.name}</h1>
        </c:if>
        <c:if test="${!empty tag}">
            <h1>标签： ${tag.name}</h1>
        </c:if>
        <div class="post">
            <h2><a href="<c:url value="/blog/show/${blog.id}.html"/>">${blog.title}</a></h2>
            <small>${blog.updateTime} by ${blog.userName} ${blog.showCount}</small>
            <div class="entry">
                ${blog.content}
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
    </div>
    <div id="sidebar">
        <myblog:rightBanner allCats="${allCategories}" allTags="${allTags}" topTenBlogs="${topTenBlogs}" topTenViewCountBlogs="${topTenViewCountBlogs}"/>
    </div>
</div>
<%@ include file="footer.jspf" %>