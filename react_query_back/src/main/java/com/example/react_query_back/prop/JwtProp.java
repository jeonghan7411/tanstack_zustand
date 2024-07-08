package com.example.react_query_back.prop;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// application.properties의 비밀키를 가지고와서 사용
@Data
@Component
@ConfigurationProperties(prefix = "com.example.react-query-back") // com.example.react-query-back 경로 하위 속성들을 지정
public class JwtProp {

    // com.example.react-query-back.secret-key -> secretKey : {인코딩된 시크릿 키}
    // properties이름에서 - 로만 작성 _ 하면 네이밍 표준 형식 에러
    private String secretKey;

}
