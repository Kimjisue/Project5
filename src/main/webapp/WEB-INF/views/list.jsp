<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kimjisue
  Date: 2022/12/02
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <h1>자유게시판</h1>

    <table id="list" width="90%">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Writer</th>
            <th>Content</th>
            <th>Regdate</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${list}" var="u">
    <tr>
        <td>${u.seq}</td>
        <td>${u.title}</td>
        <td>${u.writer}</td>
        <td>${u.content}</td>
        <td>${u.regdate}</td>
        <td><a href="editform/${u.seq}">글수정</a></td>
        <td><a href="javascript:delete_ok('${u.seq}')">글삭제</a></td>
    </tr>
</c:forEach>

    </table>
</body>

</html>
