<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<%@ include file="header.jspf" %>

<c:if test="${!empty errorMsg}">
    <div style="color:red">${errorMsg}</div>
</c:if>
<form action="<c:url value="/blog/create.html"/>" method="post">
    标题: &nbsp; <input  type="text" name="title" style="width:500px"/><br/>

    摘要: <br/>
    <textarea rows="4" cols="20" name="summary" style="width:80%;"></textarea><br/><br/>

    内容: <br/>
    <textarea rows="4" cols="20" name="content" style="width:80%;"></textarea><br/><br/>

    分类：&nbsp;
    <c:forEach var="category" items="${allCategories}">
        <input type="radio" name="categoryVo" value="${category.id}"/>${category.name} &nbsp;
    </c:forEach>
    <br/><br/>

    标签：&nbsp;
    <c:forEach var="tag" items="${allTags}">
        <input type="checkbox" name="tagVos" value="${tag.id}"/>${tag.name} &nbsp;
    </c:forEach>
    <br/><br/>

    状态: &nbsp;
    <input type="radio" name="status" value="PUBLISHED"/>发布 &nbsp;
    <input type="radio" name="status" value="DRAFT"/>草稿 &nbsp;
    <br/><br/>

    <input type="submit" value="保存">&nbsp;
    <input type="reset" value="重置"><br/>
</form>

<form action="<c:url value="/blog/uploadImage.html"/>" method="post" enctype="multipart/form-data">
    文件名：<input type="text" name="name">
    <br>
    <input type="file" name="file">
    <br>
    <input type="submit" value="upload"/>
</form>

<%@ include file="footer.jspf" %>
