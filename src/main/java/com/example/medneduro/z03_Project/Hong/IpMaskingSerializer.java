package com.example.medneduro.z03_Project.Hong;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class IpMaskingSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 이미 만들어둔 SecurityUtil의 도구를 사용함!
        gen.writeString(SecurityUtil.maskIp(value));
    }
}
