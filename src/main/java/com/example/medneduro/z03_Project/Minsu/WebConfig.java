package com.example.medneduro.z03_Project.Minsu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload-dir}")
    private String uploadDir;

    @Value("${app.output-slice-dir}")
    private String sliceDir;

    // 1. [추가] application.properties에서 3D 모델 경로 가져오기
    @Value("${app.output-obj-dir}")
    private String objDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // (1) 업로드 폴더 연결 (/mri-file/**)
        registry.addResourceHandler("/mri-file/**")
                .addResourceLocations("file:///" + uploadDir + "/");

        // (2) 2D 슬라이스 결과 폴더 연결 (/slices/**)
        registry.addResourceHandler("/slices/**")
                .addResourceLocations("file:///" + sliceDir + "/");

        // 2. [추가] 3D 모델 결과 폴더 연결 (/obj/**)
        // -> 이걸 추가해야 404 에러가 사라집니다!
        registry.addResourceHandler("/obj/**")
                .addResourceLocations("file:///" + objDir + "/");
    }
}