<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>404 Not Found</title>
    <%-- 부트스트랩 연결 (a00_com 폴더 경로 확인) --%>
    <link rel="stylesheet" href="${path}/com/bootstrap.min.css">
    <script src="${path}/com/jquery-3.7.1.js"></script>
    <script src="${path}/com/bootstrap.min.js"></script>
    <style>
        /* 화면 중앙 정렬을 위한 추가 스타일 */
        .container { margin-top: 0; max-width: 1000px; }
    </style>
</head>
<body>

<div class="container">
    <div class="jumbotron text-center bg-light border rounded p-5">
        <h1 class="display-4 text-danger">404 Error 😅</h1>
        <p class="lead">죄송합니다. 요청하신 페이지를 찾을 수 없습니다.</p>
        <hr class="my-4">

        <p>입력하신 주소가 정확한지 다시 한번 확인해 주세요.</p>

        <%-- 잘못된 URL 보여주기 (부트스트랩 alert 스타일) --%>
        <div class="alert alert-warning" role="alert">
            <strong>요청 URL:</strong> <%= request.getAttribute("javax.servlet.error.request_uri") %>
        </div>

        <%-- 이미지 출력 (img-fluid: 화면 크기에 맞춰 자동 조절) --%>
        <img src="${path}/error/error2.gif" class="img-fluid mb-4" >

        <br>
        <%-- 메인으로 가는 버튼 --%>
        <a href="${path}/" class="btn btn-primary btn-lg">메인 화면으로 이동</a>
    </div>
</div>

</body>
</html>