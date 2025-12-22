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
    <title>MadNeduro - 회원가입</title>
    <link rel="stylesheet" href="/com/bootstrap.min.css">
    <style>
        /* [전체 레이아웃] */
        body, html {
            margin: 0; padding: 0; width: 100%; height: 100%;
            font-family: 'Noto Sans KR', sans-serif;
            background-image: url("/images/main2.png");
            background-size: cover; background-position: center; background-repeat: no-repeat;
            overflow: hidden; /* 배경 스크롤 방지 */
        }

        body::before {
            content: ""; position: absolute; top: 0; left: 0; width: 100%; height: 100%;
            background: rgba(0, 0, 0, 0.7); z-index: -1;
        }

        #main {
            width: 100%; height: 100vh;
            display: flex; justify-content: center; align-items: center;
            padding: 20px;
        }

        /* [세로형 카드 컨테이너] */
        .register-card {
            display: flex;
            flex-direction: column; /* ★ 세로 배치 핵심 */
            width: 500px; /* 너비를 좁혀서 세로형에 맞게 조정 */
            height: 90vh; /* 화면 높이의 90% 사용 */
            max-height: 850px;
            background-color: #1a1a1a;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.8);
            overflow: hidden;
            border: 1px solid rgba(255,255,255,0.1);
        }

        /* [상단 패널: 브랜드 정보] */
        .card-top {
            width: 100%;
            height: 20%; /* 높이 비율 조정 */
            background: linear-gradient(145deg, #1a2a3a 0%, #121f28 100%);
            padding: 30px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center; /* 가운데 정렬 */
            text-align: center;
            color: white;
            position: relative;
            flex-shrink: 0; /* 크기 줄어들지 않게 고정 */
        }

        .card-top h1 { font-size: 2rem; font-weight: bold; margin-bottom: 10px; color: #fff; }
        .card-top p { color: #aab8c2; font-size: 0.9rem; margin: 0; }

        .feature-list { display: none; } /* 세로형에서는 공간 부족으로 리스트 숨김 (선택사항) */

        /* [하단 패널: 입력 폼] */
        .card-bottom {
            width: 100%;
            height: 75%; /* 나머지 높이 */
            background-color: #1a1a1a;
            padding: 30px 40px;
            display: flex;
            flex-direction: column;

            /* ★ 스크롤 설정 (중요) */
            overflow-y: auto;
            -ms-overflow-style: none; /* IE, Edge 스크롤바 숨기기 */
            scrollbar-width: none; /* Firefox 스크롤바 숨기기 */
        }

        /* Chrome, Safari, Opera 스크롤바 숨기기 */
        .card-bottom::-webkit-scrollbar { display: none; }

        .form-header { margin-bottom: 20px; text-align: center; }
        .form-header h2 { color: white; font-weight: bold; font-size: 1.5rem; }

        /* [입력 필드 스타일] */
        .form-label { color: #ccc; font-size: 0.85rem; margin-bottom: 5px; }
        .form-control {
            background-color: #2b2b3b;
            border: 1px solid #444;
            color: white;
            height: 40px; /* 높이 약간 줄임 */
            border-radius: 5px;
            font-size: 0.9rem;
        }
        .form-control:focus {
            background-color: #333344;
            color: white;
            border-color: #0d6efd;
            box-shadow: none;
        }

        /* [탭 스타일] */
        .user-type-selector {
            display: flex;
            background-color: #2b2b3b;
            border-radius: 5px;
            padding: 4px;
            margin-bottom: 20px;
        }
        .user-type-selector input[type="radio"] { display: none; }
        .user-type-selector label {
            flex: 1; text-align: center; padding: 8px 0; cursor: pointer;
            border-radius: 5px; color: #888; font-weight: 500; transition: all 0.3s;
            font-size: 0.9rem;
        }
        .user-type-selector input[type="radio"]:checked + label {
            background-color: #404050; color: white; font-weight: bold;
        }

        /* [버튼 스타일] */
        .btn-primary-custom {
            background-color: #0d6efd; border: none; color: white; font-weight: bold;
            height: 45px; border-radius: 5px; width: 100%; margin-top: 20px;
        }
        .btn-primary-custom:hover { background-color: #0b5ed7; }

        .btn-check-custom {
            background-color: #444; border: 1px solid #555; color: white; font-size: 0.8rem;
        }
        .btn-cancel {
            background-color: transparent; border: 1px solid #444; color: #aaa;
            height: 45px; border-radius: 5px; width: 100%; margin-top: 10px;
        }
        .btn-cancel:hover { background-color: #222; color: white; }

        .terms-box {
            background-color: #1f1f25; border: 1px solid #333; color: #aaa;
            padding: 10px; border-radius: 5px; height: 80px; overflow-y: auto;
            font-size: 0.8rem; margin-bottom: 10px;
            -ms-overflow-style: none; scrollbar-width: none;
        }
        .terms-box::-webkit-scrollbar { display: none; }
    </style>
    <script src="/com/jquery-3.7.1.js"></script>
    <script src="/com/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var isIdChecked = false;
            $("input[name='id']").on("input",function(){ isIdChecked = false; })

            $("input[name='birthDate']").keyup(function(){
                if($(this).val().length >= 8) { $("input[name='gender']").focus(); }
            });

            $("input[name='phoneNumber']").keyup(function(){
                var val = $(this).val().replace(/[^0-9]/g, '');
                if(val.length > 3 && val.length < 8){
                    val = val.substr(0,3) + "-" + val.substr(3);
                }else if(val.length > 7){
                    val = val.substr(0,3) + "-" + val.substr(3, 4) + "-" + val.substr(7);
                }
                $(this).val(val);
            });

            $("input[name='userType']").change(function(){
                if($(this).val() == 'doctor') {
                    $("#doctorFields").slideDown();
                } else {
                    $("#doctorFields").slideUp();
                    $("input[name='licenseNo']").val("");
                    $("input[name='deptId']").val("");
                }
            });

            $("#regBtn").click(function(){
                var id = $("input[name='id']").val();
                var pwd = $("input[name='pwd']").val();
                var pwd2 = $("#pwd2").val();
                var name = $("input[name='name']").val();
                var birthDate = $("input[name='birthDate']").val();
                var gender = $("input[name='gender']").val();
                var phoneNumber = $("input[name='phoneNumber']").val();
                var isAgreed = $("#agreeCheck").is(":checked");
                var userType = $("input[name='userType']:checked").val();

                if(isIdChecked == false){ alert("아이디 중복확인을 해주세요!"); $("#checkBtn").focus(); return; }
                if(id == "") { alert("아이디를 입력해주세요."); $("input[name='id']").focus(); return; }
                if(pwd == "") { alert("비밀번호를 입력해주세요."); $("input[name='pwd']").focus(); return; }
                if(pwd != pwd2) { alert("비밀번호가 일치하지 않습니다."); $("#pwd2").focus(); return; }
                if(name == "") { alert("이름을 입력해주세요."); $("input[name='name']").focus(); return; }

                if(userType == 'doctor') {
                    var licenseNo = $("input[name='licenseNo']").val();
                    var deptId = $("input[name='deptId']").val();
                    if(licenseNo == "") { alert("의사 면허 번호를 입력해주세요."); $("input[name='licenseNo']").focus(); return; }
                    if(deptId == "") { alert("부서 번호를 입력해주세요."); $("input[name='deptId']").focus(); return; }
                }

                if(birthDate.length != 8 || gender.length != 1) {
                    alert("주민등록번호를 올바르게 입력해주세요."); $("input[name='birthDate']").focus(); return;
                }
                if(phoneNumber == "") { alert("전화번호를 입력해주세요."); $("input[name='phoneNumber']").focus(); return; }
                if(!isAgreed) { alert("개인정보 수집 및 이용에 동의해주세요."); $("#agreeCheck").focus(); return; }

                if(confirm("가입하시겠습니까?")){ $("form").submit(); }
            });

            $("#cancelBtn").click(function(){ location.href = "${path}/loginpage"; });
            var msg = "${msg}"; if(msg != ""){ alert(msg); }

            $("#checkBtn").click(function(){
                var id = $("input[name='id']").val();
                if(id == ""){ alert("아이디를 입력해주세요."); $("input[name='id']").focus(); return; }
                $.ajax({
                    type : "get", url : "${path}/checkId", data : "id="+ id, dataType : "text",
                    success : function(data){
                        alert(data);
                        if(data.includes("사용 가능")){ isIdChecked = true; }
                        else { isIdChecked = false; $("input[name='id']").val(""); $("input[name='id']").focus(); }
                    },
                    error : function(err){ console.log(err); }
                })
            });
        });
    </script>
</head>

<body>
<div id="main">

    <div class="register-card">
        <div class="card-top">
            <h1>MadNeduro</h1>
            <p>MRI 3D 의료 정보 시스템</p>
        </div>

        <div class="card-bottom">

            <form action="" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="user-type-selector">
                    <input type="radio" name="userType" id="type_gen" value="general" checked>
                    <label for="type_gen">일반 회원</label>

                    <input type="radio" name="userType" id="type_doc" value="doctor">
                    <label for="type_doc">의사 회원</label>
                </div>

                <div id="doctorFields" style="display: none;">
                    <div class="mb-3">
                        <label class="form-label">의사 면허 번호</label>
                        <input type="text" name="licenseNo" class="form-control" placeholder="면허 번호 입력">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">부서 번호</label>
                        <input type="number" name="deptId" class="form-control" placeholder="부서 번호 입력 (숫자)">
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">아이디</label>
                    <div class="input-group">
                        <input type="text" name="id" class="form-control" placeholder="아이디 입력" style="border-radius: 5px 0 0 5px;">
                        <button type="button" id="checkBtn" class="btn btn-check-custom" style="border-radius: 0 5px 5px 0;">중복확인</button>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">비밀번호</label>
                    <input type="password" name="pwd" class="form-control" placeholder="비밀번호 입력">
                </div>

                <div class="mb-3">
                    <label class="form-label">비밀번호 확인</label>
                    <input type="password" id="pwd2" class="form-control" placeholder="비밀번호 재입력">
                </div>

                <div class="mb-3">
                    <label class="form-label">이름</label>
                    <input type="text" name="name" class="form-control" placeholder="이름 입력">
                </div>

                <div class="mb-3">
                    <label class="form-label">주민등록번호</label>
                    <div class="d-flex align-items-center gap-2">
                        <input type="text" name="birthDate" class="form-control text-center" maxlength="8" placeholder="생년월일(8자리)">
                        <span class="text-white">─</span>
                        <input type="text" name="gender" class="form-control text-center" maxlength="1" placeholder="뒤 1자리" >
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">휴대전화번호</label>
                    <input type="text" name="phoneNumber" class="form-control" maxlength="13" placeholder="숫자만 입력">
                </div>

                <div class="mb-3">
                    <label class="form-label text-warning">약관 동의</label>
                    <div class="terms-box">
                        1. 수집 항목: 이름, 아이디, 비밀번호, 주민번호, 연락처<br>
                        2. 목적: 본인 확인 및 진료 예약<br>
                        3. 보유 기간: 탈퇴 시까지<br>
                        ※ 주민등록번호는 의료법 시행규칙에 의거하여 수집하며, 암호화되어 안전하게 저장됩니다.
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="agreeCheck">
                        <label class="form-check-label text-white-50" for="agreeCheck">
                            위 약관에 동의합니다. (필수)
                        </label>
                    </div>
                </div>

                <button type="button" id="regBtn" class="btn-primary-custom">가입하기</button>
                <button type="button" id="cancelBtn" class="btn-cancel">취소</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>