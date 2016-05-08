<%@ tag import="pengliu.me.domain.Blog" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="allCats" required="true" rtexprvalue="true" type="java.util.ArrayList" description="所有分类" %>
<%@ attribute name="allTags" required="true" rtexprvalue="true" type="java.util.ArrayList" description="所有的标签" %>
<%@ attribute name="topTenBlogs" required="true" rtexprvalue="true" type="java.util.ArrayList" description="所有的标签" %>
<%@ attribute name="topTenViewCountBlogs" required="true" rtexprvalue="true" type="java.util.ArrayList" description="所有的标签" %>

<div id="sideLeft">
    <div class="side-recent-posts">
        <h2>最近的博客</h2>
        <ul class="bullet">
            <% int blogIndex = 0; %>
            <c:forEach var="blog" items="${topTenBlogs}">
                <li>
                    <a href="<c:url value="/blog/show/${blog.id}.html"/>">${blog.title}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <br/>
    <div class="side-categories">
        <h2>我的分类</h2>
        <ul class="folder">
            <c:forEach var="cat" items="${allCats}">
                <li>
                    <a href="<c:url value="/category/${cat.id}.html"/>">${cat.name}</a>(${cat.blogCount})
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div id="sideRight">
    <div class="side-tags">
        <h2>我的标签</h2>
        <ul class="chain">
            <c:forEach var="tag" items="${allTags}">
                <li>
                    <a href="<c:url value="/tag/${tag.id}.html"/>">${tag.name}</a>(${tag.blogCount})
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="latestBlogs">
        <h3>阅读排行榜</h3>
        <ul class="bullet">
            <% int blogIndexForViewCount = 0; %>
            <c:forEach var="blog" items="${topTenViewCountBlogs}">
                <li>
                    <a href="<c:url value="/blog/show/${blog.id}.html"/>"><%= ++blogIndexForViewCount %>. ${blog.title}(${blog.showCount})</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
