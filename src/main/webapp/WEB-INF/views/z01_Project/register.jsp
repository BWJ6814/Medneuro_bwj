<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="java.util.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:requestEncoding value="UTF-8"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/com/bootstrap.min.css">
    <style>
        body{
            display: flex;
        }
        #main{
            width : 100%;
            min-height : 100vh;
            background-image: url("/images/req1.png");
            background-size: 100% 200vh;
            background-repeat: no-repeat;
            background-position: 0px 0px;


        }
    </style>
    <script src="/com/jquery-3.7.1.js"></script>
    <script src="/com/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</head>

<body>
    <div id="main">


    </div>
</body>
</html>