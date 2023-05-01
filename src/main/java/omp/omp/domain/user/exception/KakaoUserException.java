package omp.omp.domain.user.exception;

import lombok.Getter;

@Getter
public class KakaoUserException extends RuntimeException {

    private final KakaoUserExceptionGroup kakaoUserExceptionGroup;

    public KakaoUserException(KakaoUserExceptionGroup kakaoUserExceptionGroup) {
        this.kakaoUserExceptionGroup = kakaoUserExceptionGroup;
    }
}
