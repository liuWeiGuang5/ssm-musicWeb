<%--
  Created by IntelliJ IDEA.
  User: 刘伟光的PC
  Date: 2021/6/25
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <!-- 必须的 meta 标签 -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap 的 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <title>用户列表</title>
</head>
<body>
<%--<table>
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>生日</th>
        <th>性别</th>
        <th>户籍</th>
    </tr>
    <c:forEach items="${ulist}" var="u">
    <tr>
        <td>${u.id}</td>
        <td>${u.username}</td>
        <td>
            <fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd"/>
        </td>
        <td>
            <c:if test="${u.sex==0}">女</c:if>
            <c:if test="${u.sex==1}">男</c:if>
        </td>
        <td>${u.address}</td>
    </tr>
    </c:forEach>
</table>--%>


<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">id</th>
        <th scope="col">姓名</th>
        <th scope="col">生日</th>
        <th scope="col">性别</th>
        <th scope="col">户籍</th>
    </tr>
    </thead>
    <c:forEach items="${ulist}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>
                <fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd"/>
            </td>
            <td>
                <c:if test="${u.sex==0}">女</c:if>
                <c:if test="${u.sex==1}">男</c:if>
            </td>
            <td>${u.address}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
