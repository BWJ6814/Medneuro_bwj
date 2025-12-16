<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>서비스 오류</title>
    <%-- 부트스트랩 연결 --%>
    <link rel="stylesheet" href="${path}/com/bootstrap.min.css">
    <script src="${path}/com/jquery-3.7.1.js"></script>
    <script src="${path}/com/bootstrap.min.js"></script>
    <style>
        .container { margin-top: 0; }
    </style>
</head>
<body>

<div class="container">
    <div class="card shadow">
        <div class="card-header bg-danger text-white">
            <h3 class="card-title mb-0">🚨 서비스 오류 발생</h3>
        </div>
        <div class="card-body text-center">

            <h4 class="card-text mt-3">이용에 불편을 드려 죄송합니다.</h4>
            <p class="text-muted">시스템 문제로 인해 작업을 완료하지 못했습니다.<br>잠시 후 다시 시도해 주세요.</p>

            <%-- 오류 이미지 --%>
            <img src="${path}/error/11.gif" class="img-fluid rounded mb-3" >

            <%-- 상세 오류 내용 (테이블 스타일 적용) --%>
            <div class="table-responsive">
                <table class="table table-bordered text-left">
                    <tr>
                        <th class="bg-light" style="width: 120px;">상태 코드</th>
                        <td><span class="badge badge-danger" style="font-size:1em;">
                            <%= request.getAttribute("javax.servlet.error.status_code") %>
                        </span></td>
                    </tr>
                    <tr>
                        <th class="bg-light">오류 유형</th>
                        <td>
                            <%
                                Object exc = request.getAttribute("javax.servlet.error.exception");
                                if(exc != null) { out.print(exc.getClass().getName()); }
                                else { out.print("-"); }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <th class="bg-light">메시지</th>
                        <td class="text-danger">
                            <%
                                Object msg = request.getAttribute("javax.servlet.error.message");
                                if(msg != null) { out.print(msg); }
                                else { out.print("알 수 없는 오류"); }
                            %>
                        </td>
                    </tr>
                </table>
            </div>

            <a href="${path}/" class="btn btn-secondary mt-3">홈으로 돌아가기</a>
        </div>
    </div>
</div>

</body>
</html>