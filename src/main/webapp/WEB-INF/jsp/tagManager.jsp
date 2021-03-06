<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="header.jspf" %>

<c:if test="${!empty errorMsg}">
    <div style="color:red">${errorMsg}</div>
</c:if>
<a href="<c:url value="/management/tag/create.html"/>">创建</a><br/>
<table border="1px" width="100%">
    <tr>
        <td>id</td>
        <td>标签名</td>
        <td>创建时间</td>
        <td>更改时间</td>
        <td>操作列</td>
    </tr>
    <c:forEach var="tag" items="${allTags}">
        <tr>
            <td>${tag.id}</td>
            <td>${tag.name}</td>
            <td>${tag.createTime}</td>
            <td>${tag.updateTime}</td>
            <td><a href="<c:url value="/management/tag/delete/${tag.id}.html"/>">删除</a> &nbsp;
                <a href="<c:url value="/management/tag/update/${tag.id}.html"/>">更新</a></td>
        </tr>
    </c:forEach>
</table>

<%@ include file="footer.jspf" %>
