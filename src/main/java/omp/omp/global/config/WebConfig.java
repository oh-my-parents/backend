package omp.omp.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String CORS_URL_PATTERN = "/**";

    private static final String CORS_URL = "*";
    private static final String CORS_METHOD = "*";

    /**
     * CORS 문제를 위한 글로벌 설정함수 입니다.
     * 프론트엔드 서버 만들면 보안상 수정 필요합니다.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(CORS_URL_PATTERN)
                .allowedOriginPatterns(CORS_URL)
                .allowedMethods(CORS_METHOD)
                .allowedHeaders("*") // 어떤 헤더들을 허용할 것인지
                .allowCredentials(true); // 쿠키 요청을 허용한다(다른 도메인 서버에 인증하는 경우에만 사용해야하며, true 설정시 보안상 이슈가 발생할 수 있다)
    }
}
