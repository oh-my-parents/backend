package omp.omp.domain.user.exception;

import lombok.Getter;

@Getter
public enum KakaoUserExceptionGroup {
    USER_NULL("K001", "넘겨받은 Kakao Access Token으로 조회되는 카카오 로그인 정보가 없습니다."),
    USER_SCORE_NULL("K002", "로그인한 Kakao profile에 저장되어 있는 닉네임 정보가 없습니다.");

    private final String userErrorCode;
    private final String desc;

    KakaoUserExceptionGroup(String userErrorCode, String desc) {
        this.userErrorCode = userErrorCode;
        this.desc = desc;
    }
}
