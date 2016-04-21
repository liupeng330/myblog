<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jspf" %>

<h2>${blog.title}</h2>
<small>${blog.updateTime} by ${blog.userName} ${blog.showCount}</small>
<div class="entry">
    <p>${blog.content}</p>
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

<%@ include file="footer.jspf" %>
