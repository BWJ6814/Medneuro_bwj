package com.example.medneduro.z03_Project.Minsu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 1. application.properties에 있는 값을 가져옵니다 (@Value 사용)
    @Value("${app.upload-dir}")
    private String uploadDir;

    @Value("${app.output-slice-dir}")
    private String sliceDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 2. 가져온 경로 앞에 "file:///"을 붙여서 웹 리소스로 등록합니다.

        // (1) 업로드 폴더 연결 (/mri-file/**)
        registry.addResourceHandler("/mri-file/**")
                .addResourceLocations("file:///" + uploadDir + "/");

        // (2) 슬라이스 결과 폴더 연결 (/slices/**)
        registry.addResourceHandler("/slices/**")
                .addResourceLocations("file:///" + sliceDir + "/");
    }
}