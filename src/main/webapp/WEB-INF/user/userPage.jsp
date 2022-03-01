<%--
  Created by IntelliJ IDEA.
  User: Checenec
  Date: 12.02.2022
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <img src="img/navbar_logo.jpg">
    <div class="container-fluid">
        <a class="navbar-brand font-size" href="${pageContext.request.contextPath}/user"><span class="mb-0 h2">Library</span> </a>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/user">Search</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/userProfile">Subscriptions and receipts</a>
            </div>
        </div>
        <form class="d-flex">
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/" class="btn btn-danger">Logout</a>
            </div>
        </form>
    </div>
</nav>
<form action="${pageContext.request.contextPath}/user">
    <div class="input-group w-25">
        <input type="text" id="search" name="search" class="form-control" placeholder="Input name or author's name" aria-describedby="basic-addon2">
        <button class="btn btn-secondary" type="submit">Search</button>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">Sort</button>
            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user?sort=name">Sort by name</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user?sort=author">Sort by author</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user?sort=edition">Sort by edition</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user?sort=year">Sort by year edition</a>
            </ul>
        </div>
    </div>
</form>
<nav aria-label="...">
    <ul class="pagination pagination-sm">
        <c:forEach begin="1" end="${countPage}" var="i">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user?page=${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>
<table class="table w-75 p-3">
    <thead class="table-dark">
    <tr>
        <th scope="col">id</th>
        <th scope="col">Name</th>
        <th scope="col">Author</th>
        <th scope="col">Edition</th>
        <th scope="col">Year Edition</th>
        <th scope="col">Amount</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.getId()}"/></td>
            <td><c:out value="${book.getName()}"/></td>
            <td><c:out value="${book.getAuthor()}"/></td>
            <td><c:out value="${book.getEdition()}"/></td>
            <td><c:out value="${book.getYear_edition()}"/></td>
            <td><c:out value="${book.getAmount()}"/></td>
            <td><a class="btn btn-secondary d-grid gap-2 col-6 mx-auto" href="${pageContext.request.contextPath}/orderbook?bookID=${book.getId()}&userID=${user.getId()}">Order book</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>