package com.example.medneduro.z00_error; // 1. 패키지명은 생성한 위치에 맞게 수정

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    // 2. 모든 에러 발생 시 스프링부트가 이 경로(/error)로 요청을 보냅니다.
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        
        // 3. 에러의 상태 코드(404, 500 등)를 가져옵니다.
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            // 4. [404 Not Found] 에러인 경우
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // views/error/404.jsp 파일을 찾아갑니다.
                return "error/404"; 
            }
            
            // 5. [500 Internal Server Error] 등 나머지 에러인 경우
            // views/error/errorPage.jsp 파일을 찾아갑니다.
            // (스크린샷에 보이는 파일명이 errorPage.jsp라면 아래처럼 작성)
            else {
                return "error/errorPage"; 
            }
        }
        
        // 상태 코드가 없는 에러의 경우 기본 에러 페이지로
        return "error/errorPage";
    }
}