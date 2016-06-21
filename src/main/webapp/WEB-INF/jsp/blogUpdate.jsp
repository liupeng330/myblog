<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="header.jspf" %>
<script>
    function copyToClipboard(text)
    {
        window.prompt("Copy to clipboard: Ctrl+C, Enter", text);
    }

    function getSelectValues(select, imgHeight) {
        var result = '';
        var options = select && select.options;
        var opt;

        for (var i=0, iLen=options.length; i<iLen; i++) {
            opt = options[i];

            if (opt.selected) {
                result = result + "<a href=\"${pageContext.request.contextPath}/resources/" + opt.text + "\" data-lightbox=\"image-1\"    data-title=\"\"><img src=\"${pageContext.request.contextPath}/resources/" + opt.text + "\" height=\"" + imgHeight + "%\" /></a>" + "<br/>";
            }
        }
        return result;
    }
</script>

<table width="100%">
    <tr>
        <td valign="top">
            <c:if test="${!empty errorMsg}">
                <div style="color:red">${errorMsg}</div>
            </c:if>
            <form action="<c:url value="/blog/update.html"/>" method="post">
                标题: &nbsp; <input  type="text" name="title" value="${blog.title}" style="width:100%"/><br/>
                <br/>

                摘要: <br/>
                <textarea rows="4" cols="20" name="summary" style="width:100%;">${blog.summary}</textarea><br/><br/>

                内容: <br/>
                <textarea rows="30" cols="20" name="content" style="width:100%;">${blog.content}</textarea><br/><br/>

                分类：&nbsp;
                <c:forEach var="category" items="${allCategories}">
                    <input type="radio" name="categoryVo" value="${category.id}" <c:if test='${category.checked}'>checked</c:if> /> ${category.name} &nbsp;
                </c:forEach>
                <br/><br/>

                标签：&nbsp;
                <c:forEach var="tag" items="${allTags}">
                    <input type="checkbox" name="tagVos" value="${tag.id}" <c:if test='${tag.checked}'>checked</c:if> />${tag.name} &nbsp;
                </c:forEach>
                <br/><br/>

                状态: &nbsp;
                <input type="radio" name="status" value="PUBLISHED" <c:if test='${blog.status == "PUBLISHED"}'>checked</c:if> />发布 &nbsp;
                <input type="radio" name="status" value="DRAFT" <c:if test='${blog.status == "DRAFT"}'>checked</c:if> />草稿 &nbsp;
                <br/><br/>

                格式: &nbsp;
                <input type="radio" name="blogFormat" value="HTML" <c:if test='${blog.format == "HTML"}'>checked</c:if> />HTML &nbsp;
                <input type="radio" name="blogFormat" value="MarkDown" <c:if test='${blog.format == "MarkDown"}'>checked</c:if> />MarkDown &nbsp;
                <br/><br/>

                创建时间：&nbsp; ${blog.createTime} <br/>
                更新时间：&nbsp; ${blog.updateTime}

                <input type="hidden" name="id" value="${blog.id}" /> <br/>
                <input type="submit" value="更新" />&nbsp;
                <input type="reset" value="重置" /><br/>
            </form>
        </td>
        <td valign="top" width="30%">
            <form action="<c:url value="/blog/uploadImage.html"/>" method="post" enctype="multipart/form-data">
                文件名：<input type="text" name="name">
                <br>
                <input type="file" name="file" value="选择文件">
                <br>
                <input type="submit" value="上传"/>
                <input type="hidden" name="blogId" value="${blog.id}" />
            </form>
            <br/>
            <br/>
            <select id="opts" multiple draggable="true" size="${fileNames.size()}">
                <c:forEach var="name" items="${fileNames}">
                <option>${name}
                    </c:forEach>
            </select>
            <input type="text" id="imgHeight" width="10px"/>%
            <button onclick="
                  var el = document.getElementById('opts');
                  var imgHeight = document.getElementById('imgHeight').value;
                  copyToClipboard(getSelectValues(el, imgHeight));
            ">Show selected values</button>
        </td>
    </tr>
</table>

<%@ include file="footer.jspf" %>
