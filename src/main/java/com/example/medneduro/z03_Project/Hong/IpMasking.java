package com.example.medneduro.z03_Project.Hong;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = IpMaskingSerializer.class) // 아래에서 만들 Serializer와 연결!
public @interface IpMasking {
}
