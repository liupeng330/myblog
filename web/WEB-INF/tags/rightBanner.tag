<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="allCats" required="true" rtexprvalue="true" type="java.util.ArrayList" description="所有分类" %>
<%@ attribute name="allTags" required="true" rtexprvalue="true" type="java.util.ArrayList" description="所有的标签" %>

<div class="catList">
    <h3>我的分类</h3>
    <ul>
        <c:forEach var="cat" items="${allCats}">
            <li>
                <a href="<c:url value="/category/${cat.id}.html"/>">${cat.name}</a>(${cat.blogCount})
            </li>
        </c:forEach>
    </ul>
</div>
<br/>
<div class="tagList">
    <h3>我的标签</h3>
    <ul>
        <c:forEach var="tag" items="${allTags}">
            <li>
                <a href="<c:url value="/tag/${tag.id}.html"/>">${tag.name}</a>(${tag.blogCount})
            </li>
        </c:forEach>
    </ul>
</div>

