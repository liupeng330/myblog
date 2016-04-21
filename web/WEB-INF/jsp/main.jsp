<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jspf" %>
<table width="100%">
    <tr>
        <td valign="top">
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
        </td>
        <td valign="top" width="25%">
            right banner
        </td>
    </tr>
</table>
<p align="center">
    <c:if test="${!empty category}">
        <myblog:PageBar pageUrl="/category/${category.id}.html" pageAttrKey="pageResult"/>
    </c:if>
    <c:if test="${!empty tag}">
        <myblog:PageBar pageUrl="/tag/${tag.id}.html" pageAttrKey="pageResult"/>
    </c:if>
    <c:if test="${empty tag and empty category}">
        <myblog:PageBar pageUrl="/blog.html" pageAttrKey="pageResult"/>
    </c:if>
</p>
<%@ include file="footer.jspf" %>
